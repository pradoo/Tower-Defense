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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tower_game, menu);
		return true;
	}

	private OnTouchListener mBoardListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			// So we aren't notified of continued events when finger is moved
			return false;
		} 
	};

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
				mGame.level1(board.getBoardCellHeight(), board.getBoardCellWidth());
				board.update();

			}
			Log.d(TAG, "touch at : " + row);
			return false;
		} 
	};

}
