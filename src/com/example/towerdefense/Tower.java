package com.example.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;

public class Tower extends AbsTower{

	private static final int gold = 100;
	// TODO
	//PriorityQueue for enemies goes here
	/*
	 * Priority based on enemy health, unless we aren't allowing
	 * enemies to circle around to the beginning after reaching
	 * the end?
	 * 
	 */

	/**
	 * Constructor for the Towers. Make sure to send me the TowerGameLogic object.
	 * Otherwise, I won't know how to access the enemies :)
	 * @param gameLogic required to access the list of enemies
	 */
	public Tower(int x, int y, TowerGameLogic gameLogic) {
		super(x, y, gameLogic);
		range[0] = 300;
		range[1] = 300;
	}
	
	public static int cost(){
		return gold;
	}

	@Override
	public void draw(Canvas canvas) {
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(5);
		canvas.drawCircle(position[0], position[1], 30, mPaint);
		
	}

}
