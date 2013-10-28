package com.example.towerdefense;

import java.util.ArrayList;

import android.util.Log;

public class TowerGameLogic {
	
	private static final String TAG = "GameLogic";
	private ArrayList<EnemyCircle> enemies;
	private int xchange;
	private int xmax;
	
	public TowerGameLogic(){
		enemies = new ArrayList<EnemyCircle>();
	}
	
	
	public ArrayList<EnemyCircle> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<EnemyCircle> enemies) {
		this.enemies = enemies;
	}
	
	public void level1(int cellheight, int cellwidth){
		Log.d(TAG, "cellwidth =  " + cellwidth );
		xchange = 2;
		xmax = (cellwidth * 8) + (cellwidth /2);
		enemies.add(new EnemyCircle(10, 75, 50, xchange, xmax));
	}
	
	public void updateEnemies(){
		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).update();
		}
	}

}
