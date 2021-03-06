package com.mandiecohoon.soundofsilence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.primitives.Ints;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	// Import buttons and text
	private static Button playButton;
	private Button clearGuess;
	private static TextView guessText;
	private static TextView debugger;
	private static TextView time;
	private static ProgressBar timeProgress;
	
	// Array of sounds MUST BE IN ORDER OF BUTTONS
	private static int[] soundIDs = {
			R.raw.blop,
			R.raw.pindrop,
			R.raw.shotgunreload,
			R.raw.poolshoot,
			R.raw.slap,
			R.raw.turkey_gobble,
			R.raw.ship_bell,
			R.raw.mac_startup,
			R.raw.duck,
			R.raw.teaspoon,
			R.raw.hawk,
			R.raw.air_wrench
			//R.raw.duck,
			//R.raw.dove,
			//R.raw.glass_break
	};
	private static int numberOfSoundIDs = soundIDs.length;
	private static int[] song = new int[12];
	
	// Sound buttons
	private static Button button11;
	private static Button button12;
	private static Button button13;
	private static Button button14;
	private static Button button21;
	private static Button button22;
	private static Button button23;
	private static Button button24;
	private static Button button31;
	private static Button button32;
	private static Button button33;
	private static Button button34;
	
	// Sounds
	private SoundPool soundPool;
	private SparseIntArray soundMap;
	
	// Answer
	private static int[] answer = new int[12];
	private static int[] guessAnswer = new int[12];
	private static ArrayList<String> guessList = new ArrayList();
	static int guessIndex = 0;
	
	// Get context for alert dialog
	private static Context context;
	
	// Difficulty & score
	private static int difficulty = 2;
	private static int highScoreList[] = new int[6];
	private static int score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		
		// Import UI before creating song
		time = (TextView) findViewById(R.id.songProgressTime);
		debugger = (TextView) findViewById(R.id.debugger);
		
		// Create song
		song = createSong(difficulty);
				
		// Import UI after creating song
		playButton = (Button) findViewById(R.id.play);
		playButton.setOnClickListener(playButtonListener);
		clearGuess = (Button) findViewById(R.id.clearGuess);
		clearGuess.setOnClickListener(clearGuessListener);
		guessText = (TextView) findViewById(R.id.guessText);
		timeProgress = (ProgressBar) findViewById(R.id.songProgress);
		
		// Clear data
		//clearGuess();
		
		// All the sound button choices
		//row 1
		button11 = (Button) findViewById(R.id.button_row1_1);
		button11.setOnClickListener(button11Listener);
		button12 = (Button) findViewById(R.id.button_row1_2);
		button12.setOnClickListener(button12Listener);
		button13 = (Button) findViewById(R.id.button_row1_3);
		button13.setOnClickListener(button13Listener);
		button14 = (Button) findViewById(R.id.button_row1_4);
		button14.setOnClickListener(button14Listener);
		//row 2
		button21 = (Button) findViewById(R.id.button_row2_1);
		button21.setOnClickListener(button21Listener);
		button22 = (Button) findViewById(R.id.button_row2_2);
		button22.setOnClickListener(button22Listener);
		button23 = (Button) findViewById(R.id.button_row2_3);
		button23.setOnClickListener(button23Listener);
		button24 = (Button) findViewById(R.id.button_row2_4);
		button24.setOnClickListener(button24Listener);
		//row 3
		button31 = (Button) findViewById(R.id.button_row3_1);
		button31.setOnClickListener(button31Listener);
		button32 = (Button) findViewById(R.id.button_row3_2);
		button32.setOnClickListener(button32Listener);
		button33 = (Button) findViewById(R.id.button_row3_3);
		button33.setOnClickListener(button33Listener);
		button34 = (Button) findViewById(R.id.button_row3_4);
		button34.setOnClickListener(button34Listener);
		
		// sounds for when each sound button is pressed
		 soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		 soundMap = new SparseIntArray(3);
		 for(int i = 0; i < 12/*numberOfSoundIDs*/; i++) {
			 soundMap.put(i, soundPool.load(getBaseContext(), soundIDs[i], 1));
		 }
	}
	
	// Play button
	 public OnClickListener playButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clearGuess();
			disableButtons();
			PlayMedia playAudio = new PlayMedia(getBaseContext(), song);
			playAudio.execute();
			guessText.setText("Which sounds do you hear? You have a minute to guess!");
		}
	};
	
	// Try Again button
	public OnClickListener clearGuessListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clearGuess();
		}
	};
	
	// Sound button listeners
	// Row 1 sound buttons
	public OnClickListener button11Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(0), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_1));
			showCurrentGuess(1);
		}
	};
	public OnClickListener button12Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(1), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_2));
			showCurrentGuess(2);
		}
	};
	public OnClickListener button13Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(2), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_3));
			showCurrentGuess(3);
		}
	};
	public OnClickListener button14Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(3), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row1_4));
			showCurrentGuess(4);
		}
	};
	
	// Row 2 sound buttons
	public OnClickListener button21Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(4), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_1));
			showCurrentGuess(5);
		}
	};
	public OnClickListener button22Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(5), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_2));
			showCurrentGuess(6);
		}
	};
	public OnClickListener button23Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(6), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_3));
			showCurrentGuess(7);
		}
	};
	public OnClickListener button24Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(7), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row2_4));
			showCurrentGuess(8);
		}
	};
	
	// Row 3 sound buttons
	public OnClickListener button31Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(8), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_1));
			showCurrentGuess(9);
		}
	};
	public OnClickListener button32Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(9), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_2));
			showCurrentGuess(10);
		}
	};
	public OnClickListener button33Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(10), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_3));
			showCurrentGuess(11);
		}
	};
	public OnClickListener button34Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			soundPool.play(soundMap.get(11), 1, 1, 1, 0, 1f);
			guessList.add(getResources().getString(R.string.toggle_row3_4));
			showCurrentGuess(12);
		}
	};
	
	// create the song from a set number of sounds
	public static int[] createSong(int numberOfSounds) {
		int[] soundList = new int[numberOfSounds + 1];
		Random random = new Random();
		String debugAnswer = "";
		
		// randomizes the sounds in the song
		for(int i = 0; i <= numberOfSounds; i++) {
			int randomNumber = random.nextInt(numberOfSoundIDs);
			soundList[i] = soundIDs[randomNumber];
			answer[i] = randomNumber + 1;
			debugAnswer = debugAnswer + " " + String.valueOf(answer[i]);
		}
		//debugger.setText(debugAnswer);
		Log.i("Answer", debugAnswer);
		return soundList;
	}
	
	public static boolean checkAnswer() {
		boolean flag = true;
		
		// check if answer is fully correct - if yes: starts new level, if no: keeps going
		for(int i = 0; i < answer.length; i++) {
			if(answer[i] == guessAnswer[i]) {
				// Answer is correct
				Log.i("cAnswer", String.valueOf(guessAnswer[i]) + ", " + String.valueOf(answer[i]));
			} else {
				// An incorrect guess
				Log.i("fAnswer", String.valueOf(guessAnswer[i]) + ", " + String.valueOf(answer[i]));
				flag = false;
			}
		}
		// Winning game
		if(flag) {
			//debugger.setText("Winner");
			Log.i("Answer", "Winner");
			disableButtons();
			score += 5 * (1 + timeProgress.getProgress());
			// dialog to move to next level
			new AlertDialog.Builder(context)
			.setTitle("Correct!")
			.setMessage("Move on to the next level?")
			.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
	       			@Override
	       			public void onClick(DialogInterface arg0, int arg1) {
	       				addLevel();
	       				enableButtons();
	       				stopTimer();
	       				timeProgress.setProgress(60);
	       			}
	        	})
	       .create()
	       .show();
		}
		
		return flag;
	}
	
	public void showCurrentGuess(int soundGuessed) {
		String guessedText = "";
		guessAnswer[guessIndex] = soundGuessed;
		guessIndex++;
		if(guessIndex >= 12) {
			// catch for guesses to not go out of bounds
			clearGuess();
		}
		// for ever guess in the guess list, add it to the string
		for(String guess : guessList) {
			guessedText = guessedText + guess;
			guessedText = guessedText + ", ";
		}
		guessText.setText(guessedText);
		checkAnswer();
		Log.i("Guessed", guessedText);
		//debugger.setText(String.valueOf(score));
	}
	
	public static void addLevel() {
		// highest difficulty
		if(difficulty == 11) {
			new AlertDialog.Builder(context)
			.setTitle("WOW!")
			.setMessage("You've won the game on the highest difficulty! You must be a master of memory! ...or named Derrian")
			.setPositiveButton("Oh I am!", new DialogInterface.OnClickListener() {
	       			@Override
	       			public void onClick(DialogInterface arg0, int arg1) {
	       				checkHighScore(score);
	       				playButton.setEnabled(true);
	       				gameOver();
	       			}
	        	})
	       .create()
	       .show();
		} else {
			// increase level difficulty
			difficulty++;
			playButton.setEnabled(true);
			clearGuess();
			song = createSong(difficulty);
		}
	}
	
	public static void gameOver() {
		difficulty = 2;
		score = 0;
		answer = new int[12];
		guessAnswer = new int[12];
		guessList = new ArrayList();
		guessIndex = 0;
		guessText.setText("");
		song = createSong(difficulty);
	}
	
	public static void clearGuess() {
		guessAnswer = new int[12];
		guessList = new ArrayList();
		guessIndex = 0;
		guessText.setText("");
	}
	
	public static void startTimer() {
		countDownTimer.start();
	}
	
	public static void stopTimer() {
		countDownTimer.cancel();
		time.setText("1:00");
	}
	
	// Timer
	private static CountDownTimer countDownTimer = new CountDownTimer(61000, 10) { //61000 to 10 is default
	     public void onTick(long millisUntilFinished) {
	        time.setText(""+ millisUntilFinished / 1000);
	        timeProgress.setProgress((int) (millisUntilFinished / 1000));
	     }
	     public void onFinish() {
	    	 // Timer ran out of time
    		 time.setText("Out of time!");
    		 disableButtons();
    		 checkHighScore(score);
    		 // dialog to start new game
    		 new AlertDialog.Builder(context)
    		 .setTitle("Game Over")
    		 .setMessage("New Game?")
    		 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						time.setText("Start New Game \n Your score was: " + score);
							enableButtons();
							playButton.setEnabled(true);
			   				gameOver();
			   			}
			    	})
			 .create()
			 .show();
	     }
	 };
	 
	public static void disableButtons() {
		button11.setEnabled(false);
		button12.setEnabled(false);
		button13.setEnabled(false);
		button14.setEnabled(false);
		button21.setEnabled(false);
		button22.setEnabled(false);
		button23.setEnabled(false);
		button24.setEnabled(false);
		button31.setEnabled(false);
		button32.setEnabled(false);
		button33.setEnabled(false);
		button34.setEnabled(false);
		playButton.setEnabled(false);
	}
	
	public static void enableButtons() {
		button11.setEnabled(true);
		button12.setEnabled(true);
		button13.setEnabled(true);
		button14.setEnabled(true);
		button21.setEnabled(true);
		button22.setEnabled(true);
		button23.setEnabled(true);
		button24.setEnabled(true);
		button31.setEnabled(true);
		button32.setEnabled(true);
		button33.setEnabled(true);
		button34.setEnabled(true);
	}
	
	// Check if score ranks top
	private static void checkHighScore(int currentScore) {
		SharedPreferences savedHighscores = context.getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
		SharedPreferences.Editor preferencesEditor = savedHighscores.edit();
		
		// load previous high scores
		for (int i = 0; i < 5; i++) {
			highScoreList[i] = savedHighscores.getInt("highScore" + String.valueOf(i), 0);
		}
	   
		// insert current score and sort list
		highScoreList[5] = currentScore;
	    List<Integer>list = Ints.asList(highScoreList);
	    Collections.sort(list, comparator);
	    highScoreList = Ints.toArray(list);
	   
	    // re-orders high scores, dropping the last one
	    for (int i = 0; i < 5; i++) {
	    	preferencesEditor.putInt("highScore" + String.valueOf(i), highScoreList[i]);
	    }
	    
	    preferencesEditor.apply(); 
	}
	
	// List comparator
	static Comparator<Integer> comparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	};
	
	// Shows high score list in a dialog
	private static void showHighScores() {
		// get scores from shared pref
		SharedPreferences savedHighscores = context.getSharedPreferences("highScorePref", Context.MODE_PRIVATE);
		String score1 = "" + savedHighscores.getInt("highScore0", 0);
        String score2 = "" + savedHighscores.getInt("highScore1", 0);
        String score3 = "" + savedHighscores.getInt("highScore2", 0);
        String score4 = "" + savedHighscores.getInt("highScore3", 0);
        String score5 = "" + savedHighscores.getInt("highScore4", 0);
        
        // create dialog with scores
        AlertDialog.Builder highScoreDialog = new AlertDialog.Builder(context);
        highScoreDialog.setTitle("High Scores");
        highScoreDialog.setMessage("1. " 
	               + score1
	               + "\n2. "
	               + score2
	               + "\n3. "
	               + score3
	               + "\n4. "
	               + score4
	               + "\n5. "
	               + score5
        	);
        highScoreDialog.setPositiveButton("Okay", null);
        highScoreDialog.create();
        highScoreDialog.show();
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
		
		// If high score menu item is clicked it shows dialog
		switch (id) {
			case R.id.highScores:
				showHighScores();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
