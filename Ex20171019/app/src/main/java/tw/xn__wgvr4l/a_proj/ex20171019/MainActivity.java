package tw.xn__wgvr4l.a_proj.ex20171019;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Toast toast = null;

    MyCanvas myCanvas = null;
    LinearLayout canvasLayout = null;
    Button button[] = new Button[3];
    int useColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasLayout = (LinearLayout)findViewById(R.id.canvasLayout);

        myCanvas = new MyCanvas(this, 5);

        canvasLayout.addView(myCanvas);

        int buttonR_Id[] = {R.id.swRed, R.id.swBlue, R.id.swClear};
        final int buttonR_Color[] = {Color.RED, Color.BLUE, Color.BLACK};

        useColor = buttonR_Color[2];

        for (int i = 0; i < button.length; i++) {
            final int iUse = i;
            button[i] = (Button)findViewById(buttonR_Id[i]);
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    useColor = buttonR_Color[iUse];
                }
            });
        }

        myCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN)
                    return false;

                float posX = event.getX();
                float posY = event.getY();

                int x      = 0;
                int y      = 0;
                float len  = (myCanvas.canvasWidth / myCanvas.gridNum);

                if (toast != null)
                    toast.cancel();

                if (posY >=  (len * myCanvas.gridNum)) {
                    toast = Toast.makeText(MainActivity.this, "該座標超出範圍", Toast.LENGTH_SHORT);
                    toast.show();
                    return false;
                }

                for (int i = 0; i < myCanvas.gridNum; i++) {
                    if (((i * len) <= posX) && (posX <= (i + 1) * len)) {
                        x = i;
                    }

                    if (((i * len) <= posY) && (posY <= (i + 1) * len)) {
                        y = i;
                    }
                }

                if (myCanvas.field[x][y] == buttonR_Color[2]) {
                    myCanvas.field[x][y] = useColor;
                } else {
                    if (useColor == buttonR_Color[2]) {
                        myCanvas.field[x][y] = useColor;
                    } else {
                        toast = Toast.makeText(MainActivity.this, "該位置不可選", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                return true;
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        try {
            if (this.toast != null)
                this.toast.cancel();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } finally {
            this.toast = null;
        }

        finish();
    }
}
