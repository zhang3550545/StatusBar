package com.yundoku.statusbar.fragment;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

/**
 * Created by Widsom Zhang on 2017/6/15.
 */

public class MutilChangeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarManager.getInstance().setStatusBar(getActivity().getWindow(), Color.parseColor("#dd0000"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_text, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final TextView textView = (TextView) view.findViewById(R.id.image);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewHelperUtils.click(getActivity().getWindow(), textView);
            }
        });
    }
}
