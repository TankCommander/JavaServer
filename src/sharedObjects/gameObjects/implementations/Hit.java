package sharedObjects.gameObjects.implementations;

import sharedObjects.gameObjects.interfaces.Player;


public interface Hit {

	public abstract Player getTarget();

	public abstract void setTarget(Player target);

	public abstract double getPercent();

	public abstract void setPercent(double percent);

}