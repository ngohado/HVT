package com.hvt.hbapplication.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.hvt.hbapplication.util.font.Font;
import com.hvt.hbapplication.util.font.FontCache;


public class CustomFontTextView extends AppCompatTextView {
    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private Typeface selectTypeface(Context context, int style) {
        switch (style) {
            case Typeface.BOLD:
                return FontCache.getTypeface(context, Font.FIRASANS_BOLD);

            case Typeface.ITALIC:
                return FontCache.getTypeface(context, Font.FIRASANS_ITALIC);

            case Typeface.BOLD_ITALIC:
                return FontCache.getTypeface(context, Font.FIRASANS_BOLD_ITALIC);

            case Typeface.NORMAL:
            default:
                return FontCache.getTypeface(context, Font.FIRASANS);
        }
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }
}