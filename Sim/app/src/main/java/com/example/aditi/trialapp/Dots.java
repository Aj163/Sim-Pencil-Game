package com.example.aditi.trialapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aj on 4/4/18.
 */

public class Dots extends View {

    private int w, h;
    private int X[] = {0, 0, 0, 0, 0, 0};
    private int Y[] = {0, 0, 0, 0, 0, 0};
    private int degree[] = {0, 0, 0, 0, 0, 0};
    private int losePoints[] = {0, 0, 0};
    private int radius = 35, turn, pointTurn, gameDone;

    Paint pointPaint[] = {new Paint(), new Paint(), new Paint(),
            new Paint(), new Paint(), new Paint()};

    int adj[][] = new int[6][6];

    public Dots(Context context){
        super(context);

        reset();
    }

    private void reset(){
        for(int i=0; i<6; i++){
            pointPaint[i].setColor(Color.BLACK);
        }

        for(int i=0; i<6; i++) {
            for (int j = 0; j < 6; j++)
                adj[i][j] = 0;

            degree[i] = 0;
        }

        //0 - no edge
        //1 - Player 1
        //2 - Player 2

        turn = 0; //Player 1
        pointTurn = 0; //Player has selected 0 points
        gameDone = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(gameOver()){
            gameDone = 1;
        }

        w = canvas.getWidth();
        h = canvas.getHeight();

        X[0] = X[1] = w/2;
        X[2] = X[3] = w/5;
        X[4] = X[5] = w - w/5;

        Y[0] = h/6;
        Y[1] = 19*h/20 - h/6;
        Y[2] = Y[4] = 4*h/12;
        Y[3] = Y[5] = 19*h/20 - 4*h/12;



        Paint p1 = new Paint();
        Paint p2 = new Paint();

        p1.setColor(Color.RED);
        p1.setStrokeWidth(10);

        p2.setColor(Color.GREEN);
        p2.setStrokeWidth(10);

        for(int i=0; i<6; i++)
            for(int j=i+1; j<6; j++) {
                if (adj[i][j] == 1)
                    canvas.drawLine(X[i], Y[i], X[j], Y[j], p1);
                else if (adj[i][j] == 2)
                    canvas.drawLine(X[i], Y[i], X[j], Y[j], p2);
            }

        //Game Over
        if(gameDone == 1){
            p1.setStrokeWidth(20);

            if(adj[losePoints[0]][losePoints[1]] == 1)
                p1.setColor(Color.RED);
            else
                p1.setColor(Color.GREEN);

            canvas.drawLine(X[losePoints[0]], Y[losePoints[0]],
                    X[losePoints[1]], Y[losePoints[1]], p1);
            canvas.drawLine(X[losePoints[0]], Y[losePoints[0]],
                    X[losePoints[2]], Y[losePoints[2]], p1);
            canvas.drawLine(X[losePoints[2]], Y[losePoints[2]],
                    X[losePoints[1]], Y[losePoints[1]], p1);
        }

        //Points
        for(int i=0; i<6; i++)
            canvas.drawCircle(X[i], Y[i], radius, pointPaint[i]);


        //Player turn
        Paint player = new Paint();
        if(turn == 0)
            player.setColor(Color.RED);
        else
            player.setColor(Color.GREEN);
        if(gameDone == 1)
            player.setColor(Color.WHITE);

        canvas.drawCircle(radius+30, radius+30, radius, player);
        player.setColor(Color.BLACK);
        player.setTextSize(50);
        if(gameDone == 1)
            player.setColor(Color.WHITE);
        canvas.drawText("\'s Turn", 2*radius+40, radius+40, player);

        //Game Over Text
        if(gameDone == 1){
            player.setColor(Color.BLACK);
            player.setTextSize(50);
            canvas.drawText("Player ", 40, radius+40, player);

            if(adj[losePoints[0]][losePoints[1]] == 1)
                player.setColor(Color.GREEN);
            else
                player.setColor(Color.RED);
            canvas.drawCircle(radius+200, radius+30, radius, player);

            player.setColor(Color.BLACK);
            canvas.drawText("Wins !!!", 2*radius+220, radius+40, player);


            //Play Again
            player.setColor(Color.GRAY);
            canvas.drawRect(w/4, h - 3*h/20, w - w/4, h - h/20, player);
            player.setColor(Color.BLACK);
            canvas.drawText("Play Again", w/2 - 110, h - h/13, player);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point p = new Point((int) event.getX(), (int)event.getY());

                if(gameDone == 1) {
                    if(p.x >= w/4 && p.x <= w - w/4 && p.y <= h - h/20 && p.y >= h - 3*h/20) {
                        reset();
                        invalidate();
                    }
                    return super.onTouchEvent(event);
                }

                for(int i=0; i<6; i++)
                    if(Math.abs(p.x - X[i]) <= radius && Math.abs(p.y - Y[i]) <= radius) {
                        if (pointTurn == 0 && turn == 0) {
                            if(degree[i] == 5)
                                return super.onTouchEvent(event);
                            pointPaint[i].setColor(Color.RED);
                            pointTurn = 1;
                            break;
                        }
                        else if (pointTurn == 0 && turn == 1) {
                            if(degree[i] == 5)
                                return super.onTouchEvent(event);
                            pointPaint[i].setColor(Color.GREEN);
                            pointTurn = 1;
                            break;
                        }
                        else if (pointTurn == 1 && turn == 0) {
                            Log.d("HI", "HELLO");
                            int pos = -1;
                            for(int j=0; j<6; j++)
                                if(pointPaint[j].getColor() == Color.RED){
                                    pos = j;
                                    break;
                                }

                            if(pos == i || adj[i][pos] != 0)
                                return super.onTouchEvent(event);
                            adj[pos][i] = adj[i][pos] = 1;
                            degree[i]++;
                            degree[pos]++;

                            //Reset
                            pointTurn = 0;
                            turn = 1;
                            pointPaint[pos].setColor(Color.BLACK);
                            break;
                        }
                        else if (pointTurn == 1 && turn == 1) {
                            int pos = -1;
                            for(int j=0; j<6; j++)
                                if(pointPaint[j].getColor() == Color.GREEN){
                                    pos = j;
                                    break;
                                }

                            if(pos == i || adj[i][pos] != 0)
                                return super.onTouchEvent(event);
                            adj[pos][i] = adj[i][pos] = 2;
                            degree[i]++;
                            degree[pos]++;

                            //Reset
                            pointTurn = 0;
                            turn = 0;
                            pointPaint[pos].setColor(Color.BLACK);
                            break;
                        }
                    }
                invalidate();
        }
        return super.onTouchEvent(event);
    }

    private boolean gameOver(){
        for(int i=0; i<6; i++)
            for(int j=i+1; j<6; j++)
                for(int k=j+1; k<6; k++)
                    if(adj[i][j] != 0 && adj[i][j] == adj[j][k] && adj[i][j] == adj[i][k]) {
                        losePoints[0] = i;
                        losePoints[1] = j;
                        losePoints[2] = k;
                        return true;
                    }
        return false;
    }
};
