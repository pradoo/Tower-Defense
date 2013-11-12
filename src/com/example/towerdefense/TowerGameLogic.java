package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import android.util.Log;
import android.widget.TextView;

public class TowerGameLogic {
	
	private static final String TAG = "GameLogic";
	private int gold = 0;
	private ArrayList<EnemyCircle> enemies; 
	private ArrayList<Tower> towers;
	private TowerGame act;
	LinkedList<int []> inst;
	// 0 empty 1 path 2 tower
	private int[][] indexboard;
	
	public TowerGameLogic(){
		enemies = new ArrayList<EnemyCircle>();
		towers = new ArrayList<Tower>();
		indexboard = new int[boardSurface.BOARD_HEIGHT][boardSurface.BOARD_WIDTH];
	}
	
	
	public ArrayList<EnemyCircle> getEnemies() {
		return enemies;
	}
	public void setEnemies(ArrayList<EnemyCircle> enemies) {
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
	public void updateEnemies(){
		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).update();
		}
	}
	
	public void addEnemy(EnemyCircle enemyCircle){
		enemies.add(enemyCircle);
	}


	public void removeEnemey(EnemyCircle enemyCircle) {
		enemies.remove(enemyCircle);	
	}


	public void setInst(LinkedList<int[]> i) {
		inst = i;	
	}
	
	public boolean levelOver(){
		return enemies.isEmpty();
	}


	public void addTower(Tower tower) {
		// TODO Auto-generated method stub
		towers.add(tower);
	}


	public ArrayList<Tower> getTowers() {
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



}
