package com.example.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;

public class PowerTower extends AbsTower{
	
	private static final int gold = 200;
	
	public PowerTower(int x, int y, TowerGameLogic gameLogic) {
		super(x, y, gameLogic);
		range[0] = 150;
		range[1] = 150;
		firerate = 0.1;
	}
	
	public static int cost(){
		return gold;
	}

	@Override
	public void draw(Canvas canvas) {
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(5);
		float cellHeight =  canvas.getHeight() / boardSurface.BOARD_HEIGHT;
		float cellWidth = canvas.getWidth() / boardSurface.BOARD_WIDTH;
		float radius = Math.min(cellHeight,cellWidth) / 2;
		canvas.drawCircle(position[0], position[1], radius, mPaint);
	}

}
