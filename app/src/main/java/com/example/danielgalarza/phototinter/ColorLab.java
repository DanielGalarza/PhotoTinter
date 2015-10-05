package com.example.danielgalarza.phototinter;

import android.content.Context;
import android.graphics.Color;
import java.util.Random;


import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Daniel Galarza on 9/30/15.
 * This Class provides the list portion of the view on the app a OneColor object that knows its
 * two colors to blend (Preset colors I hard coded) and helps with setting the title of the items
 * on the list.
 */
public class ColorLab {

    private static ColorLab sColorLab;
    private Context mAppContext;
    private ArrayList<OneColor> mColors;

    public ColorLab(Context appContext) {

        mAppContext = appContext;
        mColors = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            //int count = i;
            OneColor c = new OneColor();

            switch(i) {

                case 0 : c.setTitle("Blue Tint");
                    c.setColor1(0, 0, 255);
                    c.setColor2(0, 0, 255);
                    break;
                case 1 : c.setTitle("Gray Tint");
                    c.setColor1(211, 211, 211);
                    c.setColor2(211, 211, 211);
                    break;
                case 2 : c.setTitle("Green Tint");
                    c.setColor1(0, 255, 0);
                    c.setColor2(0, 255, 0);
                    break;
                case 3 : c.setTitle("Purple Tint");
                    c.setColor1(186, 85, 211);
                    c.setColor2(186, 85, 211);
                    break;
                case 4 : c.setTitle("Red Tint");
                    c.setColor1(255, 0, 0);
                    c.setColor2(255, 0, 0);
                    break;
                case 5 : c.setTitle("Yellow Tint");
                    c.setColor1(255, 255, 0);
                    c.setColor2(255, 255, 0);
                    break;

                case 6 : Random r = new Random();
                    c.setTitle("Random Tint");
                    c.setColor1(r.nextInt(256), r.nextInt(256), r.nextInt(256));
                    c.setColor2(r.nextInt(256), r.nextInt(256), r.nextInt(256));
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

    public OneColor getColor(UUID id) {
        for (OneColor c: mColors) {

            if(c.getId().equals(id))
                return c;

        }
        return null;
    }

}
