package com.example.towerdefense;

import java.util.LinkedList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class EnemyCircle extends Drawable{


	private int[] position;
	private Paint mPaint;
	LinkedList<int []> inst;
	private int radius;
	private double ratio = 0.75;
	private int health = 700;
	private int speed = 2;
	private static final int gold = 25;
	TowerGameLogic mGame;

	private PathMeasure measure;
	private Path path;
	private float[] pos, tan;
	private float distance = 0;

	private static final String TAG = "GameLogic";

	@SuppressWarnings("unchecked")
	public EnemyCircle(int x, int y, int r, LinkedList<int []> i, TowerGameLogic g){
		position = new int[2];
		position[0] = x;
		position[1] = y;
		//this line need to get changed eventually right now copies the list of instructions and save it for each enemy
		inst = (LinkedList<int[]>) i.clone();

		path = g.getPath();
		measure = new PathMeasure(path, false);
		pos=new float[2];
		tan=new float[2];			

		radius = (int) (r*ratio);
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

	/**
	 * This method will check to see if the enemy is dead or has reached the end of the path and if so it will remove it from the list of enemies
	 * otherwise it will execute the next instruction 
	 */
	public void update(){
		if(inst.isEmpty() )
			mGame.removeEnemey(this);
		else if(health <= 0){
			mGame.setGold(gold);
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

	
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawCircle(pos[1], pos[0], radius, mPaint);
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

}
