package com.example.towerdefense;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class Bullet extends Drawable{
	private TowerGameLogic mGame;
	
	private static Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	private float speed = 20;
	private float distance = 0;
	private float[] pos, tan;
	private PathMeasure measure;
	private int x;
	private int y;
	private int damage = 100;
	private int xtarget;
	private int ytarget;
	private int radius = 10;
	Rect bounds;
	Path path;
	public Bullet(int xloc, int yloc, int xtar, int ytar, TowerGameLogic g){
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(3);	
		mGame = g;
		x = xloc;
		y = yloc;
		xtarget = xtar;
		ytarget = ytar;
		path = new Path();
		path.moveTo(x, y);
		path.lineTo(xtarget, ytarget);
		measure = new PathMeasure(path, false);
		pos=new float[2];
		tan=new float[2];
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		bounds = new Rect((int)pos[0] - radius, (int)pos[1] - radius, (int)pos[0] + radius, (int)pos[1] + radius);
		canvas.drawRect(bounds, mPaint);
		this.setBounds(bounds);
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		// TODO Auto-generated method stub

	}
	
	public int getDamage(){
		return damage;
	}

	public void update() {
		// TODO Auto-generated method stub
		if(distance < measure.getLength()){
			// getPosTan pins the distance along the Path and
			// computes the position and the tangent.
			measure.getPosTan(distance, pos, tan);
			distance += speed;   // Traversal
		}
		else
			mGame.removeBullet(this);
	}
}
