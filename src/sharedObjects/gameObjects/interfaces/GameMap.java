package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameMap extends Remote, Serializable {
	
	public ArrayList<Point> getHorizonLine ()  throws RemoteException;
	public ArrayList<Point> getHorizonSkeleton() throws RemoteException;

	public double getHorizonY_Value(int x) throws RemoteException;
	
}
