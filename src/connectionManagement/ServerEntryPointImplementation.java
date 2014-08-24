package connectionManagement;

import gameManagement.MatchImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.connectionObjects.interfaces.ServerEntryPoint;
import sharedObjects.gameObjects.interfaces.Player;

public class ServerEntryPointImplementation implements ServerEntryPoint {
	
	/**
	 * List with the clients, which waiting for a connection
	 */
	private LinkedList<ClientInterface> waitingClients = new LinkedList<ClientInterface>();
	
	
	/**
	 * Function called, when a client is connecting the Server
	 */
	@Override
	public boolean registerClient (ClientInterface client) throws RemoteException {
		System.out.println("Client connected");
		if (waitingClients.size() >= 1)
		{
			this.startGame(waitingClients.poll(), client);
			return true;
		}
		else
		{
			waitingClients.addLast(client);
			return false;
		}

	}
	
	private void startGame (ClientInterface firstPlayer, ClientInterface secondPlayer) throws RemoteException
	{
		System.out.println("StartGame");
		MatchImpl match = new MatchImpl(new ArrayList<Player>(Arrays.asList(firstPlayer.getPlayer(), secondPlayer.getPlayer())));
		firstPlayer.setGameObjects(match);
		secondPlayer.setGameObjects(match);
	}
	
	
	

}
