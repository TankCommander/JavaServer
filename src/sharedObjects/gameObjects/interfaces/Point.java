package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Point extends Remote {
	
	public int getX ()  throws RemoteException;
	public int getY ()  throws RemoteException;

}
