package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.FiredObject;

public interface ServerEntryPoint extends Remote{
	
	/**
	 * Function which register the client on the server
	 * @param client
	 * @throws RemoteException
	 */
	public void registerClient (ClientInterface client) throws RemoteException;
	
	/**
	 * Function called when a client fired
	 * @param fired
	 * @throws RemoteException
	 */
	public void fire (FiredObject fired) throws RemoteException;
	
}
