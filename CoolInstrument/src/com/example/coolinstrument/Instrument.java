package com.example.coolinstrument;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class Instrument extends Activity {

	TextView b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15,
			b16;
	ArrayList<View> buttonList = new ArrayList<View>();
	ArrayList<Integer> soundList = new ArrayList<Integer>();
	ArrayList<MediaPlayer> mediaList = new ArrayList<MediaPlayer>();
	SoundPool sp;
	int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16;
	static int lock = 99;
	MediaPlayer mp1, mp2, mp3;
	private MediaPlayer mp;
	TextView test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.instrument);
		b1 = (TextView) findViewById(R.id.B1);
		b2 = (TextView) findViewById(R.id.B2);
		b3 = (TextView) findViewById(R.id.B3);
		b4 = (TextView) findViewById(R.id.B4);
		b5 = (TextView) findViewById(R.id.B5);
		b6 = (TextView) findViewById(R.id.B6);
		b7 = (TextView) findViewById(R.id.B7);
		b8 = (TextView) findViewById(R.id.B8);
		b9 = (TextView) findViewById(R.id.B9);
		b10 = (TextView) findViewById(R.id.B10);
		b11 = (TextView) findViewById(R.id.B11);
		b12 = (TextView) findViewById(R.id.B12);
		b13 = (TextView) findViewById(R.id.B13);
		b14 = (TextView) findViewById(R.id.B14);
		b15 = (TextView) findViewById(R.id.B15);
		b16 = (TextView) findViewById(R.id.B16);
		test = (TextView) findViewById(R.id.tvTest);

		buttonList.add(b1);
		buttonList.add(b2);
		buttonList.add(b3);
		buttonList.add(b4);
		buttonList.add(b5);
		buttonList.add(b6);
		buttonList.add(b7);
		buttonList.add(b8);
		buttonList.add(b9);
		buttonList.add(b10);
		buttonList.add(b11);
		buttonList.add(b12);
		buttonList.add(b13);
		buttonList.add(b14);
		buttonList.add(b15);
		buttonList.add(b16);
		for (int i = 0; i < buttonList.size(); i++) {
			View thisButton = buttonList.get(i);
			// thisButton.setOnClickListener(this);
			// thisButton.setOnTouchListener(this);
			thisButton.setSoundEffectsEnabled(false);
		}

		sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

		n1 = sp.load(this, R.raw.note1, 1);

		n2 = sp.load(this, R.raw.note2, 1);

		n3 = sp.load(this, R.raw.note3, 1);
		n4 = sp.load(this, R.raw.note4, 1);
		n5 = sp.load(this, R.raw.note5, 1);
		n6 = sp.load(this, R.raw.note6, 1);

		n7 = sp.load(this, R.raw.note7, 1);
		n8 = sp.load(this, R.raw.note8, 1);
		n9 = sp.load(this, R.raw.note9, 1);

		n10 = sp.load(this, R.raw.note10, 1);
		n11 = sp.load(this, R.raw.note11, 1);

		n12 = sp.load(this, R.raw.note12, 1);
		n13 = sp.load(this, R.raw.note13, 1);
		n14 = sp.load(this, R.raw.note14, 1);
		n15 = sp.load(this, R.raw.note15, 1);
		n16 = sp.load(this, R.raw.note16, 1);
		soundList.add(n1);

		soundList.add(n2);

		soundList.add(n3);
		soundList.add(n4);
		soundList.add(n5);
		soundList.add(n6);

		soundList.add(n7);
		soundList.add(n8);
		soundList.add(n9);

		soundList.add(n10);
		soundList.add(n11);
		soundList.add(n12);
		soundList.add(n13);
		soundList.add(n14);
		soundList.add(n15);
		soundList.add(n16);

	}

	// button onclick gesture

	// swiping gesture
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {

			float xVal = event.getX();
			float yVal = event.getY();

			int[] location = new int[2];
			for (int buttonCounter = 0; buttonCounter < buttonList.size(); buttonCounter++) {
				if (lock != buttonCounter) {
					View button = buttonList.get(buttonCounter);
					button.getLocationOnScreen(location);

					float rectX = location[0];
					float rectY = location[1];
					RectF buttonRect = new RectF(rectX, rectY, rectX
							+ button.getWidth(), rectY + button.getHeight());
					Boolean breakout = false;
					if (buttonRect.contains(xVal, yVal)) {
						sp.play(soundList.get(buttonCounter), 1, 1, 0, 0, 1);
						breakout = true;
					}
					if (breakout) {
						lock = buttonCounter;
						break;
					}
				}
			}

		}
		if (action == MotionEvent.ACTION_DOWN) {

			float xVal = event.getX();
			float yVal = event.getY();

			int[] location = new int[2];
			for (int buttonCounter = 0; buttonCounter < buttonList.size(); buttonCounter++) {
				View button = buttonList.get(buttonCounter);
				button.getLocationOnScreen(location);

				float rectX = location[0];
				float rectY = location[1];
				RectF buttonRect = new RectF(rectX, rectY, rectX
						+ button.getWidth(), rectY + button.getHeight());
				Boolean breakout = false;
				if (buttonRect.contains(xVal, yVal)) {
					sp.play(soundList.get(buttonCounter), 1, 1, 0, 0, 1);
					breakout = true;
				}
				if (breakout) {
					lock = buttonCounter;
					break;
				}
			}

		}
		return false;

	}
}
