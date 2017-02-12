package com.othree.wajeun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.TitlePage;
import com.stephentuso.welcome.WelcomeConfiguration;
import com.stephentuso.welcome.WelcomeHelper;

public class WelcomeActivity extends com.stephentuso.welcome.WelcomeActivity {
    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .defaultBackgroundColor(R.color.bckg)
                .page(new TitlePage(R.drawable.chowtrans,
                        "Welcome To Chow !")
                        .parallax(true)
                )
                .page(new BasicPage(R.drawable.ic_bell,
                        "Header",
                        "More text.")
                        .background(R.color.accent)
                        .parallax(true)
                )
                .page(new BasicPage(R.drawable.ic_analytics,
                        "Lorem ipsum",
                        "dolor sit amet.")
                        .parallax(true)

                ).page(new BasicPage(R.drawable.ic_street_view,
                        "Lorem ipsum",
                        "dolor sit amet.")
                        .parallax(true)
                        .background(R.color.accent)
                )

                .swipeToDismiss(true)
                .canSkip(false)
                .bottomLayout(WelcomeConfiguration.BottomLayout.STANDARD_DONE_IMAGE)
                .showPrevButton(true)
                .animateButtons(true)
                .showNextButton(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }

}
