package tw.xn__wgvr4l.a_proj.ex20171019;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Nagahi on 2017/10/19.
 */

public class MyCanvas extends View {
    private Paint paint[] = new Paint[2];

    public int gridNum = 3;
    public float canvasWidth  = 0;
    public float canvasHeight = 0;

    public int field[][];

    public MyCanvas(Context context, int gridNum) {
        super(context);

        this.gridNum = gridNum;

        for (int i = 0; i < paint.length; i++) {
            paint[i] = new Paint();
            paint[i].setStrokeWidth(3);
        }

        paint[0].setColor(Color.WHITE);
        paint[0].setStyle(Paint.Style.STROKE);

        paint[1].setColor(Color.BLACK);

        field = new int[gridNum][gridNum];

        for (int i= 0; i < gridNum; i++)
            for (int j= 0; j < gridNum; j++)
               field[i][j] = Color.BLACK;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        canvasWidth  = (canvas.getWidth() - 0);
        canvasHeight = canvasWidth;

        canvas.drawRect(0, 0, canvasWidth, canvasWidth, paint[0]);

        float circleSize = (canvasWidth / gridNum) / 3;

        for (int i= 1; i < gridNum; i++) {
            canvas.drawLine(0, (i*(canvasWidth/gridNum)), canvasWidth, (i*(canvasWidth/gridNum)), paint[0]);
            canvas.drawLine((i*(canvasHeight/gridNum)), 0, (i*(canvasHeight/gridNum)), canvasHeight, paint[0]);

        }
        for (int i= 0; i < gridNum; i++) {
            for (int j = 0; j < gridNum; j++) {
                paint[1].setColor(field[i][j]);

                float pointX = ((canvasWidth / gridNum) / 2) + (i * (canvasWidth / gridNum));
                float pointY = ((canvasHeight / gridNum) / 2) + (j * (canvasHeight / gridNum));

                canvas.drawCircle(pointX, pointY, circleSize, paint[1]);
            }
        }

        invalidate();
    }
}
