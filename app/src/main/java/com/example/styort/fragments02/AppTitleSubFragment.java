package com.example.styort.fragments02;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class AppTitleSubFragment extends Fragment {
    private TextView packageNameField;
    private ImageView imageView;

    private static final String ARG_APPLICATION = "application";

    public static AppTitleSubFragment newInstance(ApplicationInfo info) {
        AppTitleSubFragment fragment = new AppTitleSubFragment();
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
        View view = inflater.inflate(R.layout.app_title_fragment, container, false);
        packageNameField = (TextView) view.findViewById(R.id.app_title);
        imageView = (ImageView) view.findViewById(R.id.app_icon);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

        AppTitleTask appTitleTask = new AppTitleTask(imageView, packageNameField, applicationInfo);
        appTitleTask.execute();
    }

    private static class AppTitleTask extends AsyncTask<Void, Void, AppInfo> {
        private WeakReference<ImageView> imageViewWeakReference;
        private WeakReference<TextView> textViewWeakReference;
        private PackageManager packageManager;
        private ApplicationInfo applicationInfo;

        public AppTitleTask(ImageView imageView, TextView textViewWea, ApplicationInfo applicationInfo) {
            this.imageViewWeakReference = new WeakReference<ImageView>(imageView);
            this.textViewWeakReference = new WeakReference<TextView>(textViewWea);
            packageManager = imageView.getContext().getPackageManager();
            this.applicationInfo = applicationInfo;
        }

        @Override
        protected AppInfo doInBackground(Void... params) {
            AppInfo appInfo = new AppInfo();
            appInfo.name = packageManager.getApplicationLabel(applicationInfo).toString();
            appInfo.icon = packageManager.getApplicationIcon(applicationInfo);
            return appInfo;
        }

        @Override
        protected void onPostExecute(AppInfo info) {
            ImageView imageView = imageViewWeakReference.get();
            TextView textView = textViewWeakReference.get();

            if (imageView != null && textView != null) {
                imageView.setImageDrawable(info.icon);
                textView.setText(info.name);
            }
        }
    }

    private static final class AppInfo {
        private String name;
        private Drawable icon;
    }
}
