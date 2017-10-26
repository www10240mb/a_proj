package tw.xn__wgvr4l.a_proj.ex20171012;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Canvas canvas;
    Paint paint;
    Bitmap bitmap;

    float x0, y0, x1, y1;

    Button[] button = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        button[0] = (Button)findViewById(R.id.refreshBtn);
        button[1] = (Button)findViewById(R.id.redBtn);
        button[2] = (Button)findViewById(R.id.greenBtn);
        button[3] = (Button)findViewById(R.id.blueBtn);

        final int []color = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};


        for (int i = 1; i < color.length; i++) {
            final int ii = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    paint.setColor(color[ii]);
                }
            });
        }
        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                canvas = new Canvas(bitmap);
                imageView.setImageBitmap(bitmap);
            }
        });

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);


        imageView.setImageBitmap(bitmap);

        imageView.setOnTouchListener(new Touch());
    }

    class Touch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    x0 = event.getX();
                    y0 = event.getY();

                }
                case MotionEvent.ACTION_MOVE: {
                    x1 = event.getX();
                    y1 = event.getY();

                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawLine(x1, y1, x0, y0, paint);

                    x0 =x1 ;
                    y0 =y1 ;

                    imageView.setImageBitmap(bitmap);
                }
            }
            return true;
        }
    }
}
