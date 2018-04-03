package com.example.aditi.trialapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView dot1, dot2, dot3, dot4, dot5, dot6;
    int x1 = 0, x2 = 0, y1 = 0, y2 = 0, X, Y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dot1 = (ImageView) findViewById(R.id.imageView);
        dot2 = (ImageView) findViewById(R.id.imageView2);
        dot3 = (ImageView) findViewById(R.id.imageView3);
        dot4 = (ImageView) findViewById(R.id.imageView4);
        dot5 = (ImageView) findViewById(R.id.imageView5);
        dot6 = (ImageView) findViewById(R.id.imageView6);

        dot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot1.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });

        dot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot2.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });

        dot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot3.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });

        dot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot4.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });

        dot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot5.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });

        dot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rect r = dot6.getDrawable().getBounds();
                X = (r.left + r.right)/2;
                Y = (r.top + r.bottom)/2;
                if(x1 == 0 && y1 ==0)
                {
                    x1 = X; x2 = X; y1 = Y; y2 = Y;
                }
                else{
                    x2 = X; y2 = Y;
                }

                //function to draw line between (x1, y1) and (x2, y2) comes in after this
            }
        });
    }

}
