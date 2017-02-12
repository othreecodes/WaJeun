package com.othree.wajeun.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 2/11/17.
 */

public class Preferences {

    // Shared Preferences reference
    private SharedPreferences pref;
    // Editor reference for Shared preferences
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;
    // Shared pref mode
    private int PRIVATE_MODE = 0;
    // Sharedpref file name
    private static final String PREFER_NAME = "chowprefs";
    //sharedpref Show Tip key

    private static final String WELCOMESCREEN_PREF="WELCOMESCREEN";
    public Preferences(Context _context){
        this._context=_context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isWelcomeShown(){return pref.getBoolean(WELCOMESCREEN_PREF,false);}

    public void setWelcomeShown(){
        editor.putBoolean(WELCOMESCREEN_PREF,true);
        editor.apply();
    }

}

