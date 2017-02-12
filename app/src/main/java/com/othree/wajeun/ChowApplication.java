package com.othree.wajeun;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by root on 2/12/17.
 */
public class ChowApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/drugs.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
