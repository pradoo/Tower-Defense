package com.example.towerdefense;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TowerInfoView extends View {
	
	private Paint mPaint;
	private TowerGameLogic mGame;
	
	public TowerInfoView(Context context) {
		super(context);
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	
	public TowerInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);	       
		initialize();
	}
	
	public TowerInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		// TODO Auto-generated constructor stub
	}
	
	
	
	private void initialize() {
		// TODO Auto-generated method stub
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	public int getCellWidth() {
		return getWidth() / 3;
	}


	public int getCellHeight() {
		return getHeight() / 3;
	}
	
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
		int cellwidth = getWidth()/5;
		int viewheight = getHeight();
		//canvas.drawLine (cellwidth, 0, cellwidth, viewheight, mPaint);
		canvas.drawLine (cellwidth*4, 0, cellwidth*4, viewheight, mPaint);
		mPaint.setTextSize(100);
		canvas.drawText("Start", cellwidth*4 + cellwidth/5, viewheight - viewheight/3, mPaint);
				
	}


	public void setGame(TowerGameLogic m) {
		// TODO Auto-generated method stub
		mGame = m;
	}

	

}
