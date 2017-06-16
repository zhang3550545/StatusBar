package com.yundoku.statusbar.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

public class MutilStatusBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        StatusBarManager.getInstance().setStatusBar(getWindow(), Color.parseColor("#dd0000"));
        setContentView(R.layout.activity_text);
        final TextView imageView = (TextView) findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelperUtils.click(getWindow(), imageView);
            }
        });
    }
}
