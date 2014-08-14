package gameManagement;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Dictionary;

import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.gameObjects.implementations.GameMapImpl;
import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;

public class MatchImplementation implements Match, Serializable {

	private static final long serialVersionUID = -7945851571685082676L;
	
	private Dictionary<Player, Point> playerPositions;
	private ArrayList<Player> players;
	private GameMap map;
	
	public MatchImplementation (Player player1, Player player2) throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
		
		//Create the Map
		map = MatchBuilder.getNewGameMap();
		
		//Set the players
		players = MatchBuilder.getShuffledPlayers(players);	
		//Set backreference for players
	    for (Player player : players) 
			player.setMatch(this);
		
	    playerPositions = MatchBuilder.getNewPlayerPositions(map, players);
	}
	
	public Point getPlayerPosition(Player player){
		return playerPositions.get(player);
	}
	
	@Override
	public void Fire(ClientInterface sender, float angle, float power)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	////////////////////
	//Getter + Setter//
	//////////////////
	@Override
	public GameMap getMap() throws RemoteException {
		// TODO Auto-generated method stub
		return map;
	}
	
	@Override
	public ArrayList<Player> getPlayers() throws RemoteException {
		return players;
	}
	

}
