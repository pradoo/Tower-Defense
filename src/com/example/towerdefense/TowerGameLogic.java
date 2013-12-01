package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import Levels.AbsLevel;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.widget.TextView;

public class TowerGameLogic {
	
	private static final String TAG = "GameLogic";
	private int gold = 0;
	private int lives = 20;
	private ArrayList<AbsEnemy> enemies; 
	private ArrayList<AbsTower> towers;
	private ArrayList<Bullet> bullets;
	private TowerGame act;
	LinkedList<int []> inst;
	private Path path;
	AbsLevel level;
	private boolean firstrun = false;
	// 0 empty 1 path 2 tower
	private int[][] indexboard;
	
	public TowerGameLogic(){
		enemies = new ArrayList<AbsEnemy>();
		towers = new ArrayList<AbsTower>();
		bullets = new ArrayList<Bullet>();
		indexboard = new int[boardSurface.BOARD_HEIGHT][boardSurface.BOARD_WIDTH];
	}
	
	
	public ArrayList<AbsEnemy> getEnemies() {
		return enemies;
	}
	public void setEnemies(ArrayList<AbsEnemy> enemies) {
		this.enemies = enemies;
	}
	
	public void setGold(int g){
		gold += g;
		act.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				act.updateViews();	
			}
			
		});
		
	}
	
	public void resetGold(int g) {
		gold = g;
		act.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				act.updateViews();	
			}
			
		});
		
	}
	
	public void decLives(){
		--lives;
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				act.decLives(lives);	
			}
		});
	}
	public void updateEnemies(){
		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).update();
		}
	}
	
	public void addEnemy(AbsEnemy enemy){
		enemies.add(enemy);
	}


	public void removeEnemey(AbsEnemy absEnemy) {
		enemies.remove(absEnemy);	
	}


	public void setInst(LinkedList<int[]> i) {
		inst = i;	
	}
	
	public boolean levelOver(){
		return enemies.isEmpty() && firstrun && !level.hasEnemies();
	}


	public void addTower(AbsTower tower) {
		// TODO Auto-generated method stub
		towers.add(tower);
	}


	public ArrayList<AbsTower> getTowers() {
		// TODO Auto-generated method stub
		return towers;
	}


	public boolean checktower(int col, int row) {
		if(indexboard[col][row] == 0)
			return true;
		return false;
	}
	
	public int[][] getindexboard(){
		return indexboard;
	}


	public int getGold() {
		// TODO Auto-generated method stub
		return gold;
	}


	public void setAct(TowerGame towerGame) {
		act = towerGame;
	}


	public void addBullet(Bullet bullet) {
		// TODO Auto-generated method stub
		bullets.add(bullet);
	}


	public ArrayList<Bullet> getBullets() {
		// TODO Auto-generated method stub
		return bullets;
	}


	public void updateBullets() {
		// TODO Auto-generated method stub
		for(int i = 0; i < bullets.size(); ++i){
			bullets.get(i).update();
		}
	}


	public void removeBullet(Bullet bullet) {
		// TODO Auto-generated method stub
		bullets.remove(bullet);
	}


	public void setPath(Path p) {
		path = p;
	}


	public Path getPath() {
		// TODO Auto-generated method stub
		return path;
	}

	public void setFirstRun(boolean t){
		firstrun = t;
	}
	
	public void checkcollisions(){
		for(int i = 0; i < enemies.size(); ++i){
			for(int j = 0; j < bullets.size(); ++j){
				AbsEnemy enemy = enemies.get(i);
				Bullet bullet = bullets.get(j);
				if(Rect.intersects(enemy.getBounds(), bullet.getBounds())){
					enemy.setHealth(-bullet.getDamage());
					removeBullet(bullet);
				}
			}
		}
	}


	public void playSound(int id, float volume) {
		act.playFromSoundPool(id, volume);		
	}


	public void clear() {
		firstrun = false;
		enemies = new ArrayList<AbsEnemy>();
		towers = new ArrayList<AbsTower>();
		bullets = new ArrayList<Bullet>();
		indexboard = new int[boardSurface.BOARD_HEIGHT][boardSurface.BOARD_WIDTH];
		
	}


	public void setLevel(AbsLevel l) {
		level = l;
		
	}


}
