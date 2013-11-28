package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TankEnemy extends AbsEnemy{

	public TankEnemy(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g) {
		super(x, y, r, i, g, 0.90);
		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);        
		mPaint.setStrokeWidth(5);
		speed = 1;
		setHealth(1500);
		gold  = 50;
	}

	@Override
	public void draw(Canvas canvas) {
		bounds = new Rect(position[0]-radius/2, position[1]-radius/2,position[0]+radius/2, position[1]+radius/2);
		canvas.drawCircle(position[0], position[1], radius, mPaint);
		this.setBounds(bounds);
		
	}

	

}
