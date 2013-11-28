package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class EnemyCircle extends AbsEnemy{




	@SuppressWarnings("unchecked")
	public EnemyCircle(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g){
		super(x, y, r, i, g);
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
	}
	
	@Override
	public void draw(Canvas canvas) {
		bounds = new Rect(position[0]-radius, position[1]-radius,position[0]+radius, position[1]+radius);
		canvas.drawCircle(position[0], position[1], radius, mPaint);
		this.setBounds(bounds);
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}

}
