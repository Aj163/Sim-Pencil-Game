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

    private Paint paint = new Paint();

    private void init(){
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1f);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int x1 = 0, y1 = 0, x2, y2;

        ImageView img = findViewById(R.id.imageView);
        Rect r = img.getDrawable().getBounds();
        int X = (r.left + r.right)/2;
        int Y = (r.top + r.bottom)/2;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(x1==0 && y1==0)
                {
                    x1 = X; x2 = X;
                    y1 = Y; y2 = Y;
                }

                else {
                    x2 = X;
                    y2 = Y;
                }
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                canvas.drawLine(x1, y1, x2, y2, paint);
            }

        });
    }


}
