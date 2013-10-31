package com.example.towerdefense;

import Levels.Level;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class TowerGame extends Activity {

	private static final String TAG = "TowerGame";
	private BoardView board;
	private TowerInfoView towerinfo;
	private TowerGameLogic mGame;
	private Level level;
	int numlevel;
	private boolean running = false;

	int boardwidth;
	int boardheight;


	//creates the activity. This sets the listeners for the 2 views and then sets the game for each of the views
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tower_game);
		//numlevel = savedInstanceState.getInt("level");

		mGame = new TowerGameLogic();
		board = (BoardView) findViewById(R.id.board);
		board.setGame(mGame);
		board.setOnTouchListener(mBoardListener);


		towerinfo = (TowerInfoView) findViewById(R.id.towerinfo);
		towerinfo.setGame(mGame);
		towerinfo.setOnTouchListener(mTowerListener);

				board.getViewTreeObserver().addOnGlobalLayoutListener( 
						new OnGlobalLayoutListener(){
							@Override
							public void onGlobalLayout() {
		
								boardheight = board.getHeight()/BoardView.BOARD_HEIGHT; 
								boardwidth = board.getWidth()/BoardView.BOARD_WIDTH;
								level = new Level(mGame, boardheight,boardwidth);
								board.setLevel(level);
								board.getViewTreeObserver().removeGlobalOnLayoutListener( this );
							}
						});	
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
			int col = (int) event.getX() / towerinfo.getCellWidth();
			//int row = (int) event.getY();

			switch (col) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				if(!running){
					running = true;
					board.update();
				}
			}
			return false;
		} 
	};

}
