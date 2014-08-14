package sharedObjects.gameObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sharedObjects.connectionObjects.interfaces.ClientInterface;

public interface Match extends Remote {
		
	ArrayList<Player> getPlayers () throws RemoteException;
	
	public void Fire (ClientInterface sender, float angle, float power) throws RemoteException;
	
	public GameMap getMap () throws RemoteException;
	

}
