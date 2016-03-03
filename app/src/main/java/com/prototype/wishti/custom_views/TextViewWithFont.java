package com.prototype.wishti.custom_views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.prototype.wishti.R;


public class TextViewWithFont extends TextView {

    public TextViewWithFont(Context context) {
        super(context);
    }

    public TextViewWithFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context,attrs);
    }

    public TextViewWithFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }


    private void initAttrs(Context context,AttributeSet attrs){

        TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.TextViewWithFont);

        final int N = attributes.getIndexCount();
        for (int i = 0; i < N; ++i)
        {
            int attr = attributes.getIndex(i);
            switch (attr)
            {
                case R.styleable.TextViewWithFont_ttf_file:
                    String fontFile = attributes.getString(attr);

                    Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontFile);

                    this.setTypeface(custom_font);

                    break;
            }
        }
        attributes.recycle();
    }

}
