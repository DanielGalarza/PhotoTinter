package com.example.danielgalarza.phototinter;

import android.content.Context;
import java.util.Random;


import java.util.ArrayList;

/**
 * Created by Daniel Galarza on 9/30/15.
 * This Class provides the button list portion of the view on the app. A OneColor object that knows its
 * two colors to blend (Preset colors I hard coded) and helps with setting the title of the buttons
 * for the preset options
 */
public class ColorLab {

    private static ColorLab sColorLab;
    private Context mAppContext;
    private ArrayList<OneColor> mColors;

    public ColorLab(Context appContext) {

        mAppContext = appContext;
        mColors = new ArrayList<>();

        for (int i = 0; i < 7; i++) {

            //int count = i;
            OneColor c = new OneColor();
            int color;

            switch(i) {

                case 0 : c.setTitle("Blue Tint");
                        c.setColor(c.makeColorWithAlpha(127, 0, 0, 255));
                        break;
                case 1 : c.setTitle("Gray Tint");
                        c.setColor(c.makeColorWithAlpha(127, 211, 211, 211));
                        break;
                case 2 : c.setTitle("Green Tint");
                        c.setColor(c.makeColorWithAlpha(127, 0, 255, 0));
                        break;
                case 3 : c.setTitle("Purple Tint");
                        c.setColor(c.makeColorWithAlpha(127, 186, 85, 211));
                        break;
                case 4 : c.setTitle("Red Tint");
                        c.setColor(c.makeColorWithAlpha(127, 255, 0, 0));
                        break;
                case 5 : c.setTitle("Yellow Tint");
                        c.setColor(c.makeColorWithAlpha(127, 255, 255, 0));
                        break;
                case 6 : Random r = new Random();
                        c.setTitle("Random Tint");
                        c.setColor(c.makeColorWithAlpha(127, r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                        break;
            }

            mColors.add(c);
        }

    }

    public static ColorLab get(Context c) {

        if (sColorLab == null) {
            sColorLab = new ColorLab(c.getApplicationContext());
        }

        return sColorLab;
    }

    public ArrayList<OneColor> getColors() {
        return mColors;
    }


}
