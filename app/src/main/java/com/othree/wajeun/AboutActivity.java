package com.othree.wajeun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.thedazzler.droidicon.IconicFontDrawable;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element versionElement = new Element();
        versionElement.setTitle("Version 0.0.1");

        Element contributr = new Element();
        contributr.setTitle("Contribute to Chow");
        String url = "https://github.com/othreecodes/WaJeun";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        contributr.setIntent(i);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Chow is the android application that meets all your food needs. Find where to eat on campus," +
                        " find who to join food with and so much more.")
                .setCustomFont("fonts/drugs.otf")
                .setImage(R.drawable.chowtrans)
                .addGroup("About the developer")
                .addEmail("daviduchenna@outlook.com")
                .addWebsite("http://about.me/obiuchennadavid")
                .addFacebook("obiuchennadavid")
                .addTwitter("obi_is_a_boi")
                .addGitHub("othreecodes")
                .addInstagram("theboynamedme")
                .addItem(versionElement)
                .addItem(contributr)
                .create();

        setContentView(aboutPage);
    }

}
