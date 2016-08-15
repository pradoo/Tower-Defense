package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Path;
import android.graphics.Path.Direction;

import com.example.towerdefense.R;

public abstract class AbsLevel {
	
	protected TowerGameLogic mGame;
	protected LinkedList<int []> inst;
	protected ArrayList<ArrayList<AbsEnemy>> enemies;
	protected int wave = 0;
	protected int index = 0;
	
	protected Path path;
	protected int cw;
	protected int ch;
	protected int halfch;
	protected int halfcw;
	protected Direction d = Path.Direction.CW;
	protected int[][] board;
	public int startspeed;
	protected AbsEnemy lastenemy;


	public AbsLevel(TowerGameLogic m, int cellheight, int cellwidth) {
		cw = cellwidth;
		ch = cellheight;
		halfcw = cw/2;
		halfch = ch/2;
		startspeed = 2;
		mGame = m;
		enemies = new ArrayList<ArrayList<AbsEnemy>>();
		inst = new LinkedList<int []>();
		path = new Path();
		board = mGame.getindexboard();
		makepath();
	}
	
	public boolean addEnemey() {
		if(index < enemies.get(wave).size()){
			lastenemy = enemies.get(wave).get(index);
			mGame.addEnemy(lastenemy);
			mGame.playSound(R.raw.enemystart, (float)1);
			++index;
			return true;
		}
		return false;
	}
	
	public Path getPath() {
		return path;
	}
	
	public abstract int getGold();
	
	public abstract void makepath();
	
	public void addpath(int r, int c){
		path.addRect(c*cw, r*ch, c*cw+cw, r*ch+ch, d);
		board[r][c] = 1;
	}
	
	public boolean hasEnemies() {
		return index < enemies.get(wave).size();
	}

	public int lastSpeed() {
		return lastenemy.getSpeed();
	}

}
