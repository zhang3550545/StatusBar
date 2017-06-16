package com.yundoku.statusbar.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yundoku.statusbar.R;
import com.yundoku.statusbar.manager.StatusBarManager;
import com.yundoku.statusbar.utils.ViewHelperUtils;

/**
 * Created by Widsom Zhang on 2017/6/16.
 */

public class ImageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarManager.getInstance().setActivityWindowStyle(getActivity().getWindow());
        StatusBarManager.getInstance().setStatusBar(getActivity().getWindow(), Color.TRANSPARENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = (ImageView) view.findViewById(R.id.second_image);
        ViewHelperUtils.setImageViewBitmap(imageView);
    }
}
