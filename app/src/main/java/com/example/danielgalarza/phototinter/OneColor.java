package com.example.danielgalarza.phototinter;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by danielgalarza on 9/30/15.
 */
public class OneColor {

    private String mTitle;
    private int mColor;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }

    public int makeColorWithAlpha(int a, int r, int g, int b) {
        return Color.argb(a, r, g, b);
    }

    public int makeRandomColor() {
        Random r = new Random();
        return Color.argb(127, r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }







}
