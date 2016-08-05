package com.peppe130.fireinstaller;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.XpPreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.sufficientlysecure.htmltextview.HtmlRemoteImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;


@SuppressWarnings("ConstantConditions")
public class FragmentsCollector {

    public static ArrayList<Fragment> mListFragment = new ArrayList<>();

    public static void Setup() {

        mListFragment.add(new HomeFragment());
        mListFragment.add(new FirstFragment());
        mListFragment.add(new SecondFragment());
        mListFragment.add(new thirdFragment());
        mListFragment.add(new forthFragment());
    }

    public static class HomeFragment extends XpPreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            HtmlTextView mHtmlTextView = (HtmlTextView) getView().findViewById(R.id.htmlWelcome);
            assert mHtmlTextView != null;
            mHtmlTextView.setHtml(R.raw.welcome, new HtmlRemoteImageGetter(mHtmlTextView));
            ImageView mImageView = (ImageView) getView().findViewById(R.id.imageView);
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getActivity());
            mImageView.setImageResource(SP.getInt("theme", 0) == 0 ? R.drawable.exylight : R.drawable.exydark);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.home_fragment, container, false);
        }

        @Override
        public void onCreatePreferences2(Bundle savedInstanceState, String rootKey) {
            // Do nothing
        }

    }

    public static class FirstFragment extends XpPreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.first_fragment);
        }

        @Override
        public void onCreatePreferences2(Bundle savedInstanceState, String rootKey) {
            // Do nothing
        }

    }

    public static class SecondFragment extends XpPreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.second_fragment);
        }

        @Override
        public void onCreatePreferences2(Bundle savedInstanceState, String rootKey) {
            // Do nothing
        }

    }


    public static class thirdFragment extends XpPreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.third_fragment);
        }

        @Override
        public void onCreatePreferences2(Bundle savedInstanceState, String rootKey) {
            // Do nothing
        }

    }

    public static class forthFragment extends XpPreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.forth_fragment);
        }

        @Override
        public void onCreatePreferences2(Bundle savedInstanceState, String rootKey) {
            // Do nothing
        }

    }
}