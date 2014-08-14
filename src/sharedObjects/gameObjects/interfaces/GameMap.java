package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameMap extends Remote {
	
	public ArrayList<Point> getHorizonLine ()  throws RemoteException;
	public int getY_Value(int x) throws RemoteException;
	public Point getPlayer1Postion ()  throws RemoteException;
	public Point getPlayer2Postion ()  throws RemoteException;
	
}
