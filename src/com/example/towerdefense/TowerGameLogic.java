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
	
	
	public void level1(int cellheight, int cellwidth){
		inst = new LinkedList<int []>();
		inst.add(new int[] {0,(cellwidth * 3) + (cellwidth /2)});
		inst.add(new int[] {1,(cellheight * 6) + (cellheight/2)});
		inst.add(new int[] {0,(cellwidth * 8) + (cellheight/2)});
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 7, 50, inst, this));
	}
	
	
	public void updateEnemies(){
		for(int i = 0; i < enemies.size(); ++i){
			enemies.get(i).update();
		}
	}


	public void removeEnemey(EnemyCircle enemyCircle) {
		enemies.remove(enemyCircle);	
	}

}
