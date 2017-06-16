package com.yundoku.statusbar.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Window;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.fragment.MutilChangeFragment;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class MutilChangeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.fragment, new MutilChangeFragment()).commit();
    }
}
