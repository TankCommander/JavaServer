package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerEntryPoint extends Remote{
	
	/**
	 * Function which register the client on the server
	 * @param client
	 * @throws RemoteException
	 */
	public void registerClient (ClientInterface client) throws RemoteException;
	
}
