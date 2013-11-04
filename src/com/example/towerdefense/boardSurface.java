package com.example.towerdefense;

import java.util.ArrayList;

import Levels.Level;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class boardSurface extends SurfaceView implements Runnable{

	Thread thread = null;
	SurfaceHolder holder;
	boolean isItOk = false;
	boolean firstrun = true;

	private static final String TAG = "BoardView";
	public static final int BOARD_WIDTH = 14;
	public static final int BOARD_HEIGHT = 8;


	private long prevTime = System.currentTimeMillis();
	private long startTime = System.currentTimeMillis();
	private int frameCount;
	private int i = 1;
	private TowerGameLogic mGame;
	private Paint mPaint;
	private Level level;
	
	ArrayList<Tower> towers;
	ArrayList<EnemyCircle> enemies;
	Path path;
	int lastenemy = 0;
	int enemyDelay = 75;

	public boardSurface(Context context) {
		super(context);
		initialize();

	}

	public boardSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public boardSurface(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();	
	}

	/**
	 * gets the holder for the surface and then initializes the paint object
	 */
	private void initialize() {
		holder = getHolder();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	/**
	 * This method runs the thread that will do all of the drawing and animation
	 */
	@Override
	public void run() {
		// as long as the surface is ok to draw on then we will keep drawing over and over
		while(isItOk){
			if(!holder.getSurface().isValid())
				continue;
			
			//this locks the canvas so that no other thread can draw to it execpt for this one
			Canvas canvas = holder.lockCanvas();
			canvas.drawColor(Color.GREEN);
		
			drawPath(canvas);
			drawBoard(canvas);
			drawTowers(canvas);
			drawEnemies(canvas);
			holder.unlockCanvasAndPost(canvas);
			
			//this is used so that the enemies dont start the moment that the thread starts up. This will be true once the use click start
			if(!firstrun){
				update();
				mGame.updateEnemies();
			}
		}
	}

	/**
	 * This method will check if the game is over and will add enemies to the board after so many frames
	 */
	private void update() {
		if(mGame.levelOver()){
			Log.d(TAG, "Game Over");

		}
		++lastenemy;
		handleFrameRateChecks();
		if(lastenemy >= enemyDelay && !mGame.levelOver()){
			level.addEnemey(); 
			lastenemy = 0;
		}
	}

	/**
	 * This method pauses the thread so that it wont keep running after the user navigates away from the app
	 */
	public void pause(){
		if(thread != null) {
			isItOk = false;
			while(true){
				try{
					thread.join();
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				break;
			}
			thread = null;
		}
	}

	/**
	 * This method resumes the thread to draw the animations when the app is started up or restarted up
	 */
	public void resume(){
		isItOk = true;
		thread = new Thread(this);
		thread.start();
	}

	public void setFirstRun(boolean t){
		firstrun = t;
	}

	public void setGame(TowerGameLogic mGame) {
		this.mGame = mGame;
	}

	public int getBoardCellWidth() {
		return getWidth() / BOARD_WIDTH;
	}


	public int getBoardCellHeight() {
		return getHeight() / BOARD_HEIGHT;
	}

	public void setLevel(Level level1) {
		// TODO Auto-generated method stub
		level = level1;
	}
	
	private void drawPath(Canvas canvas) {
		mPaint.setColor(Color.GRAY);  
		path = level.getPath();
		canvas.drawPath(path, mPaint);
	}

	public void drawBoard(Canvas canvas){
		int boardWidth = canvas.getWidth();
		int boardHeight = canvas.getHeight();
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);

		int cellWidth = (boardWidth / BOARD_WIDTH);
		int cellHeight = boardHeight / BOARD_HEIGHT;
		for(int i = 0; i < BOARD_WIDTH; ++i){
			canvas.drawLine(i * cellWidth, 0, i * cellWidth, boardHeight, mPaint);
		}

		for(int i = 0; i <= BOARD_HEIGHT; ++i){
			canvas.drawLine(0, i * cellHeight, boardWidth, i * cellHeight, mPaint);
		}
	}

	public void drawEnemies(Canvas canvas){
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
		enemies = mGame.getEnemies();
		for(int i = 0; i < enemies.size(); ++i){
			EnemyCircle temp = enemies.get(i);
			canvas.drawCircle(temp.getXpos(), temp.getYpos(), temp.getRadius(), mPaint);
		}
	}
	
	/**
	 * This method will draw the towers and then find all enemies within each towers range and attack that tower
	 */
	private void drawTowers(Canvas canvas) {
		mPaint.setColor(Color.BLUE);        
		mPaint.setStrokeWidth(5);
		towers = mGame.getTowers();
		for(int i = 0; i < towers.size(); ++i){
			Tower temp = towers.get(i);
			temp.findEnemiesInRange();
			temp.attackEnemies();
			canvas.drawCircle(temp.getX(), temp.getY(), 20, mPaint);
		}
		
	}

	/**
	 * This is used to just compute how many frames per second the app is getting
	 */
	private void handleFrameRateChecks() {
		long currTime = System.currentTimeMillis();
		if(frameCount < 30) {
			frameCount++;
		}
		else {
			frameCount = 0;
			long timeDiff = currTime - startTime;
			startTime = currTime;
			double frameRate = 1000.0 / (timeDiff / 30.0) ;
			Log.d(TAG, "frame rate: " + (int) frameRate);
			Log.d(TAG, "timediff: " + timeDiff);
		}
	}


}
