package com.example.towerdefense;

import android.content.Context;
import android.view.View;

public class BoardView extends View{

	private TowerGameLogic mGame;
	
	public BoardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void setGame(TowerGameLogic game) {
		mGame = game;
	}	

}
