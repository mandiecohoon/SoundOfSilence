package com.mandiecohoon.soundofsilence;

import java.util.ArrayList;
import java.util.Random;

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
import android.widget.TextView;

public class MainActivity extends Activity {
	// Import buttons
	private Button playButton;
	private TextView guessText;
	
	// Array of sounds MUST BE IN ORDER OF BUTTONS
	private int[] soundIDs = {
			R.raw.blop,
			R.raw.pindrop,
			R.raw.poolshoot,
			R.raw.shotgunreload,
			R.raw.slap,
			R.raw.cat_meow,
			R.raw.ship_bell,
			R.raw.turkey_gobble,
			R.raw.air_wrench,
			R.raw.teaspoon,
			R.raw.dove,
			R.raw.duck,
			R.raw.hawk,
			R.raw.mac_startup,
			R.raw.glass_break
	};
	private int numberOfSoundIDs = soundIDs.length;
	private int[] song = new int[10];
	
	// Sound buttons
	private Button button11;
	private Button button12;
	private Button button13;
	private Button button14;
	private Button button21;
	private Button button22;
	private Button button23;
	private Button button24;
	private Button button31;
	private Button button32;
	private Button button33;
	private Button button34;
	// Sounds
	private SoundPool soundPool;
	private SparseIntArray soundMap;
	
	// Answer
	private ArrayList<String> answer = new ArrayList();
	private ArrayList<String> guessList = new ArrayList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create song
		song = createSong(5);
		
		// Import UI
		playButton = (Button) findViewById(R.id.play);
		playButton.setOnClickListener(playButtonListener);
		
		guessText = (TextView) findViewById(R.id.guessText);
		
		// All the sound button choices
		//row 1
		button11 = (Button) findViewById(R.id.toggleButton_row1_1);
		button11.setOnClickListener(button11Listener);
		button12 = (Button) findViewById(R.id.toggleButton_row1_2);
		button12.setOnClickListener(button12Listener);
		button13 = (Button) findViewById(R.id.toggleButton_row1_3);
		button13.setOnClickListener(button13Listener);
		button14 = (Button) findViewById(R.id.toggleButton_row1_4);
		button14.setOnClickListener(button14Listener);
		//row 2
		button21 = (Button) findViewById(R.id.toggleButton_row2_1);
		button21.setOnClickListener(button21Listener);
		button22 = (Button) findViewById(R.id.toggleButton_row2_2);
		button22.setOnClickListener(button22Listener);
		button23 = (Button) findViewById(R.id.toggleButton_row2_3);
		button23.setOnClickListener(button23Listener);
		button24 = (Button) findViewById(R.id.toggleButton_row2_4);
		button24.setOnClickListener(button24Listener);
		//row 3
		button31 = (Button) findViewById(R.id.toggleButton_row3_1);
		button31.setOnClickListener(button31Listener);
		button32 = (Button) findViewById(R.id.toggleButton_row3_2);
		button32.setOnClickListener(button32Listener);
		button33 = (Button) findViewById(R.id.toggleButton_row3_3);
		button33.setOnClickListener(button33Listener);
		button34 = (Button) findViewById(R.id.toggleButton_row3_4);
		button34.setOnClickListener(button34Listener);
		
		 soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		 soundMap = new SparseIntArray(3);
		 for(int i = 0; i < 12/*numberOfSoundIDs*/; i++) {
			 soundMap.put(i, soundPool.load(getBaseContext(), soundIDs[i], 1));
		 }
	}
	
	 public OnClickListener playButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PlayMedia playAudio = new PlayMedia(getBaseContext(), song);
			playAudio.execute();
		}
	};
	
	// Sound button listeners
	// Row 1 sound buttons
	public OnClickListener button11Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(0), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_1));
			showCurrentGuess();
		}
	};
	public OnClickListener button12Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(1), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_2));
			showCurrentGuess();
		}
	};
	public OnClickListener button13Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(2), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_3));
			showCurrentGuess();
		}
	};
	public OnClickListener button14Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(3), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_4));
			showCurrentGuess();
		}
	};
	
	// Row 2 sound buttons
	public OnClickListener button21Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(4), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_1));
			showCurrentGuess();
		}
	};
	public OnClickListener button22Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(5), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_2));
			showCurrentGuess();
		}
	};
	public OnClickListener button23Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(6), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_3));
			showCurrentGuess();
		}
	};
	public OnClickListener button24Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(7), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_4));
			showCurrentGuess();
		}
	};
	
	// Row 3 sound buttons
	public OnClickListener button31Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(8), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_1));
			showCurrentGuess();
		}
	};
	public OnClickListener button32Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(9), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_2));
			showCurrentGuess();
		}
	};
	public OnClickListener button33Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(10), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_3));
			showCurrentGuess();
		}
	};
	public OnClickListener button34Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(11), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_4));
			showCurrentGuess();
		}
	};
	
	
	public int[] createSong(int numberOfSounds) {
		int[] soundList = new int[numberOfSounds + 1];
		Random rand = new Random();
		
		for(int i = 0; i <= numberOfSounds; i++) {
			soundList[i] = soundIDs[rand.nextInt(numberOfSoundIDs - 1)];
		}
		
		return soundList;
	}
	
	public void checkAnswer() {
		
	}
	
	public void showCurrentGuess() {
		String guessedText = "";
		for(String guess : guessList) {
			guessedText = guessedText + guess;
			guessedText = guessedText + ", ";
		}
		guessText.setText(guessedText);
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
