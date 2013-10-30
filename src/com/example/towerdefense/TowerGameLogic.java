package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import android.util.Log;

public class TowerGameLogic {
	
	private static final String TAG = "GameLogic";
	private ArrayList<EnemyCircle> enemies; 
	LinkedList<int []> inst;
	
	public TowerGameLogic(){
		enemies = new ArrayList<EnemyCircle>();
	}
	
	
	public ArrayList<EnemyCircle> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<EnemyCircle> enemies) {
		this.enemies = enemies;
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

}
