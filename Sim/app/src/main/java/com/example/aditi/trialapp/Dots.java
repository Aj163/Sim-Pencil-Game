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

    private int X[] = {0, 0, 0, 0, 0, 0};
    private int Y[] = {0, 0, 0, 0, 0, 0};
    private int degree[] = {0, 0, 0, 0, 0, 0};
    private int radius = 35, turn, pointTurn, gameDone;

    Paint pointPaint[] = {new Paint(), new Paint(), new Paint(),
            new Paint(), new Paint(), new Paint()};

    int adj[][] = new int[6][6];

    public Dots(Context context){
        super(context);

        for(int i=0; i<6; i++){
            pointPaint[i].setColor(Color.BLACK);
        }

        for(int i=0; i<6; i++)
            for(int j=0; j<6; j++)
                adj[i][j] = 0;
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

        int w = canvas.getWidth();
        int h = canvas.getHeight();

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

        //Points
        for(int i=0; i<6; i++)
            canvas.drawCircle(X[i], Y[i], radius, pointPaint[i]);

        if(gameOver()){
            gameDone = 1;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(gameDone == 1)
            return super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point p = new Point((int) event.getX(), (int)event.getY());

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
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                if(adj[i][j]==1)
                {
                    for(int k=i;k<6;k++){
                        for(int l=j+1;j<6;j++){
                            if(adj[k][l]==1){
                                for(int m=k;m<6;m++){
                                    for(int n=l+1;n<6;n++){
                                        if(adj[m][n]==1)
                                            return true;
                                    }
                                }
                                for(int m=k+1;m<6;m++){
                                    for(int n=l;n<6;n++){
                                        if(adj[m][n]==1)
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                    for(int k=i+1;k<6;k++){
                        for(int l=j;l<6;l++){
                            if(adj[k][l]==1){
                                for(int m=k;m<6;m++){
                                    for(int n=l+1;n<6;n++){
                                        if(adj[m][n]==1)
                                            return true;
                                    }
                                }
                                for(int m=k+1;m<6;m++){
                                    for(int n=l;n<6;n++){
                                        if(adj[m][n]==1)
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                }
                else if(adj[i][j]==2){
                    for(int k=i;k<6;k++){
                        for(int l=j+1;j<6;j++){
                            if(adj[k][l]==2){
                                for(int m=k;m<6;m++){
                                    for(int n=l+1;n<6;n++){
                                        if(adj[m][n]==2)
                                            return true;
                                    }
                                }
                                for(int m=k+1;m<6;m++){
                                    for(int n=l;n<6;n++){
                                        if(adj[m][n]==2)
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                    for(int k=i+1;k<6;k++){
                        for(int l=j;l<6;l++){
                            if(adj[k][l]==2){
                                for(int m=k;m<6;m++){
                                    for(int n=l+1;n<6;n++){
                                        if(adj[m][n]==2)
                                            return true;
                                    }
                                }
                                for(int m=k+1;m<6;m++){
                                    for(int n=l;n<6;n++){
                                        if(adj[m][n]==2)
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
};
