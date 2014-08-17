package sharedObjects.gameObjects.implementations;

import sharedObjects.gameObjects.interfaces.Hit;
import sharedObjects.gameObjects.interfaces.Player;

public class HitImpl extends TimePointImpl implements Hit{

	private static final long serialVersionUID = 6519528769132257396L;
	private double percent;
	private Player target;

	@Override
	public Player getTarget() {
		return target;
	}

	@Override
	public void setTarget(Player target) {
		this.target = target;
	}

	public HitImpl(double x, double y, double t, double percent, Player target) {
		super(x, y, t);
		
		this.setPercent(percent);
		this.target = target;
	}

	@Override
	public double getPercent() {
		return percent;
	}

	@Override
	public void setPercent(double percent) {
		this.percent = percent;
	}

}
