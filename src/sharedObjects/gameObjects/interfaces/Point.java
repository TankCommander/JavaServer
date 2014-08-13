package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Point extends Remote {
	
	public double getX ()  throws RemoteException;
	public double getY ()  throws RemoteException;

}
