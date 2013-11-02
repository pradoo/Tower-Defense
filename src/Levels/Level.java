package Levels;

import java.util.ArrayList;
import java.util.LinkedList;

import com.example.towerdefense.EnemyCircle;
import com.example.towerdefense.TowerGameLogic;

public class Level{

	private TowerGameLogic mGame;
	private LinkedList<int []> inst;
	private ArrayList<EnemyCircle> enemies;
	private int index = 1;
	private ArrayList<int[]> path;
	

	public Level(TowerGameLogic m, int cellheight, int cellwidth) {
		mGame = m;
		enemies = new ArrayList<EnemyCircle>();
		inst = new LinkedList<int []>();
		path = new ArrayList<int[]>();
		makepath(mGame.getindexboard());
		

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
		mGame.addEnemy(enemies.get(0));
	}

	public void addEnemey() {
		if(index < enemies.size()){
			mGame.addEnemy(enemies.get(index));
			++index;
		}
	}
	
	public void makepath(int[][] i){
		path.add(new int []{3,0});
		path.add(new int []{3,1});
		path.add(new int []{3,2});
		path.add(new int []{3,3});
		path.add(new int []{4,3});
		path.add(new int []{5,3});
		i[3][0] = 1;
		i[3][1] = 1;
		i[3][2] = 1;
		i[3][3] = 1;
		i[4][3] = 1;
		i[5][3] = 1;
	}

	public ArrayList<int[]> getPath() {
		return path;
	}


}
