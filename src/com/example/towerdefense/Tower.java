package com.example.towerdefense;

import java.util.ArrayList;

import android.graphics.Canvas;
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
	public Tower(int startingRange, int x, int y, TowerGameLogic gameLogic) {
		super(startingRange, x, y, gameLogic);
	}
	
	public static int cost(){
		return gold;
	}

	@Override
	public void draw(Canvas arg0) {
		// TODO Auto-generated method stub
		
	}

}
