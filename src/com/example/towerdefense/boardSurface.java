package com.example.towerdefense;

import java.util.ArrayList;

import Levels.Level;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

	private TowerGameLogic mGame;
	private Paint mPaint;
	private Level level;
	
	ArrayList<Tower> towers;
	ArrayList<EnemyCircle> enemies;
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

	private void initialize() {
		holder = getHolder();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isItOk){
			if(!holder.getSurface().isValid())
				continue;
			Canvas canvas = holder.lockCanvas();
			canvas.drawColor(Color.GREEN);
			
			drawEnemies(canvas);
			drawTowers(canvas);
			drawBoard(canvas);
			holder.unlockCanvasAndPost(canvas);
			
			if(!firstrun){
				update();
				mGame.updateEnemies();
			}
		}
	}

	private void update() {
		if(mGame.levelOver()){
			Log.d(TAG, "Game Over");
			//pause();
		}
		++lastenemy;
		handleFrameRateChecks();
		if(lastenemy >= enemyDelay){
			level.addEnemey(); 
			lastenemy = 0;
		}
	}

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
	
	private void drawTowers(Canvas canvas) {
		mPaint.setColor(Color.BLUE);        
		mPaint.setStrokeWidth(5);
		towers = mGame.getTowers();
		for(int i = 0; i < towers.size(); ++i){
			Tower temp = towers.get(i);
			canvas.drawCircle(temp.getX(), temp.getY(), 20, mPaint);
		}
		
	}

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
