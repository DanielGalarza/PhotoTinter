package com.example.danielgalarza.phototinter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by danielgalarza on 9/26/15.
 */
abstract class SingleFragmentActivity extends Activity {

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tint);

        FragmentManager fm = getFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {

            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
            // how can this be broken down so that I can add 2 fragments to one activity?

        }
    }



}