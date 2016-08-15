package com.example.towerdefense;

import java.util.ArrayList;

public class Level3 extends AbsLevel{


	private static final int gold = 1000;

	public Level3(TowerGameLogic m, int cellheight, int cellwidth) {
		super(m, cellheight, cellwidth);
		inst.add(new int[] {0,(cw * 6) + halfcw, 1});
		inst.add(new int[] {1,(ch * 7) + halfch, 1});
		inst.add(new int[] {0,(cw * 13) + halfcw, 1});
		
		m.setPath(path);
		
		mGame.setInst(inst);  
		ArrayList<AbsEnemy> wave1 = new ArrayList<AbsEnemy>();
		
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new EnemyCircle(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (1*ch+halfch), halfch, inst, m));
		wave1.add(new TankEnemy(0, (1*ch+halfch), halfch, inst, m));
		enemies.add(wave1);
	}

	@Override
	public void makepath() {
		addpath(1,0);
		addpath(1,1);
		addpath(1,2);
		addpath(1,3);
		addpath(1,4);
		addpath(1,5);
		addpath(1,6);
		addpath(2,6);
		addpath(3,6);
		addpath(4,6);
		addpath(5,6);
		addpath(6,6);
		addpath(7,6);
		addpath(7,7);
		addpath(7,8);
		addpath(7,9);
		addpath(7,10);
		addpath(7,11);
		addpath(7,12);
		addpath(7,13);
		
	}

	@Override
	public int getGold() {
		// TODO Auto-generated method stub
		return gold;
	}

}
