package tw.xn__wgvr4l.a_proj.ex20171102;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by Nagahi on 2017/11/2.
 */

public class MainActivity extends AppCompatActivity {
    public MyCanvas myCanvas = null;
    public Random random     = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myCanvas = new MyCanvas(this, 3);
        random   = new Random();

        setContentView(myCanvas);

        myCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;

                float x = event.getX();
                float y = event.getY();
                float width = (myCanvas.aCanvas.width / myCanvas.aGrid.num);
                float height = (myCanvas.aCanvas.height / myCanvas.aGrid.num);

                int keyId = 0;
                for (int i = 0; i < myCanvas.aGrid.num; i++) {
                    for (int j = 0; j < myCanvas.aGrid.num; j++) {
                        if (((width * i) <= x) && (x <= ( width * (i + 1))) &&
                            ((height * j) <= y) && (y <= ( height * (j + 1)))) {

                            myCanvas.aGrid.randomPos[keyId][0] = random.nextInt(13);
                            myCanvas.aGrid.randomPos[keyId][1] = random.nextInt(4);
                            Log.d("keyId", ""+keyId);
                        }

                        keyId++;
                    }
                }

                return false;
            }
        });
    }
}