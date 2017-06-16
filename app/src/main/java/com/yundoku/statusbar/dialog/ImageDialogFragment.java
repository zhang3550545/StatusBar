package com.yundoku.statusbar.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class ImageDialogFragment extends DialogFragment {

    public static ImageDialogFragment newInstance() {
        return new ImageDialogFragment();
    }

    public static void showAdDialog(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ImageDialogFragment prev = (ImageDialogFragment) fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            if (prev.isVisible()) {
                prev.dismiss();
            }
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        ImageDialogFragment adDialogFragment = ImageDialogFragment.newInstance();
        adDialogFragment.show(ft, "dialog");
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        if (getActivity() != null) {
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarManager.getInstance().setDialogWindowStyle2(getDialog().getWindow());
        View view = inflater.inflate(R.layout.common_image, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.second_image);
        ViewHelperUtils.setImageViewBitmap(imageView);
        return view;
    }

}
