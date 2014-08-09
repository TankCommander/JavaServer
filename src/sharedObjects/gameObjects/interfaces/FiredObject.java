package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FiredObject extends Remote {
	
	public String getMatchId () throws RemoteException;
	public String getPlayerID ()  throws RemoteException;
	public float getAngle ()  throws RemoteException;
	public float getPower ()  throws RemoteException;

}
