package com.mandiecohoon.soundofsilence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SplashActivity extends Activity {
	
	private Button splashContinue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		splashContinue = (Button) findViewById(R.id.splashContinue);
		splashContinue.setOnClickListener(splashContinueListener);
        
	}
	
	 public OnClickListener splashContinueListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		};

}