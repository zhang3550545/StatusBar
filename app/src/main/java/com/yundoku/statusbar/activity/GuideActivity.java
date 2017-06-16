package com.yundoku.statusbar.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.dialog.ImageDialogFragment;
import com.yundoku.statusbar.dialog.MutilDialogFragment;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

public class GuideActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
    }

    public void activityMutil(View view) {
        startActivity(new Intent(getApplicationContext(), MutilStatusBarActivity.class));
    }

    public void activityImage(View view) {
        startActivity(new Intent(getApplicationContext(), ImageStatusBarActivity.class));
    }

    public void fragmentMutil(View view) {
        startActivity(new Intent(getApplicationContext(), MutilChangeActivity.class));
    }

    public void fragmentImage(View view) {
        startActivity(new Intent(getApplicationContext(), ImageActivity.class));
    }

    public void dialogMutil(View view) {
        MutilDialogFragment.showAdDialog(getFragmentManager());
    }

    public void dialogImage(View view) {
        ImageDialogFragment.showAdDialog(getFragmentManager());
    }

    public void windowMutil(View view) {
        final Window window = getWindow();
        StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#dd0000"));
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_text2, null);
        final TextView textView = (TextView) v.findViewById(R.id.text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelperUtils.click(window, textView);
            }
        });
        StatusBarManager.getInstance().setWindowManagerStyle(window, v, false);
    }

    public void windowImage(View view) {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.common_image, null);
        ImageView imageView = (ImageView) v.findViewById(R.id.second_image);
        ViewHelperUtils.setImageViewBitmap(imageView);
        StatusBarManager.getInstance().setWindowManagerStyle(getWindow(), v, true);
    }
}
