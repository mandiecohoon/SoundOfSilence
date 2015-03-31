package com.mandiecohoon.soundofsilence;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private static final int WAH_SOUND_ID = 0;
	private static final int TANOOKI_SOUND_ID = 1;
	private static final int YA_SOUND_ID = 2;
	private static final int WA_SOUND_ID = 3;
	private static final int BA_SOUND_ID = 4;
	private static final int JUMP_SOUND_ID = 5;
	private SoundPool soundPool;
	private SparseIntArray soundMap;
	private Button playButton;
	private int[] soundIDs = {R.raw.waluigi, R.raw.tanooki_mario, R.raw.yahoo, R.raw.wahoo, R.raw.badoo, R.raw.tanooki_jump};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		playButton = (Button) findViewById(R.id.play);
		playButton.setOnClickListener(playButtonListener);

	}
	
	 public OnClickListener playButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PlayMedia playAudio = new PlayMedia(getBaseContext(),soundIDs);
			playAudio.execute();
		}
	};
	
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
