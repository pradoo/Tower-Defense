package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.widget.TextView;

public class TowerGameLogic {
	
	private static final String LOG_TAG = "GameLogic";
	private int gold = 0;
	private int lives = 20;
	private boolean hasSaved;
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
		Log.i(LOG_TAG,"TowerGameLogic()");
		enemies = new ArrayList<AbsEnemy>();
		towers = new ArrayList<AbsTower>();
		bullets = new ArrayList<Bullet>();
		indexboard = new int[boardSurface.BOARD_HEIGHT][boardSurface.BOARD_WIDTH];
	}
	
	public void setSaved(boolean aa){
		Log.i(LOG_TAG,"setSaved("+aa+")");
		hasSaved = aa;
	}
	
	public boolean hasSaved(){
		Log.i(LOG_TAG,"hasSaved()");
		return hasSaved;
	}
	
	public ArrayList<AbsEnemy> getEnemies() {
		Log.i(LOG_TAG,"getEnemies()");
		return enemies;
	}
	public void setEnemies(ArrayList<AbsEnemy> enemies) {
		Log.i(LOG_TAG,"setEnemies()");
		this.enemies = enemies;
	}
	
	public void setGold(int g){
		Log.i(LOG_TAG,"setGold("+g+")");
		gold += g;
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				act.updateViews();	
			}			
		});		
	}
	
	public void resetGold(int g) {
		Log.i(LOG_TAG,"resetGold("+g+")");
		gold = g;
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				act.updateViews();	
			}			
		});		
	}
	
	public void setLives(int g){
		Log.i(LOG_TAG,"setLives("+g+")");
		lives = g;
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				act.updateViews();	
			}			
		});		
	}
	
	public void decLives(){
		Log.i(LOG_TAG,"decLives()");
		--lives;
		act.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				act.decLives(lives);	
			}
		});
	}
	
	public void updateEnemies(){
		Log.i(LOG_TAG,"updateEnemies()");
		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).update();
		}
	}
	
	public void addEnemy(AbsEnemy enemy){
		Log.i(LOG_TAG,"addEnemy()");
		enemies.add(enemy);
	}


	public void removeEnemy(AbsEnemy absEnemy) {
		Log.i(LOG_TAG,"removeEnemy()");
		enemies.remove(absEnemy);	
	}


	public void setInst(LinkedList<int[]> i) {
		Log.i(LOG_TAG,"setInst()");
		inst = i;	
	}
	
	public boolean levelOver(){
		Log.i(LOG_TAG,"levelOver()");
		return enemies.isEmpty() && firstrun && !level.hasEnemies();
	}


	public void addTower(AbsTower tower) {
		Log.i(LOG_TAG,"addTower()");
		towers.add(tower);
	}


	public ArrayList<AbsTower> getTowers() {
		Log.i(LOG_TAG,"getTowers()");
		return towers;
	}


	public boolean checktower(int col, int row) {
		Log.i(LOG_TAG,"checkTower("+col+","+row+")");
		if(indexboard[col][row] == 0)
			return true;
		return false;
	}
	
	public int[][] getindexboard(){
		Log.i(LOG_TAG,"getIndexBoard()");
		return indexboard;
	}


	public int getGold() {
		Log.i(LOG_TAG,"getGold()");
		return gold;
	}
	
	public int getLives(){
		Log.i(LOG_TAG,"getLives()");
		return lives;
	}

	public void setAct(TowerGame towerGame) {
		Log.i(LOG_TAG,"setAct()");
		act = towerGame;
	}


	public void addBullet(Bullet bullet) {
		Log.i(LOG_TAG,"addBullet()");
		bullets.add(bullet);
	}


	public ArrayList<Bullet> getBullets() {
		Log.i(LOG_TAG,"getBullets()");
		return bullets;
	}


	public void updateBullets() {
		Log.i(LOG_TAG,"updateBullets()");
		for(int i = 0; i < bullets.size(); ++i){
			bullets.get(i).update();
		}
	}


	public void removeBullet(Bullet bullet) {
		Log.i(LOG_TAG,"removeBullet()");
		bullets.remove(bullet);
	}


	public void setPath(Path p) {
		Log.i(LOG_TAG,"setPath()");
		path = p;
	}


	public Path getPath() {
		Log.i(LOG_TAG,"getPath()");
		return path;
	}

	public void setFirstRun(boolean t){
		Log.i(LOG_TAG,"setFirstRun("+t+")");
		firstrun = t;
	}
	
	public void checkcollisions(){
		Log.i(LOG_TAG,"checkCollisions()");
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
		Log.i(LOG_TAG,"playSound()");
		act.playFromSoundPool(id, volume);		
	}


	public void clear() {
		Log.i(LOG_TAG,"clear()");
		firstrun = false;
		enemies = new ArrayList<AbsEnemy>();
		towers = new ArrayList<AbsTower>();
		bullets = new ArrayList<Bullet>();
		indexboard = new int[boardSurface.BOARD_HEIGHT][boardSurface.BOARD_WIDTH];
		
	}


	public void setLevel(AbsLevel l) {
		Log.i(LOG_TAG,"setLevel()");
		level = l;		
	}
	
	public void addToBoard(int row, int col, int value){
		Log.i(LOG_TAG,"addToBoard()");
		indexboard[row][col] = value;
	}


}
