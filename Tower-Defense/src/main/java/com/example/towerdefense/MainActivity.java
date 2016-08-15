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

public class MainActivity extends Activity implements OnClickListener{	
	private static final String LOG_TAG = "MainActivity_tag";
	private static int[] ids = {R.id.play};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(LOG_TAG,"onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View[] buttons = new View[ids.length];
		for(int i = 0; i < buttons.length; ++i){
			buttons[i] = findViewById(ids[i]);
			buttons[i].setOnClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d(LOG_TAG,"onCreateOptionsMenu()");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d(LOG_TAG,"onClick()");
		// TODO Auto-generated method stub
		Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
        vb.vibrate(50);
		switch (v.getId()) {
			case R.id.play:
				Intent i = new Intent(this, LevelPickActivity.class);
				startActivity(i);
				break;
		}
	}

}
