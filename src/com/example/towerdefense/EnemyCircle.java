package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class EnemyCircle {


	private int[] position;
	private Paint mPaint;
	LinkedList<int []> inst;
	private int radius;
	private int health = 700;
	private int speed = 2;
	TowerGameLogic mGame;


	private static final String TAG = "GameLogic";

	@SuppressWarnings("unchecked")
	public EnemyCircle(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g){
		position = new int[2];
		position[0] = x;
		position[1] = y;		
		inst = (LinkedList<int[]>) i.clone();
		radius = r;
		mGame = g;
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);        
		mPaint.setStrokeWidth(5);
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

	public void update(){
		if(inst.isEmpty() || health <= 0){
			mGame.removeEnemey(this);
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

}
