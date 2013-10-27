package com.example.towerdefense;

import java.util.ArrayList;

public class TowerGameLogic {
	
	private ArrayList<EnemyCircle> enemies;
	
	public TowerGameLogic(){
		enemies = new ArrayList<EnemyCircle>();
		enemies.add(new EnemyCircle(100, 100, 20));
	}
	
	
	public ArrayList<EnemyCircle> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<EnemyCircle> enemies) {
		this.enemies = enemies;
	}
	
	public void level1(int cellheight, int cellwidth){
		
	}

}
