package com.example.danielgalarza.phototinter;

import java.util.UUID;
import android.graphics.Color;

/**
 * Created by danielgalarza on 9/30/15.
 */
public class OneColor {

    private UUID mId;
    private String mTitle;

    private int mColor1;
    private int mColor2;

    private int mRed1;
    private int mGreen1;
    private int mBlue1;

    private int mRed2;
    private int mGreen2;
    private int mBlue2;



    public OneColor() {

        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    public int getColor1(int r, int g, int b) {
        return Color.rgb(r,g,b);
    }

    public void setColor1(int r, int g, int b) {
        mColor1 = Color.rgb(r,g,b);
    }
    public int getColor2(int r, int g, int b) {
        return Color.rgb(r,g,b);
    }

    public void setColor2(int r, int g, int b) {
        mColor2 = Color.rgb(r,g,b);
    }

    public int getColor1() {
        return mColor1;
    }

    public int getColor2() {
        return mColor2;
    }




}
