package com.dynamsoft.demo.dynamsoftbarcodereaderdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends Activity {
   ImageView image;
   TextView start,appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        image=findViewById(R.id.image);
        start=findViewById(R.id.start);
        appname=findViewById(R.id.appname);
        Typeface font = Typeface.createFromAsset(getAssets(), "b.ttf");
        start.setTypeface(font);
        appname.setTypeface(font);
        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        image.startAnimation(aniSlide);
        start.startAnimation(aniSlide);
        appname.startAnimation(aniSlide);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splash.this,FirstScreen.class));
                finish();
            }
        });



    }
}
