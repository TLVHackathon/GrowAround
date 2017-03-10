package com.example.olga.growaround.manager.model;

import android.support.annotation.DrawableRes;

/**
 * Created by Shay Lavi on 10/03/2017.
 */

public class itemsWrapper {


    private final int drawableResource;
    private final String name;

    public itemsWrapper(@DrawableRes int drawableResource,
                        String name) {

        this.drawableResource = drawableResource;
        this.name = name;
    }

    public int getDrawableResource() {
        return drawableResource;
    }

    public String getName() {
        return name;
    }
}
