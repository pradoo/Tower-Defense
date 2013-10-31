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



	public Level(TowerGameLogic m, int cellheight, int cellwidth) {
		mGame = m;
		enemies = new ArrayList<EnemyCircle>();
		inst = new LinkedList<int []>();

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


}
