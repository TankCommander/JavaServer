package sharedObjects.gameObjects.implementations;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Point;

public class GameMapImpl implements GameMap {
	private ArrayList<Point> horizonSkeleton = null;
	
	public GameMapImpl(ArrayList<Point> horizonSkeleton) {
		this.horizonSkeleton = horizonSkeleton;
	}
       		
	@Override
	public ArrayList<Point> getHorizonLine() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getPlayer1Postion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getPlayer2Postion() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
