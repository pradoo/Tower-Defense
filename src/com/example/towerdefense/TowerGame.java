package com.example.towerdefense;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TowerGame extends Activity {

	private static final String TAG = "TowerGame";
	private BoardView board;
	private TowerInfoView towerinfo;
	private TowerGameLogic mGame;
	private Level level;
	private boolean running = false;


	//creates the activity. This sets the listeners for the 2 views and then sets the game for each of the views
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tower_game);
		mGame = new TowerGameLogic();
		board = (BoardView) findViewById(R.id.board);
		board.setGame(mGame);
		board.setOnTouchListener(mBoardListener);
		
		
		towerinfo = (TowerInfoView) findViewById(R.id.towerinfo);
		towerinfo.setGame(mGame);
		towerinfo.setOnTouchListener(mTowerListener);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tower_game, menu);
		return true;
	}

	//listener for the board not used right now but eventually will be when towers get placed
	private OnTouchListener mBoardListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// So we aren't notified of continued events when finger is moved
			return false;
		} 
	};

	
	//Listener for the second view this will see which box the user clicked on and then run if it was the bottom box.
	private OnTouchListener mTowerListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) { 
			int col = (int) event.getX();
			int row = (int) event.getY() / towerinfo.getCellHeight();
			
			switch (row) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				if(!running){
					running = true;
					level = new Level(mGame,board.getBoardCellHeight(),board.getBoardCellWidth());
					board.setLevel(level);
					board.update();
				}
			}
			return false;
		} 
	};

}
