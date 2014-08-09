package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Player;

public interface ClientInterface extends Remote {
	
	/**
	 * Function called when a player is available
	 * @param opponent: the opponent of the player
	 * @throws RemoteException
	 */
	public void playerAvailable (Player opponent)  throws RemoteException;
	
	/**
	 * Function called on client side when the game begins. Sends the map data
	 * @param map
	 * @throws RemoteException
	 */
	public void mapData (GameMap map) throws RemoteException;
	
	/**
	 * Function called when the server lost the connection to an Opponent
	 * @throws RemoteException
	 */
	public void connectionLost ()  throws RemoteException;

}
