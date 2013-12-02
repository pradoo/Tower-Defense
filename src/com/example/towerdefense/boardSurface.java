package com.example.towerdefense;

import java.util.ArrayList;

import Levels.AbsLevel;
import Levels.Level;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Shader;
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
	public static final int BROWN = Color.rgb(150, 75, 0);
	public static final int RUDDY_BROWN = Color.rgb(187, 101, 40);
	public static final int BURNT_ORANGE = Color.rgb(207, 83, 0);
	public static final int Green = Color.rgb(32, 104, 3);
	public static final int BUFF = Color.rgb(240, 220, 130);

	private long prevTime = System.currentTimeMillis();
	private long startTime = System.currentTimeMillis();
	private int frameCount;
	private int i = 1;
	private TowerGameLogic mGame;
	private Paint mPaint;
	private AbsLevel level;
	

	ArrayList<AbsTower> towers;
	ArrayList<AbsEnemy> enemies;
	ArrayList<Bullet> bullets;
	Path path;
	int lastenemy = 0;
	int enemyDelay = 150/2;

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
			canvas.drawColor(Color.rgb(80, 208, 80));
			drawBoard(canvas);
			drawPath(canvas);
			drawTowers(canvas);
			drawEnemies(canvas);
			drawBullets(canvas);
			holder.unlockCanvasAndPost(canvas);
			mGame.updateBullets();

			//this is used so that the enemies dont start the moment that the thread starts up. This will be true once the use click start
			if(!firstrun){
				update();
				mGame.updateEnemies();
			}
			mGame.checkcollisions();
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
			if(level.addEnemey())
				enemyDelay = 150/level.lastSpeed();
			lastenemy = 0;
			mGame.setFirstRun(true);
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

	public void setLevel(AbsLevel l) {
		// TODO Auto-generated method stub
		level = l;
	}

	private void drawPath(Canvas canvas) {
		mPaint = new Paint();
		mPaint.setColor(BUFF); 
		path = level.getPath();
		canvas.drawPath(path, mPaint);
	}

	public void drawBoard(Canvas canvas){
//		LinearGradient lg;
//		lg = new LinearGradient(0,0,0,canvas.getHeight(),Green,Green,Shader.TileMode.MIRROR);
//		mPaint.setShader(lg);
//		canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);
	
		int boardWidth = canvas.getWidth();
		int boardHeight = canvas.getHeight();
		mPaint.setColor(Color.rgb(2, 74, 0));        
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
		//mPaint.setColor(Color.BLACK);        
		//mPaint.setStrokeWidth(5);		
		enemies = mGame.getEnemies();
		for(int i = 0; i < enemies.size(); ++i){
			AbsEnemy temp = enemies.get(i);
			temp.draw(canvas);
		}
	}

	/**
	 * This method will draw the towers and then find all enemies within each towers range and attack that tower
	 */
	private void drawTowers(Canvas canvas) {
		towers = mGame.getTowers();
		for(int i = 0; i < towers.size(); ++i){
			AbsTower temp = towers.get(i);
			temp.findEnemiesInRange();
			temp.attackEnemies();
			temp.draw(canvas);
		}

	}

	/**
	 * This method will draw the bullets will call each bullets onDraw method
	 */
	private void drawBullets(Canvas canvas) {
		bullets = mGame.getBullets();
		for(int i = 0; i < bullets.size(); ++i){
			bullets.get(i).draw(canvas);
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
