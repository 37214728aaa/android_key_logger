package com.maemresen.infsec.keylogapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * author: wangshiju
 * description: 自己绘制文本内容，避免被KeyLogger监听到文本内容
 * date: 2022-12-22
 */
public class MyTextView extends TextView {

    private String mText;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setText(String text) {
        mText = text;
//        StringBuilder fakeStr = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            fakeStr.append("*");
//        }
//        setText(fakeStr.toString());

        super.setText("");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getPaint().measureText(mText);
        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        float textHeight = fontMetrics.descent - fontMetrics.ascent;
        float m2b = textHeight / 2 - fontMetrics.descent;

        canvas.drawText(mText, (getWidth() - width) / 2, getHeight() / 2 + m2b, getPaint());
    }
}
