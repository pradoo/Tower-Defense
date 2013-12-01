package com.example.towerdefense;

import java.util.HashMap;

import Levels.AbsLevel;
import Levels.Level;
import Levels.Level2;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

@SuppressLint("UseSparseArrays")
public class TowerGame extends Activity {

	private static final String LOG_TAG = "TowerGame_tag";
	private boardSurface board;
	private TowerInfoView towerinfo;
	private TowerGameLogic mGame;
	private AbsLevel level;
	int numlevel;
	private boolean running = false;
	private int tower;
	private int towercost;
	private int boardwidth;
	private int boardheight;
	private TextView gold;
	private TextView level_num;
	private TextView lives;
	private boolean soundOn = true;
	
	private SoundPool sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
	private HashMap<Integer, Integer> soundMap;
	
	//creates the activity. This sets the listeners for the 2 views and then sets the game for each of the views
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(LOG_TAG,"OnCreate");
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_tower_game);
		numlevel = getIntent().getExtras().getInt("level");
		
		soundMap = new HashMap<Integer, Integer>();
		int[] soundIds = {R.raw.bulletsound, R.raw.enemystart, R.raw.loselife, R.raw.lazer};
		for(int id : soundIds) 
			soundMap.put(id, sounds.load(this, id, 1));
		
		mGame = new TowerGameLogic();
		board = (boardSurface) findViewById(R.id.board);
		board.setGame(mGame);
		board.setOnTouchListener(mBoardListener);

		towerinfo = (TowerInfoView) findViewById(R.id.towerinfo);
		towerinfo.setGame(mGame);
		towerinfo.setOnTouchListener(mTowerListener);
		
		level_num = (TextView)findViewById(R.id.level_num);
		gold = (TextView)findViewById(R.id.gold);
		lives = (TextView)findViewById(R.id.lives);
		
		mGame.setAct(this);

		
		//This is used to get the board height and width as the board is getting drawn
		//if this is not used then the getwidth() and getheight() will always return 0 becasue the view hasnt been drawn yet
		board.getViewTreeObserver().addOnGlobalLayoutListener( 
				new OnGlobalLayoutListener(){
					@Override
					public void onGlobalLayout() {

						boardheight = board.getBoardCellHeight();
						boardwidth = board.getBoardCellWidth();					
						Bullet.boardheight = board.getHeight();
						Bullet.boardwidth = board.getWidth();
						board.getViewTreeObserver().removeGlobalOnLayoutListener( this );
						startnewgame();
					}
				});	
	}
	
	public void startnewgame(){
		running = false;
		
		board.setFirstRun(true);
		
		mGame.clear();
		if(numlevel == 1)
			level = new Level(mGame, boardheight,boardwidth);
		if(numlevel == 2)
			level = new Level2(mGame, boardheight,boardwidth);
		mGame.resetGold(level.getGold());		
		mGame.setLevel(level);
		level_num.setText("Level " + numlevel);						
		gold.setText("Gold: " + mGame.getGold());	
		board.setLevel(level);
		lives.setText("Lives: 20");
	}

	/**
	 * This calls the pause on the Thread that draws the animation
	 */
	@Override
	protected void onPause() {
		super.onPause();
		board.pause();
	}

	public void updateViews(){
		gold.setText("Gold: " + mGame.getGold());
	}
	
	/**
	 * This calls the resume on the Thread that draws the animation
	 */
	@Override
	protected void onResume() {
		super.onResume();
		board.resume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tower_game, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.new_game: 
			startnewgame();
			return true;
		case R.id.sound:
			soundOn = !soundOn;
			return true;
		}
		return false;
	}

	//This sets up a listener for the board view and then places a tower if the user has previously click on a tower
	private OnTouchListener mBoardListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {
			Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
	        vb.vibrate(50);
			if(tower != 0){
				int col = (int) event.getX() / board.getBoardCellWidth();
				int row = (int) event.getY() / board.getBoardCellHeight();
				if(mGame.checktower(row, col) && mGame.getGold() >= towercost){
					int x = col * board.getBoardCellWidth() + board.getBoardCellWidth()/2;
					int y = row * board.getBoardCellHeight() + board.getBoardCellHeight()/2;
					mGame.addToBoard(row, col, 2);
					if(tower == 1)
						mGame.addTower(new Tower(x, y, mGame));
					else if(tower == 2)
						mGame.addTower(new PowerTower(x, y, mGame));
					
					mGame.setGold(-towercost);
				}
				tower = 0;
			}
			return false;
		} 
	};


	//Listener for the second view this will see which box the user clicked on and then run if it was the bottom box.
	private OnTouchListener mTowerListener = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) { 
			Vibrator vb = (Vibrator)   getSystemService(Context.VIBRATOR_SERVICE);
	        vb.vibrate(50);
			int col = (int) event.getX() / towerinfo.getCellWidth();
			switch (col) {
			case 0:
				//this case means that the user wants to place the first tower on the board
				tower = 1;
				towercost = Tower.cost();
				break;
			case 1:
				tower = 2;
				towercost = PowerTower.cost();
				break;
			case 4:
				//this case is when the user presses the start button sets the game to be running so that multiple presses dont keep starting the game over and over
				if(!running){
					running = true;
					board.setFirstRun(false);
				}
				break;
			}
			return false;
		} 
	};
	
	public void decLives(int l){
		lives.setText("Lives: " + l);
	}
	
	public void playFromSoundPool(int id, float volume) {
		if(sounds != null && soundOn)
			sounds.play(soundMap.get(id), volume, volume, 1, 0, 1);
	}
	
	

}
