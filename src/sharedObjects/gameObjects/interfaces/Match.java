package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Match extends Remote {
		
	ArrayList<Player> getPlayers () throws RemoteException;
	
	public void Fire (float angle, float power) throws RemoteException;

}
