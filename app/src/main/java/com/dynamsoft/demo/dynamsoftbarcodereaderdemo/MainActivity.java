package com.dynamsoft.demo.dynamsoftbarcodereaderdemo;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

	    public static EditText tvresult;
       Button share,copy;

	AdView mAdView;
	InterstitialAd interstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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
		tvresult =  findViewById(R.id.tvresult);
		share =  findViewById(R.id.share);
		copy =  findViewById(R.id.copy);

		Button btn = (Button) findViewById(R.id.btn);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ScanActivity.class);
				startActivity(intent);
			}
		});

		share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(getActivity(), Main2Activity.jokes.get(viewPager.getCurrentItem()), Toast.LENGTH_SHORT).show();
				String shareBody =tvresult.getText().toString();
				Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
				sharingIntent.setType("text/plain");
				sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
				startActivity(sharingIntent);
			}
		});

		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String shareBody =tvresult.getText().toString();
				ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				ClipData clip = ClipData.newPlainText("label", shareBody);
				clipboard.setPrimaryClip(clip);
				Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();

			}
		});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==event.KEYCODE_BACK){
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


		return super.onKeyDown(keyCode, event);
	}



}