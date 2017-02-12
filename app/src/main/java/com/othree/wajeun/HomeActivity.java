package com.othree.wajeun;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.othree.wajeun.adapters.HomePagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private NavigationTabStrip mTopNavigationTabStrip;
    private NavigationTabStrip mCenterNavigationTabStrip;
    private NavigationTabStrip mBottomNavigationTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = (ViewPager) findViewById(R.id.vp_main);

        mCenterNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);

        mViewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);
        navigationTabStrip.setViewPager(mViewPager);

    }





}
