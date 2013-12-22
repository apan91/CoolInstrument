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

public class Instrument extends Activity implements OnTouchListener {

	Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12;
	ArrayList<Button> buttonList = new ArrayList<Button>();
	ArrayList<Integer> soundList = new ArrayList<Integer>();
	ArrayList<MediaPlayer> mediaList = new ArrayList<MediaPlayer>();
	SoundPool sp;
	int n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12 = 0;
	static int lock = 99;
	MediaPlayer mp1, mp2, mp3;
	private MediaPlayer mp;
	TextView test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.instrument);
		b1 = (Button) findViewById(R.id.B1);
		b2 = (Button) findViewById(R.id.B2);
		b3 = (Button) findViewById(R.id.B3);
		b4 = (Button) findViewById(R.id.B4);
		b5 = (Button) findViewById(R.id.B5);
		b6 = (Button) findViewById(R.id.B6);
		b7 = (Button) findViewById(R.id.B7);
		b8 = (Button) findViewById(R.id.B8);
		b9 = (Button) findViewById(R.id.B9);
		b10 = (Button) findViewById(R.id.B10);
		b11 = (Button) findViewById(R.id.B11);
		b12 = (Button) findViewById(R.id.B12);
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
		for (int i = 0; i < buttonList.size(); i++) {
			Button thisButton = buttonList.get(i);
			// thisButton.setOnClickListener(this);
			thisButton.setOnTouchListener(this);
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

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		test.setText("" + event.getActionMasked());
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int i = buttonList.indexOf(v);
			sp.play(soundList.get(i), 1, 1, 0, 0, 1);
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int action=event.getAction();
		if (action==MotionEvent.ACTION_MOVE){
			
			float xVal=event.getX();
			float yVal=event.getY();

			
			int[] location = new int[2];
			for (int buttonCounter=0;buttonCounter<buttonList.size();buttonCounter++){
				if (lock!=buttonCounter){
				View button=buttonList.get(buttonCounter);
						button.getLocationOnScreen(location);
				
				float rectX=location[0];
				float rectY=location[1];
				RectF buttonRect=new RectF(rectX,rectY,rectX+button.getWidth(),rectY+button.getHeight());
				Boolean breakout=false;
				if (buttonRect.contains(xVal, yVal)){
					sp.play(soundList.get(buttonCounter), 1, 1, 0, 0, 1);
					breakout=true;
				}
				if (breakout){
					lock=buttonCounter;
					break;
				}
				}
			}
			
			
		}
		return false;
		
	
	}
}
