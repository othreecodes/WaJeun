package com.othree.wajeun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;

import com.othree.wajeun.util.Preferences;
import com.stephentuso.welcome.WelcomeHelper;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;


public class SplashActivity extends AwesomeSplash {


        @Override
        public void initSplash(ConfigSplash configSplash) {

            //Customize Circular Reveal
            configSplash.setBackgroundColor(R.color.primary_dark); //any color you want form colors.xml
            configSplash.setAnimCircularRevealDuration(2000); //int ms
            configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
            configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP


            configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
            configSplash.setAnimLogoSplashDuration(2000); //int ms
            configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
            configSplash.setOriginalHeight(400);
            configSplash.setOriginalHeight(400);

            //Customize Title
            configSplash.setTitleSplash(" ");
            configSplash.setTitleTextColor(R.color.primary_text);
            configSplash.setTitleTextSize(30f); //float value
            configSplash.setAnimTitleDuration(0000);
            configSplash.setAnimTitleTechnique(Techniques.FadeInUp);

        }

        WelcomeHelper welcomeScreen;
        @Override
        public void animationsFinished() {

            if(new Preferences(this).isWelcomeShown()){
                //welcome screen already shown.... load main activity
                Intent in= new Intent(this,MainActivity.class);
                startActivity(in);
                finish();
            }else{
                WelcomeHelper welcomeHelper= new WelcomeHelper(this,WelcomeActivity.class);
                welcomeHelper.show(null);
            }


        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WelcomeHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
            // The key of the welcome screen is in the Intent
            //String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

            /*if (resultCode == RESULT_OK) {
                // Code here will run if the welcome screen was completed
            } else {
                // Code here will run if the welcome screen was canceled
                // In most cases you'll want to call finish() here
            }*/
            new Preferences(this).setWelcomeShown();
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
            finish();
        }

    }

}
