package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.SweepGradient;

public class TankEnemy extends AbsEnemy{

	public TankEnemy(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g) {
		super(x, y, r, i, g, 0.90);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		speed = 1;
		setHealth(2000);
		gold  = 50;
	}

	@Override
	public void draw(Canvas canvas) {
		//mPaint.setColor(Color.GREEN); 
		mPaint.setStrokeWidth(5);
		int[] colors = {Color.GREEN,Color.BLACK,Color.GREEN,Color.BLACK};
		float[] positions = {0.0f,0.25f,0.5f,1.0f};
		LinearGradient lg;
		lg = new LinearGradient(position[0], position[1],position[0] + 2*radius, position[1] + 2*radius,Color.BLACK,Color.GREEN,TileMode.MIRROR);
		mPaint.setShader(lg);
		bounds = new Rect(position[0]-radius, position[1]-radius,position[0]+radius, position[1]+radius);
		canvas.drawRect(bounds, mPaint);
		this.setBounds(bounds);
		
	}

	

}
