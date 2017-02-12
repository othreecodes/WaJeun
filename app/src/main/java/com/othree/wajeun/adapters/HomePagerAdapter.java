package com.othree.wajeun.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.othree.wajeun.BlankFragment;
import com.othree.wajeun.FeedFragment;
import com.othree.wajeun.NotificationFragment;

/**
 * Created by root on 2/12/17.
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {


    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FeedFragment();
            case 1:
                return new NotificationFragment();

            case 2:
                return new BlankFragment();
            case 3:
                return new BlankFragment();
            default:
                break;
        }
        return new BlankFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
