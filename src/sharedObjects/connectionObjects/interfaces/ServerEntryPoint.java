package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.Player;

public interface ServerEntryPoint extends Remote{
	
	/**
	 * Function which register the client on the server
	 * @param client
	 * @throws RemoteException
	 * @return: True if a player is available otherwise false
	 */
	public boolean registerClient (ClientInterface client) throws RemoteException;

	public void receiveKeepAlive(Player player) throws RemoteException;
	
}
