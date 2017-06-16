package com.yundoku.statusbar.manager;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class StatusBarManager {
    private final static StatusBarManager ourInstance = new StatusBarManager();

    public static StatusBarManager getInstance() {
        return ourInstance;
    }

    private StatusBarManager() {
    }

    /**
     * 设置StatusBar字体颜色
     * <p>
     * 参数true表示StatusBar风格为Light，字体颜色为黑色
     * 参数false表示StatusBar风格不是Light，字体颜色为白色
     * <p>
     * <item name="android:windowLightStatusBar">true</item>
     * 在theme或style中使用这个属性改变StatusBar的字体颜色，这种形式相对不灵活
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void setStatusBarTextColor(Window window, boolean lightStatusBar) {
        if (window == null) return;
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        if (lightStatusBar) {
            ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decor.setSystemUiVisibility(ui);
    }

    /**
     * 设置StatusBar的颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBar(Window window, @ColorInt int color) {
        if (window == null) return;
        window.setStatusBarColor(color);
    }

    /**
     * 设置Dialog风格的Window的背景色
     * <p>
     * 这个和设置StatusBar的颜色有区别的，它是设置一个Window背景色，
     * 这时StatusBar和Window的背景色保持一致，如果StatusBar下面的View背景色改变，会就盖住Window的背景色，界面就会丑陋，
     * 如果想做到沉浸式，除非将StatusBar下面的View的颜色和Window的背景色同时改变
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setDialogWindowStyle(Window window, @ColorInt int color) {
        if (window == null) return;
        window.setBackgroundDrawable(new ColorDrawable(color));
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
    }

    /**
     * 设置Dialog风格的Window的StatusBar的颜色
     * <p>
     * 这种实现方式是将原本在StatusBar下面的View，直接固定到屏幕最上面，
     * 这时StatusBar盖在View的上面，这时对View设置背景色，就像是沉浸式了
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setDialogWindowStyle2(Window window) {
        if (window == null) return;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置window背景色为透明色
        setActivityWindowStyle(window);
    }

    /**
     * 设置Activity风格的Window的StatusBar的颜色
     * <p>
     * 这种实现方式是将原本在StatusBar下面的View，直接固定到屏幕最上面，
     * 这时StatusBar盖在View的上面，如果这个View的背景设置的是一张图片，可以显示出很好的沉浸式效果。
     * 如果View有图片，不要设置这个Window的属性，直接调用setStatusBar()方法
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setActivityWindowStyle(Window window) {
        if (window == null) return;
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }


    /**
     * 通过WindowManager来设置沉浸式状态
     * <p>
     * 通过指定window的位置params.y来设置沉浸式，
     * 这里的params.height是WRAP_CONTENT，才有效果，
     * 如果是MATCH_PARENT会显示在最上面。
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setWindowManagerStyle(final Window window, View view, boolean fullScreen) {
        if (window == null) return;
        WindowManager manager = window.getWindowManager();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = 1.0f;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = fullScreen ? WindowManager.LayoutParams.MATCH_PARENT : WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888;
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 0;
        params.y = fullScreen ? 0 : getStatusBarHeight(window.getContext());
        manager.addView(view, params);
    }

    public int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

}
