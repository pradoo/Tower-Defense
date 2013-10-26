package com.example.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View{

	private TowerGameLogic mGame;
	private Paint mPaint;
	
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
	}

}
