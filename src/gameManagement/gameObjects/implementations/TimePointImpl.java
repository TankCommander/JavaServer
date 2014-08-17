package gameManagement.gameObjects.implementations;

import sharedObjects.gameObjects.interfaces.TimePoint;

public class TimePointImpl extends PointImpl implements TimePoint{

	private static final long serialVersionUID = 2254055045365246897L;
	private double t;

	public TimePointImpl(double x, double y, double t) {
		super(x, y);
		this.setT(t);
	}

	public double getT() {
		return t;
	}
	public void setT(double t) {
		this.t = t;
	}

}
