package com.yundoku.statusbar.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class MutilDialogFragment extends DialogFragment {

    public static MutilDialogFragment newInstance() {
        return new MutilDialogFragment();
    }

    public static void showAdDialog(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        MutilDialogFragment prev = (MutilDialogFragment) fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            if (prev.isVisible()) {
                prev.dismiss();
            }
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        MutilDialogFragment adDialogFragment = MutilDialogFragment.newInstance();
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
        StatusBarManager.getInstance().setDialogWindowStyle(getDialog().getWindow(), Color.parseColor("#dd0000"));
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_text, container);
        final TextView textView = (TextView) view.findViewById(R.id.image);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelperUtils.click(getDialog().getWindow(), textView);
            }
        });
        return view;
    }

}
