package com.example.auditiondemo.MyViewDemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.auditiondemo.R;

public class AppBar extends LinearLayout implements View.OnClickListener {
    public AppBar(Context context) {
       this(context,null);
    }

    public AppBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Init(context,attrs);
    }

    private void Init(Context context, AttributeSet attrs) {
            if(attrs!=null){
                TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.xhead);
            }
    }

    @Override
    public void onClick(View v) {
        

    }
}
