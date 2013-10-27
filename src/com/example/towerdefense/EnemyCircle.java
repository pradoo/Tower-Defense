package com.example.towerdefense;

public class EnemyCircle {
	
	private int xpos;
	private int ypos;
	private int radius;
	private int health;
	private int speed;
	
	public EnemyCircle(int x, int y, int r){
		xpos = x;
		ypos = y;
		radius = r;
	}
	
	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public int getRadius() {
		// TODO Auto-generated method stub
		return radius;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

}
