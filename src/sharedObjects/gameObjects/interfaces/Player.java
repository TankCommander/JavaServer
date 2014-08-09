package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Player extends Remote {
	
	public String getName ()  throws RemoteException;
	public String getDamage ()  throws RemoteException;

}
