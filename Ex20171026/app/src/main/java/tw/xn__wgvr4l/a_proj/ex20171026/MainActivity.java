package tw.xn__wgvr4l.a_proj.ex20171026;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.refresh);

        LinearLayout lnearLayout = (LinearLayout) findViewById(R.id.canvasLayout);

        final MyCanvas myCanvas = new MyCanvas(this, 4);

        lnearLayout.addView(myCanvas);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.refreshPoker();
            }
        });
    }
}
