package com.example.towerdefense;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	private static int[] ids = {R.id.new_game, R.id.keepgoing};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.new_game:
				Intent i = new Intent(this, TowerGame.class);
				startActivity(i);
				break;
			case R.id.keepgoing:
				Intent f = new Intent(this, TowerGame.class);
				startActivity(f);
				break;
		}
	}

}
