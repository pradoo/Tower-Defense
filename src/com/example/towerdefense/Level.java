package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

public class Level{

	private TowerGameLogic mGame;
	private LinkedList<int []> inst;
	private ArrayList<EnemyCircle> enemies;
	private int index = 0;



	public Level(TowerGameLogic m, int cellheight, int cellwidth) {
		mGame = m;
		enemies = new ArrayList<EnemyCircle>();
		inst = new LinkedList<int []>();

		//each instruction is an int array with the first int being which direction you want to move in 0 for x and 1 for y. Next is how far you want to go to.
		inst.add(new int[] {0,(cellwidth * 3) + (cellwidth /2)});
		inst.add(new int[] {1,(cellheight * 6) + (cellheight/2)});
		inst.add(new int[] {0,(cellwidth * 8) + (cellheight/2)});
		mGame.setInst(inst);
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
		enemies.add(new EnemyCircle(-50, (cellheight/2)* 9, 50, inst, m));
	}

	public void addEnemey(){
		if(index < enemies.size()){
			mGame.addEnemy(enemies.get(index));
			++index;
		}
	}


}
