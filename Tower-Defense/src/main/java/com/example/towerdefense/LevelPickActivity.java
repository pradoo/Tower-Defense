package com.example.towerdefense;

import android.os.Bundle;
import android.os.Vibrator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;


public class LevelPickActivity extends Activity implements OnClickListener{
	private static final String LOG_TAG = "LevelPickActivity_tag";	
	private static int[] ids = {R.id.level1, R.id.level2, R.id.level3};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG,"onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_pick);

		View[] buttons = new View[ids.length];
		for(int i = 0; i < buttons.length; ++i){
			buttons[i] = findViewById(ids[i]);
			buttons[i].setOnClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.i(LOG_TAG,"onCreateOptionsMenu()");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_pick, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d(LOG_TAG,"onClick()");
		Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
		vb.vibrate(50);
		Intent i;
		switch (v.getId()) {	
		case R.id.level1:
			i = new Intent(this, TowerGame.class);
			i.putExtra("level", 1);
			startActivity(i);
			break;
		case R.id.level2:
			i = new Intent(this, TowerGame.class);
			i.putExtra("level", 2);
			startActivity(i);
			break;
		case R.id.level3:
			i = new Intent(this, TowerGame.class);
			i.putExtra("level", 3);
			startActivity(i);
			break;
		}
	}

	/*
	public void playlevel1(View v){
		Log.d(this.LOG_TAG, "playlevel1()");
		Intent i = new Intent(this, TowerGame.class);
		//i.putExtra("level", 1);
		startActivity(i);
	}
	 */

}
