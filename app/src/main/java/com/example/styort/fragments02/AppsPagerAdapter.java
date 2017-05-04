package com.example.styort.fragments02;

import android.content.pm.ApplicationInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by user14 on 04.05.2017.
 */

public class AppsPagerAdapter extends FragmentStatePagerAdapter {
    private final List<ApplicationInfo> mApplicationInfos;

    public AppsPagerAdapter(FragmentManager fm,
                            List<ApplicationInfo> applicationInfos) {
        super(fm);
        mApplicationInfos = applicationInfos;
    }

    @Override
    public Fragment getItem(int position)
    {
        return AppInfoFragment.newInstance(mApplicationInfos.get(position));
    }

    @Override
    public int getCount() {
        return mApplicationInfos.size();
    }
}
