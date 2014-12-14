package com.metaphore.qbanksimplepassword;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Exactly the same TextView but with custom font (App.rf)
 */
public class CustomFontTextView extends TextView {
    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Typeface font = App.rf;

//        if (attrs != null) {
//            final TypedArray a = context.obtainStyledAttributes(attrs,
//                    R.styleable.CustomFontTextView, defStyle, 0);
//
//            boolean bold = a.getBoolean(R.styleable.CustomFontTextView_bold, false);
//            if (bold) {
//                font = MyApplication.rf_bold;
//            }
//
//            a.recycle();
//        }

        setTypeface(font);
    }
}
