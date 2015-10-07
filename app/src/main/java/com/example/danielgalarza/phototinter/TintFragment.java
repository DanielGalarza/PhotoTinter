package com.example.danielgalarza.phototinter;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by danielgalarza on 9/30/15.
 */
public class TintFragment extends Fragment {  //extends Fragment

    private Bitmap mPhoto;
    private ImageView mImageView;
    private String photoPath;

    private Button mPhotoChooser;
    private Button mTintButton;
    private Button mButtonColor1;
    private Button mButtonColor2;

    private SeekBar mBlender;
    private TextView mBlendedColor;

    private Button mBlueTint;
    private Button mGrayTint;
    private Button mGreenTint;
    private Button mPurpleTint;
    private Button mRedTint;
    private Button mYelloTint;
    private Button mRandomTint;

    private ArrayList<OneColor> mColors;


    public static final String EXTRA_COLOR = "com.example.danielgalarza.phototinter.color_one";

    //Request codes to handle activity results
    public static final int PHOTO_REQ = 0;
    public static final int COLOR_ONE_REQ = 1;
    public static final int COLOR_TWO_REQ = 2;


    //default values for the color buttons
    int colorOne = Color.WHITE;
    int colorTwo = Color.WHITE;
    int defaultColorOne = Color.WHITE;
    int defaultColorTwo = Color.WHITE;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        mColors = ColorLab.get(getActivity()).getColors();

    }

    /*****************
     *  UI CONTENTS  *
     *****************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Used to inflate the layout (view) of the fragment containing the UI.
        View v = inflater.inflate(R.layout.photo_fragment, parent, false);

        // Link mBlendedColor to color swatch 'blended_color' view
        mBlendedColor = (TextView) v.findViewById(R.id.blended_color);

        // Link mImageView to the image display view/area
        mImageView = (ImageView)v.findViewById(R.id.photo);

        /**********************************************************************************
         * BUTTON ADD PHOTO - LAUNCHES IMAGE CHOOSER - WAIT FOR RESPONSE INTENT
         * *********************************************************************************/
        mPhotoChooser = (Button)v.findViewById(R.id.photo_button);
        mPhotoChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Used reference from http://developer.android.com/training/basics/intents/result.html
                // Starting an intent to the photo gallery on the device.
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, 0);
            }
        });
        /*********************************************************************************
         * BUTTON TINT - ADD TINT TO PHOTO
         **********************************************************************************/
        mTintButton = (Button)v.findViewById(R.id.tint_button);

        /**********************************************************************************
         * BUTTON LEFT: CHOOSE COLOR #1 - LAUNCH COLOR-PICKER - WAIT FOR RESPONSE INTENT
         **********************************************************************************/
        mButtonColor1 = (Button)v.findViewById(R.id.color1_button);
        mButtonColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ColorPickerAppActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        /**********************************************************************************
         * BUTTON RIGHT: CHOOSE COLOR #2 - LAUNCH COLOR-PICKER - WAIT FOR RESPONSE INTENT
         **********************************************************************************/
        mButtonColor2 = (Button)v.findViewById(R.id.color2_button);
        mButtonColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ColorPickerAppActivity.class);
                startActivityForResult(intent, 2);
            }
        });
        /**********************************************************************************
         * SEEK BAR - BLEND COLORS based on value of seek-bar
         **********************************************************************************/
        mBlender = (SeekBar)v.findViewById(R.id.seekBar);
        mBlender.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int seekValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue = progress;

                // UPDATE COLOR WITH SEEK-BAR VALUE
                update(seekBar);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        update(mBlender);

        /* *********************************************************************************/


        /* *********************************************************************************/


        /* *********************************************************************************/


        /* *********************************************************************************/

        return v;

    }/*** END UI CONTENTS *** END onCreateView() ***/


    /******************************************
     * UPDATE COLOR OF TEXT-VIEW COLOR PALLET *
     ******************************************/
    private void update(final SeekBar mBlender) {

        mBlendedColor = (TextView) mBlendedColor.findViewById(R.id.blended_color);

        final int colorStart = colorOne;
        final int colorEnd = colorTwo;

        mBlendedColor.setBackgroundColor(interpolateColor(colorStart, colorEnd, mBlender.getProgress() / 100f));

    }

    /**************************************************************
     * COLOR BLENDING HANDLED BY THE NEXT TWO INTERPOLATE METHODS *
     **************************************************************/
    private float interpolate(final float a, final float b, final float proportion){
        return (a + ((b-a) * proportion));
    }

    private int interpolateColor(final int a, final int b, final float proportion){
        final float[] hsva = new float[3];
        final float[] hsvb = new float[3];
        Color.colorToHSV(a, hsva);
        Color.colorToHSV(b, hsvb);
        for (int i = 0; i < 3; i++){
            hsvb[i] = interpolate(hsva[i], hsvb[i], proportion);
        }
        return Color.HSVToColor(hsvb);
    }
    /********* END COLOR BLEND - INTERPOLATION ALGORITHM *********/

    /**************************************************************
     ******** HANDLE INTENTS FOR MULTIPLE REQUEST-CODES ***********
     **************************************************************/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result was returned correctly
        if(resultCode == getActivity().RESULT_OK) {

            // Handle photo chooser result - display chosen image
            switch (requestCode) {

                case PHOTO_REQ:
                    Uri selectedImage = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    photoPath = c.getString(columnIndex);
                    c.close();
                    mPhoto = (BitmapFactory.decodeFile(photoPath));
                    mImageView.setImageBitmap(mPhoto);
                    break;

                // Handle color picker result for button one
                case COLOR_ONE_REQ:
                    colorOne = data.getIntExtra(EXTRA_COLOR, defaultColorOne);
                    //update left button (1) color
                    mButtonColor1.setBackgroundColor(colorOne);
                    update(mBlender);
                    break;

                // Handle color picker result for button two
                case COLOR_TWO_REQ:
                    colorTwo = data.getIntExtra(EXTRA_COLOR, defaultColorTwo);
                    //update right button (2) color
                    mButtonColor2.setBackgroundColor(colorTwo);
                    update(mBlender);
                    break;
            }

        }
    }// END onActivityResult
    /**********  END REQUEST-CODE LOGIC - INTENT HANDLER **********/

}// END MAIN: TintFragment
