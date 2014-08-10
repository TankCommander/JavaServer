package gameManagement;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;

public class MatchImplementation implements Match, Serializable {

	private static final long serialVersionUID = -7945851571685082676L;
	
	private ArrayList<Player> players = new ArrayList<Player>();
	private GameMap map;
	
	public MatchImplementation () throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	@Override
	public ArrayList<Player> getPlayers() throws RemoteException {
		return players;
	}

	@Override
	public void Fire(ClientInterface sender, float angle, float power)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	

}
