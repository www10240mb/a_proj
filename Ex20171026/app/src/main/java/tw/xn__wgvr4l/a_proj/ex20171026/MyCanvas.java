package tw.xn__wgvr4l.a_proj.ex20171026;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nagahi on 2017/10/26.
 */

public class MyCanvas extends View {
    private Paint paint = new Paint();
    private Bitmap poker[][] = new Bitmap[4][13];
    private List<Integer> pokerBuffer = new ArrayList<>();
    private Random random = new Random();

    public int gridNum = 3;
    private Boolean refresh = false;

    int pokerRId[][] = {
            {R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5, R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10, R.drawable.h11, R.drawable.h12, R.drawable.h13},
            {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5, R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10, R.drawable.s11, R.drawable.s12, R.drawable.s13},
            {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13},
            {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10, R.drawable.d11, R.drawable.d12, R.drawable.d13}
    };

    public MyCanvas(Context context, int gridNum) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);

        for (int j = 0; j < pokerRId.length; j++)
            for (int i = 0; i < pokerRId[j].length; i++)
                poker[j][i] = BitmapFactory.decodeResource(context.getResources(), pokerRId[j][i]);

        this.gridNum = ((gridNum >= 7) ? 7 : ((gridNum <= 1) ? 1 : gridNum));

        refreshPoker();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth  = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        canvas.drawColor(Color.BLACK);
        canvas.drawRect(0, 0, canvasWidth, canvasHeight, paint);

        int pokerWidth  = ((canvasWidth / gridNum) - 30);
        int pokerHeight = ((canvasHeight / gridNum) - 50);

        int id = 0;

        for (int i = 0; i < gridNum; i++)
            for (int j = 0; j < gridNum; j++) {
                int pointX = ((30 * (i + 1)) + (pokerWidth * i)) - 15;
                int pointY = ((50 * (j + 1)) + (pokerHeight * j)) - 25;

                if (refresh) {
                    pokerBuffer.add(random.nextInt(pokerRId.length));
                    pokerBuffer.add(random.nextInt(13));
                }

                Bitmap bottomBitmap = Bitmap.createScaledBitmap(poker[pokerBuffer.get(id++)][pokerBuffer.get(id++)], pokerWidth, pokerHeight, true);
                canvas.drawBitmap(bottomBitmap, pointX, pointY, null);
            }

        refresh = false;

        invalidate();
    }

    public void refreshPoker() {
        pokerBuffer.clear();
        refresh = true;
    }
}