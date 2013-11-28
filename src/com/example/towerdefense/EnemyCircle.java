package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
public class EnemyCircle extends AbsEnemy{


	public EnemyCircle(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g){
		super(x, y, r, i, g, 0.75);
		mPaint = new Paint();

		speed = 2;
		setHealth(700);
		gold  = 25;
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
		int t = (int) (radius*0.75);
		bounds = new Rect(position[0]-t, position[1]-t,position[0]+t, position[1]+t);
		canvas.drawCircle(position[0], position[1], radius, mPaint);
		this.setBounds(bounds);
	}


}
