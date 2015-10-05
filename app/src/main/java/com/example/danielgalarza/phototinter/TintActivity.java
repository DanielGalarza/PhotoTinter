package com.example.danielgalarza.phototinter;

import android.app.Fragment;

public class TintActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TintFragment();
    }
}
