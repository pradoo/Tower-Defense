package com.example.towerdefense;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class LevelPickActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_pick);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_pick, menu);
		return true;
	}
	
	public void playlevel1(View v){
		Intent i = new Intent(this, TowerGame.class);
		//i.putExtra("level", 1);
		startActivity(i);
	}

}
