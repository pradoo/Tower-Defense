package com.example.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

public class Tower extends AbsTower{

	private static final int gold = 100;
	public static final int SEAL_BROWN = Color.rgb(50, 20, 20);
	public static final int STONE_GRAY = Color.rgb(139,141,122);
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
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(5);
		mPaint.setColor(STONE_GRAY);
		float cellHeight =  canvas.getHeight() / boardSurface.BOARD_HEIGHT;
		float cellWidth = canvas.getWidth() / boardSurface.BOARD_WIDTH;
		float radius = Math.min(cellHeight,cellWidth) / 3;
		RadialGradient rg;
		rg = new RadialGradient(position[0], position[1], radius, mPaint.getColor(), Color.LTGRAY, Shader.TileMode.CLAMP);		
		mPaint.setShader(rg);
		RectF base = new RectF(position[0] - radius, position[1] - radius,position[0] + radius, position[1] + radius);
		canvas.drawRoundRect(base,radius,radius,mPaint);
		mPaint.setColor(SEAL_BROWN);
		//decrease radius for top part of tower
		radius = (float) (radius*0.5);
		rg = new RadialGradient(position[0], position[1] - 4, radius, Color.YELLOW, mPaint.getColor(), Shader.TileMode.CLAMP);
		mPaint.setShader(rg);
		canvas.drawCircle(position[0], position[1], radius, mPaint);		
	}

}
