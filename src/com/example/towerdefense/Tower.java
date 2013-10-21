package com.example.towerdefense;

public class Tower {

	//Not sure if this needs to stay int
	//Also not sure if 'final'
	final int RANGE;
	int damage;
	
	//PriorityQueue for enemies goes here
	/*
	 * Priority based on enemy health, unless we aren't allowing
	 * enemies to circle around to the beginning after reaching
	 * the end?
	 * 
	 */
	
	public Tower(int starting_range) {
		RANGE = starting_range;
		damage = 10;//subject to change based on enemies
	}
	
	public void findEnemiesInRange() {
		//Help fill this in once enemies are implemented
	}
}
