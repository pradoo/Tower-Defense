package com.example.towerdefense;

import java.util.ArrayList;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public abstract class AbsTower extends Drawable{

	// firerate means after so many seconds a bullet will fire. ex firerate of 2 means 1 bullet after 2 seconds
	protected double firerate = 1;
	protected int[] position;	
	protected TowerGameLogic logic;
	protected ArrayList<AbsEnemy> enemiesInRange;
	protected long lastime = 0;
	protected int[] range;
	protected Paint mPaint;
	
	
	public AbsTower(int x, int y, TowerGameLogic gameLogic) {
		mPaint = new Paint();
		range = new int[2];	
		position = new int[2];
		logic = gameLogic;
		enemiesInRange = new ArrayList<AbsEnemy>();
		//The following are subject to change as needed
		position[0] = x;
		position[1] = y;
	}

	public int[] getRange() {
		return range;
	}

	/**
	 * Used for upgrading the tower if we get to that point
	 * @param newX the new X range distance
	 * @param newY the new Y range distance
	 */
	public void setRange(int newX, int newY) {
		range[0] = newX;
		range[1] = newY;
	}

	public int getX() {
		return position[0];
	}
	
	public int getY() {
		return position[1];
	}

	/**
	 * Call this when the tower is being moved from it's previous position.
	 * @param x Tower's new X coordinate
	 * @param y Tower's new Y coordinate
	 */
	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}
	
	private boolean inRange(AbsEnemy e) {
		//Determine if the enemy is left or right of tower
		boolean inX = (e.getXpos() < position[0]) ?
				position[0] - range[0] <= e.getXpos() :
				position[0] + range[0] >= e.getXpos() ;

		//Determine if the enemy is above or below tower
		boolean inY = (e.getYpos() < position[1]) ?
				position[1] - range[1] <= e.getYpos() :
				position[1] + range[1] >= e.getYpos() ;

		return inX && inY;
	}
	
	
	/**
	 * This method finds all enemies within firing range of the tower.
	 */
	public void findEnemiesInRange() {
		enemiesInRange.clear();
		ArrayList<AbsEnemy> temp = logic.getEnemies();

		for(AbsEnemy e: temp)
			if(inRange(e))
				enemiesInRange.add(e);

	}


	/**
	 * This method will attack the enemies within the tower's firing range.
	 * This is done by using the EnemyCircle.setHealth() method.
	 */
	public void attackEnemies() {
		double time = System.currentTimeMillis();
		double diff = (time - lastime)/1000;
		AbsEnemy temp;
		//This only gets the first enemy if you do the one below then it will attack multiple enemies dont want this
		if(enemiesInRange.size() > 0 && diff >= firerate){
			temp = enemiesInRange.get(0);
			logic.addBullet(new Bullet(position[0],position[1], temp.getXpos(), temp.getYpos(), logic));
			lastime = System.currentTimeMillis();
		}
	}
	
	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter arg0) {
		// TODO Auto-generated method stub
		
	}

}
