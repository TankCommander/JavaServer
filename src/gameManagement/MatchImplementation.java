package gameManagement;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Dictionary;

import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.gameObjects.interfaces.FlightPath;
import sharedObjects.gameObjects.interfaces.GameMap;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;
import sharedObjects.gameObjects.interfaces.Point;

public class MatchImplementation implements Match, Serializable {

	private static final long serialVersionUID = -7945851571685082676L;
	
	private Calculation calculation;
	private Dictionary<Player, Point> playerPositions;
	private ArrayList<Player> players;
	private GameMap map;
	private Player activePlayer;
	
	public MatchImplementation (ArrayList<Player> players) throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
		
		//Create the Map
		map = MatchBuilder.getNewGameMap();
		
		//Set the players
		this.players = MatchBuilder.getShuffledPlayers(players);	
		//Set backreference for players
	    for (Player player : players) 
			player.setMatch(this);
		
	    playerPositions = MatchBuilder.getNewPlayerPositions(map, players);
	    
	    this.calculation = new Calculation(Consts.WORLD_WIDTH, map.getHorizonLine(), players, playerPositions);
	    this.activePlayer = this.players.get(0);
	}
	
	public Point getPlayerPosition(Player player){
		return playerPositions.get(player);
	}
	
	/**
	 * Called when a player want to fire to the 
	 */
	@Override
	public boolean Fire(ClientInterface sender, float angle, float power)
			throws RemoteException {
		
		//Check if the right player sends the message
		if (sender.getPlayer() != this.activePlayer)
		{
			System.out.println("Wrong player sends fire Command");
			return false;
		}
		
		//Calculate the flight path
		FlightPath path = this.calcFlightPath(sender.getPlayer(), angle, power);
		
		//Set the new active player
		//TODO: Implement Code here
		
		
		//Send the flight path to the clients
		for (Player player : this.players) {
			player.getClientInterface().setNewFlightPath(path);
		}
		
		
		return false;
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

	@Override
	public Player getActivePlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightPath calcFlightPath(Player source, double angle, double speed) throws RemoteException {
		return calculation.calc_flugbahn(source, angle, speed);
	}
	

}
