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
	
	
	//this is how a level gets created right now complex. I will make this better later on.
	public void level1(int cellheight, int cellwidth){
		inst = new LinkedList<int []>();
		
		//each instruction is an int array with the first int being which direction you want to move in 0 for x and 1 for y. Next is how far you want to go to.
		inst.add(new int[] {0,(cellwidth * 3) + (cellwidth /2)});
		inst.add(new int[] {1,(cellheight * 6) + (cellheight/2)});
		inst.add(new int[] {0,(cellwidth * 8) + (cellheight/2)});
		
		
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 7, 50, inst, this));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, this));
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

}
