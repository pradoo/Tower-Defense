package com.example.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;

public class PowerTower extends AbsTower{
	
	private static final int gold = 200;
	
	public PowerTower(int x, int y, TowerGameLogic gameLogic) {
		super(x, y, gameLogic);
		range[0] = 150;
		range[1] = 150;
	}
	
	public static int cost(){
		return gold;
	}

	@Override
	public void draw(Canvas canvas) {
		mPaint.setColor(Color.MAGENTA);
		mPaint.setStrokeWidth(5);
		canvas.drawCircle(position[0], position[1], 50, mPaint);
	}

}
