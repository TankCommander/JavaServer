package sharedObjects.connectionObjects.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.gameObjects.interfaces.FlightPath;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;

public interface ClientInterface extends Remote {
		
	/**
	 * Function called on client side when the game begins. Sends the match object
	 * @param map
	 * @throws RemoteException
	 */
	public void setGameObjects (Match match) throws RemoteException;
	
	
	/**
	 * Function which will inform the client about a new flight path of the fired object
	 * @param flightPath
	 * @throws RemoteException
	 */
	public void setNewFlightPath (FlightPath flightPath) throws RemoteException;
	
	
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
	
	/**
	 * Function called when a game is finished
	 * @throws RemoteException
	 */
	public void gameEnded (boolean winner) throws RemoteException;

}
