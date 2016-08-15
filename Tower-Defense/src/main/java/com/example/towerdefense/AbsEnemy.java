package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public abstract class AbsEnemy extends  Drawable{

	
	protected int[] position;
	protected Paint mPaint;
	protected int radius;
	protected double ratio;
	protected int health;
	protected int speed;
	protected int gold;
	LinkedList<int []> inst;
	TowerGameLogic mGame;
	Rect bounds;
	
	@SuppressWarnings("unchecked")
	public AbsEnemy(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g, double rat) {
		position = new int[2];
		position[0] = x;
		position[1] = y;
		ratio = rat;
		//this line need to get changed eventually right now copies the list of instructions and save it for each enemy
		inst = (LinkedList<int[]>) i.clone();
		radius = (int) (r*ratio);
		mGame = g;
	}
	
	public int getXpos() {
		return position[0];
	}

	public void setXpos(int xpos) {
		position[0] = xpos;
	}

	public int getYpos() {
		return position[1];
	}

	public void setYpos(int ypos) {
		position[1] = ypos;
	}
	

	public int getRadius() {
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
		this.health += health;
	}

	
	/**
	 * This method will check to see if the enemy is dead 
	 * or has reached the end of the path and if so it will 
	 * remove it from the list of enemies
	 * otherwise it will execute the next instruction 
	 */
	public void update(){
		if(inst.isEmpty()){
			mGame.decLives();
			mGame.playSound(R.raw.loselife, 1);
			mGame.removeEnemy(this);
		}
		else if(health <= 0){
			mGame.setGold(gold);
			mGame.removeEnemy(this);
		} 
		else if(inst.peek()[2] == 1){
			if(position[inst.peek()[0]] < inst.peek()[1]){
				position[inst.peek()[0]] += speed;
			}
			else{
				inst.pop();
				update();
			}
		}
		else {
			if(position[inst.peek()[0]] > inst.peek()[1]){
				position[inst.peek()[0]] -= speed;
			}
			else{
				inst.pop();
				update();
			}
		}
	}
	
	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub
		
	}
	
	abstract public void draw(Canvas Canvas);

}
