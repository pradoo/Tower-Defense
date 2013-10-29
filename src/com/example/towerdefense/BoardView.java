package com.example.towerdefense;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BoardView extends View{

	private TowerGameLogic mGame;
	private Paint mPaint;

	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 7;
	
	
	private static final String TAG = "BoardView";

	private static final int STOPPED = 0;
	private static final int RUNNING = 1;

	private long prevTime = System.currentTimeMillis();
	private long startTime = System.currentTimeMillis();
	private int frameCount;
	private int mode;
	
	private int moveDelay = 10;



	public BoardView(Context context) {
		super(context);
		initialize();	
		mode = RUNNING;
		//update();
		// TODO Auto-generated constructor stub
	}

	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
		mode = RUNNING;
		//update();
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		
		mode = RUNNING;
		//update();
	}

	public void initialize() {   
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	public void setGame(TowerGameLogic game) {
		mGame = game;
	}	


	public int getBoardCellWidth() {
		return getWidth() / BOARD_WIDTH;
	}


	public int getBoardCellHeight() {
		return getHeight() / BOARD_HEIGHT;
	}



	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
		drawPath(canvas);
		drawEnemies(canvas);

	}

	

	public void drawBoard(Canvas canvas){
		int boardWidth = getWidth();
		int boardHeight = getHeight();
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);

		int cellWidth = boardWidth / BOARD_WIDTH;
		int cellHeight = boardHeight / BOARD_HEIGHT;
		for(int i = 0; i < BOARD_WIDTH; ++i){
			canvas.drawLine(i * cellWidth, 0, i * cellWidth, boardHeight, mPaint);
		}

		for(int i = 0; i < BOARD_HEIGHT; ++i){
			canvas.drawLine(0, i * cellHeight, boardWidth, i * cellHeight, mPaint);
		}
	}
	
	private void drawPath(Canvas canvas) {
		
		
	}

	public void drawEnemies(Canvas canvas){
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
		ArrayList<EnemyCircle> enemies = mGame.getEnemies();
		for(int i = 0; i < enemies.size(); ++i){
			EnemyCircle temp = enemies.get(i);
			canvas.drawCircle(temp.getXpos(), temp.getYpos(), temp.getRadius(), mPaint);
		}
	}

	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {

		@Override
		public void handleMessage(Message msg) {
			BoardView.this.update();
			BoardView.this.invalidate();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	}

	public void update() {
		// TODO Auto-generated method stub

		if (mode == RUNNING) {
			//handleFrameRateChecks();
			long now = System.currentTimeMillis();
			if (now - prevTime > moveDelay) {
				prevTime = now;
				mGame.updateEnemies();
			}
			mRedrawHandler.sleep(moveDelay);
		}
	}

	private void handleFrameRateChecks() {
		long currTime = System.currentTimeMillis();
		// long diff = currTime - prevTime;
		// prevTime = currTime;
		// Log.d(TAG, "time diff: " + diff);

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
