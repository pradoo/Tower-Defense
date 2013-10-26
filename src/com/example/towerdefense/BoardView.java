package com.example.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View{

	private TowerGameLogic mGame;
	private Paint mPaint;

	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 7;

	public BoardView(Context context) {
		super(context);
		initialize();
		// TODO Auto-generated constructor stub
	}

	public BoardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public BoardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public void initialize() {   
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	public void setGame(TowerGameLogic game) {
		mGame = game;
	}	


	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBoard(canvas);
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
	
	public void drawEnemies(Canvas canvas){
		
	}

}
