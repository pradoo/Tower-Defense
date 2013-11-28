package Levels;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Path;
import android.graphics.Path.Direction;

import com.example.towerdefense.AbsEnemy;
import com.example.towerdefense.EnemyCircle;
import com.example.towerdefense.R;
import com.example.towerdefense.TankEnemy;
import com.example.towerdefense.TowerGameLogic;

public class Level{

	private TowerGameLogic mGame;
	private LinkedList<int []> inst;
	private ArrayList<AbsEnemy> enemies;
	private int index = 0;
	private static final int gold = 1000;
	Path path;
	private int cw;
	private int ch;
	private int halfch;
	private int halfcw;

	public Level(TowerGameLogic m, int cellheight, int cellwidth) {
		cw = cellwidth;
		ch = cellheight;
		halfcw = cw/2;
		halfch = ch/2;
		mGame = m;
		enemies = new ArrayList<AbsEnemy>();
		inst = new LinkedList<int []>();
		path = new Path();
		
		makepath(mGame.getindexboard());
		inst.add(new int[] {0,(cw * 3) + halfcw, 1});
		inst.add(new int[] {1,(ch * 5) + halfch, 1});
		inst.add(new int[] {0,(cw * 8) + halfcw, 1});
		inst.add(new int[] {1,(ch * 3) + halfch, -1});
		inst.add(new int[] {0,(cw * 13) + halfcw, 1});
		
		m.setPath(path);
		
		mGame.setInst(inst);  
		
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		
		enemies.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
	}
	
	public int getGold(){
		return gold;
	}
	public void addEnemey() {
		if(index < enemies.size()){
			mGame.addEnemy(enemies.get(index));
			mGame.playSound(R.raw.enemystart, (float)1);
			++index;
		}
	}
	
	public void makepath(int[][] i){
		Direction d = Path.Direction.CW;
		path.addRect(0*cw, 3*ch, 0*cw+cw, 3*ch+ch, d);
		path.addRect(1*cw, 3*ch, 1*cw+cw, 3*ch+ch, d);
		path.addRect(2*cw, 3*ch, 2*cw+cw, 3*ch+ch, d);
		path.addRect(3*cw, 3*ch, 3*cw+cw, 3*ch+ch, d);
		path.addRect(3*cw, 4*ch, 3*cw+cw, 4*ch+ch, d);
		path.addRect(3*cw, 5*ch, 3*cw+cw, 5*ch+ch, d);
		path.addRect(4*cw, 5*ch, 4*cw+cw, 5*ch+ch, d);
		path.addRect(5*cw, 5*ch, 5*cw+cw, 5*ch+ch, d);
		path.addRect(6*cw, 5*ch, 6*cw+cw, 5*ch+ch, d);
		path.addRect(7*cw, 5*ch, 7*cw+cw, 5*ch+ch, d);
		path.addRect(8*cw, 5*ch, 8*cw+cw, 5*ch+ch, d);
		path.addRect(8*cw, 4*ch, 8*cw+cw, 4*ch+ch, d);
		path.addRect(8*cw, 3*ch, 8*cw+cw, 3*ch+ch, d);
		path.addRect(9*cw, 3*ch, 9*cw+cw, 3*ch+ch, d);
		path.addRect(10*cw, 3*ch, 10*cw+cw, 3*ch+ch, d);
		path.addRect(11*cw, 3*ch, 11*cw+cw, 3*ch+ch, d);
		path.addRect(12*cw, 3*ch, 12*cw+cw, 3*ch+ch, d);
		path.addRect(13*cw, 3*ch, 13*cw+cw, 3*ch+ch, d);
		i[3][0] = 1;
		i[3][1] = 1;
		i[3][2] = 1;
		i[3][3] = 1;
		i[4][3] = 1;
		i[5][3] = 1;
		i[5][4] = 1;
		i[5][5] = 1;
		i[5][6] = 1;
		i[5][7] = 1;
		i[5][8] = 1;
		i[4][8] = 1;
		i[3][8] = 1;
		i[3][9] = 1;
		i[3][10] = 1;
		i[3][11] = 1;
		i[3][12] = 1;
		i[3][13] = 1;
	}

	public Path getPath() {
		return path;
	}


}
