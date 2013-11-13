package com.example.towerdefense;

import java.util.ArrayList;

public class Tower {

	private int[] range;
	//Range can be flattened into a single int
	//IF we want xRange == yRange

	private int firerate;
	private int[] position;
	private int damage;
	private TowerGameLogic logic;
	private static int gold = 100;
	private ArrayList<EnemyCircle> enemiesInRange;

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
		range = new int[2];	
		position = new int[2];
		logic = gameLogic;
		enemiesInRange = new ArrayList<EnemyCircle>();
		//The following are subject to change as needed
		damage = 2;
		range[0] = startingRange;
		range[1] = startingRange;
		position[0] = x;
		position[1] = y;
	}
	
	public static int cost(){
		return gold;
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

	/**
	 * Returns how much damage the tower will inflict on the enemy
	 * @return The amount of damage the tower will do
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Sets a new damage amount for the tower
	 * @param newDamage the new damage amount
	 */
	public void setDamage(int newDamage) {
		damage = newDamage;
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

	private boolean inRange(EnemyCircle enemy) {
		//Determine if the enemy is left or right of tower
		boolean inX = (enemy.getXpos() < position[0]) ?
				position[0] - range[0] <= enemy.getXpos() :
				position[0] + range[0] >= enemy.getXpos() ;

		//Determine if the enemy is above or below tower
		boolean inY = (enemy.getYpos() < position[1]) ?
				position[1] - range[1] <= enemy.getYpos() :
				position[1] + range[1] >= enemy.getYpos() ;

		return inX && inY;
	}

	/**
	 * This method finds all enemies within firing range of the tower.
	 */
	//I think maybe we just need to find the first enemy that is within the range and then attack that one instead of having it find all enemies
	public void findEnemiesInRange() {
		enemiesInRange.clear();
		ArrayList<EnemyCircle> temp = logic.getEnemies();

		for(EnemyCircle e: temp)
			if(inRange(e))
				enemiesInRange.add(e);

	}
	
	//Is it possible to combine findEnemiesInRange() and attackEnemies()?
	//I feel like these two methods may take longer than we have to allow...
	
	/**
	 * This method will attack the enemies within the tower's firing range.
	 * This is done by using the EnemyCircle.setHealth() method.
	 */
	public void attackEnemies() {
		EnemyCircle temp;
		//This only gets the first enemy if you do the one below then it will attack multiple enemies dont want this
		if(enemiesInRange.size() > 0){
			temp = enemiesInRange.get(0);
			logic.addBullet(new Bullet(position[0],position[1], temp.getXpos(), temp.getYpos(), logic));
			//temp.setHealth(temp.getHealth()-damage);
		}
		//Afraid to use a for-each loop because half the time it doesn't work
//		for(int i = 0; i < enemiesInRange.size(); ++i) {
//			temp = enemiesInRange.get(i);
//			temp.setHealth(temp.getHealth()-damage);
//		}
	}
}
