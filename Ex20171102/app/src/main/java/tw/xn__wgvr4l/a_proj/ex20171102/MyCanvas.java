package tw.xn__wgvr4l.a_proj.ex20171102;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Nagahi on 2017/11/2.
 */

public class MyCanvas extends View {
    private Paint paint    = null;
    public ACanvas aCanvas = null;
    public AGrid aGrid     = null;
    public APoker aPoker   = null;

    public class ACanvas {
        int width  = 0;
        int height = 0;
    }

    public class AGrid {
        int num = 3;
        int randomPos[][];

        AGrid(int num) {
            this.num = num;
            randomPos = new int[this.num * this.num][2];

            for (int i = 0; i < randomPos.length; i++) {
                randomPos[i][0] = 2;
                randomPos[i][1] = 4;
            }
        }
    }

    public class APoker {
        Bitmap bitmap = null;
        int fullWidth  = 0;
        int fullHeight = 0;
        int width  = 0;
        int height = 0;

        Rect src, dest;

        APoker(Context context) {
            bitmap     = BitmapFactory.decodeResource(context.getResources(), R.drawable.poker);

            fullWidth  = bitmap.getWidth();
            fullHeight = bitmap.getHeight();

            width      = (fullWidth / 13);
            height     = (fullHeight / 5);
        }
    }

    public MyCanvas(Context context, int gridNum) {
        super(context);

        paint  = new Paint();
        aCanvas = new ACanvas();
        aGrid   = new AGrid(gridNum);
        aPoker  = new APoker(context);

        paint.setStrokeWidth(3);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        aCanvas.width = canvas.getWidth();
        aCanvas.height = canvas.getHeight();

        canvas.drawColor(Color.BLACK);
        canvas.drawRect(3, 3, aCanvas.width, aCanvas.height, paint);

        for (int i = 1; i < aGrid.num; i++) {
            canvas.drawLine(0, (i * (aCanvas.height / aGrid.num)), aCanvas.width, (i * (aCanvas.height / aGrid.num)), paint);
            canvas.drawLine((i * (aCanvas.width / aGrid.num)), 0, (i * (aCanvas.width / aGrid.num)), aCanvas.height, paint);
        }


        int keyId = 0;
        for (int i = 0; i < aGrid.num; i++) {
            for (int j = 0; j < aGrid.num; j++) {
                aPoker.src  = new Rect((aGrid.randomPos[keyId][0] * aPoker.width), (aGrid.randomPos[keyId][1] * aPoker.height), ((aGrid.randomPos[keyId][0] + 1) * aPoker.width), ((aGrid.randomPos[keyId][1] + 1) * aPoker.height));
                aPoker.dest = new Rect((i * (aCanvas.width / aGrid.num)), (j * (aCanvas.height / aGrid.num)), ((i + 1) * (aCanvas.width / aGrid.num)), ((j + 1) * (aCanvas.height / aGrid.num)));
                canvas.drawBitmap(aPoker.bitmap, aPoker.src, aPoker.dest, null);

                keyId++;
            }
        }

        invalidate();
    }
}