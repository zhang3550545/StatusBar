package com.yundoku.statusbar.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.yundoku.statusbar.manager.StatusBarManager;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class ViewHelperUtils {

    private final static int RED = 0;
    private final static int YELLOW = 1;
    private final static int GREEN = 2;
    private final static int BLUE = 3;
    private final static int WHITE = 4;
    private final static int BLACK = 5;
    private static int color = RED;

    public static void click(Window window, TextView textView) {
        color++;
        StatusBarManager.getInstance().setStatusBarTextColor(window, false);
        textView.setTextColor(Color.WHITE);
        switch (color) {
            case RED:
                textView.setBackgroundColor(Color.RED);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#dd0000"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#dd0000")));
                }
                break;
            case YELLOW:
                textView.setBackgroundColor(Color.YELLOW);
                StatusBarManager.getInstance().setStatusBarTextColor(window, true);
                textView.setTextColor(Color.BLACK);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#ffdd00"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffdd00")));
                }
                break;
            case GREEN:
                textView.setBackgroundColor(Color.GREEN);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#00dd00"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00dd00")));
                }
                break;
            case BLUE:
                textView.setBackgroundColor(Color.BLUE);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#0000dd"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0000dd")));
                }
                break;
            case WHITE:
                textView.setBackgroundColor(Color.WHITE);
                StatusBarManager.getInstance().setStatusBarTextColor(window, true);
                textView.setTextColor(Color.BLACK);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#ffffdd"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffdd")));
                }
                break;
            case BLACK:
                textView.setBackgroundColor(Color.BLACK);
                if (!window.isFloating()) {
                    StatusBarManager.getInstance().setStatusBar(window, Color.parseColor("#333333"));
                } else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#333333")));
                }
                color = -1;
                break;
        }
    }

    public static void setImageViewBitmap(ImageView imageView) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path = path + "/1.jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bitmap);
    }
}
