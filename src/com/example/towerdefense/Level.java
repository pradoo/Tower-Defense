package com.example.towerdefense;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.SharedPreferences;
import android.graphics.Path;
import android.graphics.Path.Direction;


public class Level extends AbsLevel{
	
	
	private int gold = 1000;

	
	public Level(TowerGameLogic m, int cellheight, int cellwidth) {
		super(m, cellheight, cellwidth);
		
		inst.add(new int[] {0,(cw * 3) + halfcw, 1});
		inst.add(new int[] {1,(ch * 5) + halfch, 1});
		inst.add(new int[] {0,(cw * 8) + halfcw, 1});
		inst.add(new int[] {1,(ch * 3) + halfch, -1});
		inst.add(new int[] {0,(cw * 13) + halfcw, 1});
		
		m.setPath(path);
		
		mGame.setInst(inst);  
		ArrayList<AbsEnemy> wave1 = new ArrayList<AbsEnemy>();
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (3*ch+halfch), halfch, inst, m));
		enemies.add(wave1);
	}
	
	public int getGold(){
		return gold;
	}
	
	public void makepath(){
		addpath(3,0);
		addpath(3,1);
		addpath(3,2);
		addpath(3,3);
		addpath(4,3);
		addpath(5,3);
		addpath(5,4);
		addpath(5,5);
		addpath(5,6);
		addpath(5,7);
		addpath(5,8);
		addpath(4,8);
		addpath(3,8);
		addpath(3,9);
		addpath(3,10);
		addpath(3,11);
		addpath(3,12);
		addpath(3,13);
	}
}
