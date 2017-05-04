package com.example.styort.fragments02;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private AppsPagerAdapter mAppsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        PackageManager pm = getPackageManager();
        mAppsPagerAdapter = new AppsPagerAdapter(getSupportFragmentManager(), pm.getInstalledApplications(0));

        mViewPager.setAdapter(mAppsPagerAdapter);
    }
}
