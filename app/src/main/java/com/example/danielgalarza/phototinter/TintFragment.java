package com.example.danielgalarza.phototinter;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Button mColor1;
    private Button mColor2;

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


    public static final int PHOTO_REQ = 0;
    public static final int COLOR_ONE_REQ = 1;
    public static final int COLOR_TWO_REQ = 2;


    //default values for the color buttons
    int colorOne;
    int colorTwo;
    int defaultColorOne = -1;
    int defaultColorTwo = -1;


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


        /* *********************************************************************************/
        mImageView = (ImageView)v.findViewById(R.id.photo);

        /* *********************************************************************************
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
        /* *********************************************************************************
         * BUTTON TINT - ADD TINT TO PHOTO
         **********************************************************************************/
        mTintButton = (Button)v.findViewById(R.id.tint_button);

        /* *********************************************************************************
         * BUTTON LEFT: CHOOSE COLOR #1 - LAUNCH COLOR-PICKER - WAIT FOR RESPONSE INTENT
         **********************************************************************************/
        mColor1 = (Button)v.findViewById(R.id.color1_button);
        mColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ColorPickerAppActivity.class);
                startActivityForResult(intent, 1);

            }
        });
        /* *********************************************************************************
         * BUTTON RIGHT: CHOOSE COLOR #2 - LAUNCH COLOR-PICKER - WAIT FOR RESPONSE INTENT
         **********************************************************************************/
        mColor2 = (Button)v.findViewById(R.id.color2_button);
        mColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ColorPickerAppActivity.class);
                startActivityForResult(intent, 2);

            }
        });
        /* *********************************************************************************/


        /* *********************************************************************************/


        /* *********************************************************************************/


        /* *********************************************************************************/


        /* *********************************************************************************/

        return v;

    }/*** END UI CONTENTS *** END onCreateView() ***/

    /**********************************************
     * HANDLE INTENTS FOR MULTIPLE REQUEST-CODES  *
     **********************************************/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result was returned correctly
        if(resultCode == getActivity().RESULT_OK) {

            // Handle photo chooser result - display chosen image
            if (requestCode == PHOTO_REQ) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                Log.d("choosing pic: ", "pic was clicked");
                int columnIndex = c.getColumnIndex(filePath[0]);
                photoPath = c.getString(columnIndex);
                c.close();
                mPhoto = (BitmapFactory.decodeFile(photoPath));
                mImageView.setImageBitmap(mPhoto);
            }

            // Handle color picker result for button one
            else if (requestCode == COLOR_ONE_REQ) {
                colorOne = data.getIntExtra(EXTRA_COLOR, defaultColorOne);
                //update left button (1) color
                mColor1.setBackgroundColor(colorOne);
            }

            // Handle color picker result for button two
            else if (requestCode == COLOR_TWO_REQ) {
                colorTwo = data.getIntExtra(EXTRA_COLOR, defaultColorTwo);
                //update right button (2) color
                mColor2.setBackgroundColor(colorTwo);
            }

            else;

        }


    }





}
