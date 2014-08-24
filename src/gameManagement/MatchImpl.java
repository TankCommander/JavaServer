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

public class MatchImpl implements Match, Serializable {

	private static final long serialVersionUID = -7945851571685082676L;
	
	private Calculation calculation;
	private Dictionary<Player, Point> playerPositions;
	private ArrayList<Player> players;
	private GameMap map;
	private Player activePlayer;
	
	public MatchImpl (ArrayList<Player> players) throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
		
		//Create the Map
		map = MatchBuilder.getNewGameMap();
		
		//Set the players
		this.players = MatchBuilder.getShuffledPlayers(players);
		this.activePlayer = this.players.get(0);
		
	    playerPositions = MatchBuilder.getNewPlayerPositions(map, players);
	    
		//Set backreference for players
	    for (Player player : players) 
			player.setMatch(this);
		
	    this.calculation = new Calculation(Consts.WORLD_WIDTH, map.getHorizonLine(), players, playerPositions);
	}
	
	@Override
	public Point getPlayerPosition(Player player){
		return playerPositions.get(player);
	}
	
	/**
	 * Called when a player want to fire to the 
	 */
	@Override
	public boolean Fire(ClientInterface sender, double angle, double power)
			throws RemoteException {
		
		//Check if the right player sends the message
		if (!(sender.getPlayer().equals(this.activePlayer)))
		{
			System.out.println("Wrong player sends fire Command");
			return false;
		}
		
		//Calculate the flight path
		FlightPath path = this.calcFlightPath(sender.getPlayer(), angle, power);
		
		//Set the new active player
		this.activePlayer = this.foundNextPlayer(sender.getPlayer());
		
		//Send the flight path to the clients
		for (Player player : this.players) {
			player.getClientInterface().setNewFlightPath(path);
		}
		
		return true;
	}
	
	/**
	 * Function which will found the player which should make the next turn
	 * @param currentPlayer
	 * @return
	 * @throws RemoteException 
	 */
	private Player foundNextPlayer (Player currentPlayer) throws RemoteException
	{
		int i=0;
		for (Player player : this.players) {
			i++;
			if (player.equalsPlayer(currentPlayer))
				break;
		}
		
		if (i >= this.players.size())
			i=0;
		
		return this.players.get(i);
	}
	
	////////////////////
	//Getter + Setter//
	//////////////////
	@Override
	public GameMap getMap() throws RemoteException {
		return map;
	}
	
	@Override
	public ArrayList<Player> getPlayers() throws RemoteException {
		return players;
	}

	@Override
	public Player getActivePlayer() {
		return activePlayer;
	}

	@Override
	public void setActivePlayer(Player activePlayer) {
		this.activePlayer = activePlayer;
	}

	@Override
	public FlightPath calcFlightPath(Player source, double angle, double speed) throws RemoteException {
		return calculation.calc_flugbahn(source, angle, speed);
	}
	

}
