package gameManagement.gameObjects.implementations;

import sharedObjects.gameObjects.interfaces.Point;

/* Point erbt nicht von java.awt.point, da diese int-Punkte haben
 * zum Berechnen sind double besser geeignet
 */
public class PointImpl implements Point{

	private static final long serialVersionUID = -7665122984374293365L;
	private double x;
	private double y;
	
	public PointImpl(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}
}
