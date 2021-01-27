package com.example.maintransportation.database.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.example.maintransportation.R;

import java.util.Map;
import java.util.Random;

public class ChartView extends View {
    private Context context;
    private Map<String, Integer> source;
    private Paint paint;
    private Random random;

    public ChartView(Context context, Map<String, Integer> source) {
        super(context);
        this.context = context;
        this.source = source;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (source == null || source.isEmpty()) {
            return;
        }
        int maxValue = extractMaxValue();
        float widthBar = getWidth() / source.size();
        drawValues(canvas, maxValue, widthBar);
    }

    private void drawValues(Canvas canvas, int maxValue, float widthBar) {
        int currentBarPosition = 0;
        for (String label : source.keySet()) {
            drawValue(canvas, maxValue, widthBar, currentBarPosition, label);
            currentBarPosition++;
        }
    }

    private void drawValue(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, String label) {
        int value = source.get(label);
        int color = generateColor();
        paint.setColor(color);
        drawBar(canvas, maxValue, widthBar, currentBarPosition, value);
        drawLabel(canvas, widthBar, currentBarPosition, label, value);
    }

    private void drawLabel(Canvas canvas, float widthBar, int currentBarPosition, String label, int value) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.3 * widthBar));
        float x = (float) (currentBarPosition + 0.5) * widthBar;
        float y = (float) (0.95 * getHeight());
        canvas.rotate(270, x, y);
        canvas.drawText(context.getString(R.string.chart_label_format, label, value), x, y, paint);
        canvas.rotate(-270, x, y);
    }

    private void drawBar(Canvas canvas, int maxValue, float widthBar, int currentBarPosition, int value) {
        float x1 = currentBarPosition * widthBar;
        float y1 = (1 - (float) (value) / maxValue) * getHeight();
        float x2 = x1 + widthBar;
        float y2 = getHeight();
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    private int generateColor() {
        return Color.argb(100, 1 + random.nextInt(254),
                1 + random.nextInt(254),
                1 + random.nextInt(254));
    }

    private int extractMaxValue() {
        int maxValue = 0;
        for (Integer value : source.values()) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }
}