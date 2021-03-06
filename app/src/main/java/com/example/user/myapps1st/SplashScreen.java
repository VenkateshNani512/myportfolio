package com.example.user.myapps1st;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {
     /* you don't have to override every property */

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.purple); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
       // configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(3000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(2); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.black); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(2000);
        configSplash.setPathSplashFillColor(R.color.fade); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("My Portfolio");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(25f); //float value
        configSplash.setAnimTitleDuration(2000);
        configSplash.setAnimTitleTechnique(Techniques.RotateIn);
        //configSplash.setTitleFont("fonts/diti_sweet.ttf"); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished() {
        Intent intent = new Intent(SplashScreen.this, LoginActivity1.class);
        startActivity(intent);
    }
}
