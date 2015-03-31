package com.mandiecohoon.soundofsilence;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button playButton;
	private int[] soundIDs = {R.raw.waluigi, R.raw.tanooki_mario, R.raw.yahoo, R.raw.wahoo, R.raw.badoo, R.raw.tanooki_jump};
	private int[] song = new int[10];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		song = createSong(3);
		
		playButton = (Button) findViewById(R.id.play);
		playButton.setOnClickListener(playButtonListener);
		
	}
	
	 public OnClickListener playButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PlayMedia playAudio = new PlayMedia(getBaseContext(), song);
			playAudio.execute();
		}
	};
	
	public int[] createSong(int numberOfSounds) {
		int[] soundList = new int[numberOfSounds];
		Random rand = new Random();
		
		for(int i = 0; i <= numberOfSounds; i++) {
			soundList[i] = soundIDs[rand.nextInt(5)];
		}
		
		return soundList;
	}
	
	public void checkAnswer() {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
