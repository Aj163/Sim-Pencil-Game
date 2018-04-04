package com.example.app.trialapp;

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
    private int radius = 30, turn, pointTurn;

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

        for(int i=0; i<6; i++)
            canvas.drawCircle(X[i], Y[i], radius, pointPaint[i]);

        turn = 0; //Player 1
        pointTurn = 0; //Player has selected 0 points
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Point p = new Point((int) event.getX(), (int)event.getY());

                for(int i=0; i<6; i++)
                    if(Math.abs(p.x - X[0]) <= radius && Math.abs(p.y - Y[0]) <= radius){

                    }

                invalidate();
        }
        return super.onTouchEvent(event);
    }
};
