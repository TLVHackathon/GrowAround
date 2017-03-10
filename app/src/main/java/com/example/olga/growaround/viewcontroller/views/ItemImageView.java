package com.example.olga.growaround.viewcontroller.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olga.growaround.R;

/**
 * Created by Shay Lavi on 09/03/2017.
 */

public class ItemImageView extends FrameLayout {


    public ItemImageView(Context context) {
        super(context);
        init(context);
    }

    public ItemImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ItemImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_element, this);
    }

    public void setImageResource(int resource) {
        ((ImageView) findViewById(R.id.image_element)).setImageResource(resource);
    }

    public void setText(String text){
        ((TextView) findViewById(R.id.text_element)).setText(text);
    }

}
