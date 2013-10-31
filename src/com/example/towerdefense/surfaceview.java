package com.example.towerdefense;

import java.util.ArrayList;

import Levels.Level;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class surfaceview extends SurfaceView implements SurfaceHolder.Callback{

	
	
	private TowerGameLogic mGame;
	private Level level;

	public static final int BOARD_WIDTH = 14;
	public static final int BOARD_HEIGHT = 8;
	
	private static final int STOPPED = 0;
	private static final int RUNNING = 1;
	
	private int mode;

	private int moveDelay = 10;
	private int enemyDelay = 75;
	private int lastenemy = 0;
	
	private StaticThread thread;
	private int frameCount;
	private long prevTime = System.currentTimeMillis();
	private long startTime = System.currentTimeMillis();
	private static final String TAG = "Static";
	
	public surfaceview(Context context) {
		super(context);
		initalize();

	}

	public surfaceview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initalize();
	}

	public surfaceview(Context context, AttributeSet attrs) {
		super(context, attrs);
		initalize();
	}
	private void initalize() {
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		thread = new StaticThread(holder);
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		thread.setRunning(false);
		while(retry){
			try{
				thread.join();
				Log.d(TAG, "Thread stopped");
				retry = false;
			}
			catch (InterruptedException e){
				
			}
		}
		
	}

	private class StaticThread extends Thread{
		private boolean running;
		private SurfaceHolder surfaceholder;
		private Bitmap image;
		private Paint paint;
		
		public StaticThread(SurfaceHolder sh){
			surfaceholder = sh;
			paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(6);
		}
		
		public void setRunning(boolean status){
			running = status;
		}
		
		@Override
		public void run(){
			while(running){
				if(surfaceholder.getSurface().isValid()){
					handleFrameRateChecks();
					Canvas canvas = surfaceholder.lockCanvas();
					doDraw(canvas);
					surfaceholder.unlockCanvasAndPost(canvas);
				}
			}
		}
		

		public void doDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			drawBoard(canvas);
			drawPath(canvas);
			drawEnemies(canvas);
		}
		
		public void drawBoard(Canvas canvas){
			int boardWidth = canvas.getWidth();
			int boardHeight = canvas.getHeight();
			paint.setColor(Color.BLACK);        
			paint.setStrokeWidth(5);

			int cellWidth = (boardWidth / BOARD_WIDTH);
			;
			int cellHeight = boardHeight / BOARD_HEIGHT;
			for(int i = 0; i < BOARD_WIDTH; ++i){
				canvas.drawLine(i * cellWidth, 0, i * cellWidth, boardHeight, paint);
			}

			for(int i = 0; i <= BOARD_HEIGHT; ++i){
				canvas.drawLine(0, i * cellHeight, boardWidth, i * cellHeight, paint);
			}
		}

		private void drawPath(Canvas canvas) {


		}

		public void drawEnemies(Canvas canvas){
			paint.setColor(Color.BLACK);        
			paint.setStrokeWidth(5);
			ArrayList<EnemyCircle> enemies = mGame.getEnemies();
			for(int i = 0; i < enemies.size(); ++i){
				EnemyCircle temp = enemies.get(i);
				canvas.drawCircle(temp.getXpos(), temp.getYpos(), temp.getRadius(), paint);
			}
		}

		
		
		
		
	}

	public void handleFrameRateChecks() {
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

	public void setGame(TowerGameLogic m) {
		// TODO Auto-generated method stub
		mGame = m;
		
	}

	public void setLevel(Level l) {
		// TODO Auto-generated method stub
		level = l;
	}

}
