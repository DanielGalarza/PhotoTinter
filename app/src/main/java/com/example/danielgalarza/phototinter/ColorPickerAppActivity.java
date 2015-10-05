package com.example.danielgalarza.phototinter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;


public class ColorPickerAppActivity extends Activity {

    int rVal = 0;
    int gVal = 0;
    int bVal = 0;

    int color;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_picker_app);

        final NumberPicker mNumberPickerR = (NumberPicker) findViewById(R.id.numPicker_R);
        final NumberPicker mNumberPickerG = (NumberPicker) findViewById(R.id.numPicker_G);
        final NumberPicker mNumberPickerB = (NumberPicker) findViewById(R.id.numPicker_B);
        final Button mButton = (Button)findViewById(R.id.get_button);


        mNumberPickerR.setMaxValue(255);
        mNumberPickerR.setMinValue(0);
        mNumberPickerR.setWrapSelectorWheel(true);
        mNumberPickerR.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                rVal = newVal;
                setColor(rVal, gVal, bVal);
            }
        });


        mNumberPickerG.setMaxValue(255);
        mNumberPickerG.setMinValue(0);
        mNumberPickerG.setWrapSelectorWheel(true);
        mNumberPickerG.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                gVal = newVal;
                setColor(rVal, gVal, bVal);
            }
        });

        mNumberPickerB.setMaxValue(255);
        mNumberPickerB.setMinValue(0);
        mNumberPickerB.setWrapSelectorWheel(true);
        mNumberPickerB.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                bVal = newVal;
                setColor(rVal, gVal, bVal);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                    // Making an intent to the Photo Tinter. It will send one color at
                    Intent i = new Intent();
                    i.putExtra(TintFragment.EXTRA_COLOR, getColor());
                    setResult(RESULT_OK, i);
                    finish();


                }

        });

    }//end onCreate


    private void setColor(int rVal, int gVal, int bVal) {
        Log.i("SettingColor", "Red: " + rVal + " Green: " + gVal + " Blue: " + bVal);
        color = Color.rgb(rVal, gVal, bVal);
        View bgElement = findViewById(R.id.myRectangleView);
        bgElement.setBackgroundColor(color);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_number_picker_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public int getColor() {
        return color;
    }



}
