package com.dynamsoft.demo.dynamsoftbarcodereaderdemo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class FirstScreen extends AppCompatActivity {

    TextView about,youtube,scantext,showfile,exit;

    AdView mAdView;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


        MobileAds.initialize(this,
                getString(R.string.app_id));
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstital));
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        // Display Banner ad
        //
        mAdView.loadAd(adRequest);
        interstitialAd.loadAd(adRequest);
        Typeface font = Typeface.createFromAsset(getAssets(), "b.ttf");
        scantext=findViewById(R.id.voicechange);
        showfile=findViewById(R.id.showfile);
        youtube=findViewById(R.id.youtube);
        about=findViewById(R.id.about);
        exit=findViewById(R.id.exit);

    /*  voicechange.setTypeface(font);
      youtube.setTypeface(font);
      showfile.setTypeface(font);
      about.setTypeface(font);*/

        Animation aniSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        scantext.startAnimation(aniSlide);
        showfile.startAnimation(aniSlide);
        youtube.startAnimation(aniSlide);
        about.startAnimation(aniSlide);
        exit.startAnimation(aniSlide);

        showfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{ startActivity(new Intent(FirstScreen.this,ScanActivity.class));}
                catch (Exception e){
                    Toast.makeText(FirstScreen.this,"No sound file yet",0).show();
                }

            }
        });
//in my best friend
        scantext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            1);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant

                    return;
                }
                startActivity(new Intent(FirstScreen.this,MainActivity.class));
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/user/vikasch01";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstScreen.this,Aboutus.class));
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(FirstScreen.this);
                dialog.setCancelable(false);
                dialog.setTitle("Exit");
                dialog.setIcon(android.R.drawable.ic_dialog_alert);
                dialog.setMessage("Do you really want to exit from this application?"  );
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        dialog.cancel();
                        finish();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder dialog = new AlertDialog.Builder(FirstScreen.this);
            dialog.setCancelable(false);
            dialog.setTitle("Exit");
            dialog.setIcon(android.R.drawable.ic_dialog_alert);
            dialog.setMessage("Do you really want to exit from this application?"  );
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //Action for "Delete".
                    dialog.cancel();
                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();

                        interstitialAd.setAdListener(new AdListener(){

                            @Override
                            public void onAdClosed() {
                                super.onAdClosed();
                                finish();
                            }

                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();

                            }
                        });}
                    else
                        finish();
                }
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alert = dialog.create();
            alert.show();
        }

        return super.onKeyDown(keyCode, event);
    }
}
