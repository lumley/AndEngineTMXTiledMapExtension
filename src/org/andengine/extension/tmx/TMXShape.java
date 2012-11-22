package org.andengine.extension.tmx;

import java.util.ArrayList;

public class TMXShape {

	private final String type;
	private final int X;
	private final int Y;
	private final ArrayList<TMXPoint> points = new ArrayList<TMXPoint>();
	
	public TMXShape(String type, int initX, int initY) {
		this.type = type;
		this.X = initX;
		this.Y = initY;
	}
	
	public ArrayList<TMXPoint> getPoints() {
		return points;
	}
	
	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public String getType() {
		return type;
	}
	
	public void addNextPoint(int X, int Y){
		this.points.add(new TMXPoint(this.getX() + X,
				 this.getY() + Y ));
	}

}
