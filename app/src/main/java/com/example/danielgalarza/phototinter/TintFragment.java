package com.example.danielgalarza.phototinter;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        mColors = ColorLab.get(getActivity()).getColors();




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        // Used to inflate the layout (view) of the fragment containing the UI.
        View v = inflater.inflate(R.layout.photo_fragment, parent, false);

        /**********************************************************************************/
        mImageView = (ImageView)v.findViewById(R.id.photo);

        /**********************************************************************************/

        mPhotoChooser = (Button)v.findViewById(R.id.photo_button);

        mPhotoChooser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Used reference from http://developer.android.com/training/basics/intents/result.html
                // Starting an intent to the photo gallery on the device.
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider
                                    .MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        /**********************************************************************************/
        mTintButton = (Button)v.findViewById(R.id.tint_button);

        /**********************************************************************************/


        /**********************************************************************************/


        /**********************************************************************************/


        /**********************************************************************************/


        /**********************************************************************************/


        /**********************************************************************************/


        /**********************************************************************************/

        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == getActivity().RESULT_OK)
        {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();

            int columnIndex = c.getColumnIndex(filePath[0]);
            photoPath = c.getString(columnIndex);
            c.close();
            mPhoto = (BitmapFactory.decodeFile(photoPath));
            mImageView.setImageBitmap(mPhoto);
        }
    }

    /*@Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //Note n = (Note)(getListAdapter()).getItem(position);
        //Note n = ((NoteAdapter)getListAdapter()).getItem(position);
        //will display message to console when list item is clicked
        //Log.d(TAG, n.getTitle() + " was clicked");

    } */

    ///////////////////////////////////////////////
   /* private class ListAdapter extends ArrayAdapter<> {
        public ListAdapter(ArrayList<> notes) {
            super(getActivity(), 0, notes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.photo_fragment, null);
            }
            // Configure the view for this Crime
            //Note n = getItem(position);

            TextView mFragTitle = (TextView)convertView.findViewById(R.id.item_title);
            //mFragTitle.setText(n.getTitle());

            //TextView mFragDetails = (TextView)convertView.findViewById(R.id.frag_details);
            //mFragDetails.setText(n.getD());
            //String mTitle;
            //int mFragTitle;
            //int mDetails;

            return convertView;
        }
    }*/
}
