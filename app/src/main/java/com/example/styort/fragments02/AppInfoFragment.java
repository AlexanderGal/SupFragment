package com.example.styort.fragments02;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by user14 on 04.05.2017.
 */

public class AppInfoFragment extends Fragment {
    private TextView packageNameField;
    private static final String ARG_APPLICATION = "application";

    public static AppInfoFragment newInstance(ApplicationInfo info) {
        AppInfoFragment fragment = new AppInfoFragment();
        Bundle bundle = new Bundle();

        bundle.putParcelable(ARG_APPLICATION, info);
        fragment.setArguments(bundle);
        return fragment;
    }

    private ApplicationInfo applicationInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationInfo = getArguments().getParcelable(ARG_APPLICATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_info_fragment, container, false);
        packageNameField = (TextView) view.findViewById(R.id.package_name);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AppTitleSubFragment appInfoFragment = AppTitleSubFragment.newInstance(applicationInfo);
        getChildFragmentManager().beginTransaction().add(R.id.app_info_fragment, appInfoFragment).commit();
    }
}
