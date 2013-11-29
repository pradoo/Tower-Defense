package Levels;

import java.util.ArrayList;
import java.util.LinkedList;

import android.graphics.Path;

import com.example.towerdefense.AbsEnemy;
import com.example.towerdefense.R;
import com.example.towerdefense.TowerGameLogic;

public abstract class AbsLevel {
	
	protected TowerGameLogic mGame;
	protected LinkedList<int []> inst;
	protected ArrayList<ArrayList<AbsEnemy>> enemies;
	protected int wave = 0;
	protected int index = 0;
	
	protected Path path;
	protected int cw;
	protected int ch;
	protected int halfch;
	protected int halfcw;


	public AbsLevel(TowerGameLogic m, int cellheight, int cellwidth) {
		cw = cellwidth;
		ch = cellheight;
		halfcw = cw/2;
		halfch = ch/2;
		mGame = m;
		enemies = new ArrayList<ArrayList<AbsEnemy>>();
		inst = new LinkedList<int []>();
		path = new Path();
		
		makepath(mGame.getindexboard());
	}
	
	public boolean addEnemey() {
		if(index < enemies.get(wave).size()){
			mGame.addEnemy(enemies.get(wave).get(index));
			mGame.playSound(R.raw.enemystart, (float)1);
			++index;
			return true;
		}
		return false;
	}
	
	public Path getPath() {
		return path;
	}
	
	public abstract int getGold();
	
	public abstract void makepath(int[][] i);

}
