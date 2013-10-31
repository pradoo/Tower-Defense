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
		return getWidth() / 5;
	}


	public int getCellHeight() {
		return getHeight();
	}
	
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
		int cellwidth = getWidth()/5;
		int viewheight = getHeight();
		for(int i = 0; i < 5; ++ i){
			canvas.drawLine(cellwidth * i, 0, cellwidth * i, viewheight, mPaint);
		}
		//canvas.drawLine (cellwidth*4, 0, cellwidth*4, viewheight, mPaint);
		mPaint.setTextSize(cellwidth/4);
		canvas.drawText("Tower1", 0 + cellwidth/10, viewheight - viewheight/3, mPaint);
		canvas.drawText("Tower2", cellwidth + cellwidth/10, viewheight - viewheight/3, mPaint);
		canvas.drawText("Tower3", cellwidth*2 + cellwidth/10, viewheight - viewheight/3, mPaint);
		canvas.drawText("Tower4", cellwidth*3 + cellwidth/10, viewheight - viewheight/3, mPaint);
		canvas.drawText("Start", cellwidth*4 + cellwidth/6, viewheight - viewheight/3, mPaint);
				
	}


	public void setGame(TowerGameLogic m) {
		// TODO Auto-generated method stub
		mGame = m;
	}

	

}
