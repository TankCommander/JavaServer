package sharedObjects.gameObjects.implementations;

import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.Point;

public class PointImplementation extends java.awt.Point implements Point{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6907383472404847668L;

	public PointImplementation(int x, int y){
		super(x,y);
	}
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
