package com.example.careminder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.example.careminder.R;


public class VisualizerView extends View {
    private byte[] bytes;
    private Paint paint = new Paint();
    private float volumeThreshold = 20.0f;

    final int numLines = 20;

    public VisualizerView(Context context) {
        super(context);
        init();
    }

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bytes = null;

        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.black));
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void updateVisualizer(byte[] bytes) {
        this.bytes = bytes;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bytes == null) {
            return;
        }

        float width = getWidth();
        float height = getHeight();
        int bytesPerLine = bytes.length / numLines;

        float strokeWidth = width / numLines - 5;
        paint.setStrokeWidth(strokeWidth);

        float centerY = height / 2;
        for (int i = 0; i < numLines; i++) {
            int start = i * bytesPerLine;
            int end = start + bytesPerLine;
            float avgAbsValue = 0;
            for (int j = start; j < end; j++) {
                avgAbsValue += Math.abs(bytes[j]);
            }
            avgAbsValue /= bytesPerLine;
            avgAbsValue = Math.max(avgAbsValue - volumeThreshold, 0);
            Log.d("test", "" + avgAbsValue);

            float relX = i * (width - strokeWidth) / (numLines - 1) + strokeWidth/2;
            float absY = avgAbsValue / (128.0f - volumeThreshold) * (height - strokeWidth) / 2;
            canvas.drawLine(relX, centerY - absY, relX, centerY + absY, paint);
        }
    }

}
