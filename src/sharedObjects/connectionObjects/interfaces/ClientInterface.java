package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.FiredObject;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;

public interface ClientInterface extends Remote {
		
	/**
	 * Function called on client side when the game begins. Sends the match object
	 * @param map
	 * @throws RemoteException
	 */
	public void gameObject (Match match) throws RemoteException;
	
	/**
	 * Function called when a opponent fired a shot
	 * @param data
	 * @throws RemoteException
	 */
	public void opponentFired (FiredObject data) throws RemoteException;
	
	
	/**
	 * Function called when the server lost the connection to an Opponent
	 * @throws RemoteException
	 */
	public void connectionLost ()  throws RemoteException;
	
	
	/**
	 * Function which will return the Player object
	 * @return
	 * @throws RemoteException
	 */
	public Player getPlayer () throws RemoteException;

}
