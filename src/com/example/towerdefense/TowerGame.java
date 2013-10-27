package com.example.towerdefense;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TowerGame extends Activity {
	
	private BoardView board;
	private TowerGameLogic mGame;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tower_game);
		mGame = new TowerGameLogic();
		board = (BoardView) findViewById(R.id.board);
		board.setGame(mGame);
		mGame.level1(board.getBoardCellHeight(), board.getBoardCellWidth());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tower_game, menu);
		return true;
	}

}
