package com.yundoku.statusbar.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.ImageView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

public class ImageStatusBarActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        StatusBarManager.getInstance().setActivityWindowStyle(getWindow());
        StatusBarManager.getInstance().setStatusBar(getWindow(), Color.TRANSPARENT);
        setContentView(R.layout.common_image);
        initView();
    }

    private void initView() {
        ImageView imageView = (ImageView) findViewById(R.id.second_image);
        ViewHelperUtils.setImageViewBitmap(imageView);
    }
}
