package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import sharedObjects.connectionObjects.interfaces.ClientInterface;

public interface Match extends Remote, Serializable {
		
	public Player getActivePlayer() throws RemoteException;

	ArrayList<Player> getPlayers () throws RemoteException;
	
	public boolean Fire (ClientInterface sender, double angle, double power) throws RemoteException;
	
	public GameMap getMap () throws RemoteException;
	
	public FlightPath calcFlightPath(Player source, double angle, double speed) throws RemoteException;

	void setActivePlayer(Player activePlayer) throws RemoteException;

	Point getPlayerPosition(Player player) throws RemoteException;
	

}
