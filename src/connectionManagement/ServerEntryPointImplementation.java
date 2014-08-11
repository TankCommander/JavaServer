package connectionManagement;

import gameManagement.MatchImplementation;

import java.rmi.RemoteException;
import java.util.LinkedList;
import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.connectionObjects.interfaces.ServerEntryPoint;

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
		MatchImplementation match = new MatchImplementation(firstPlayer.getPlayer(), secondPlayer.getPlayer());
		firstPlayer.gameObject(match);
		secondPlayer.gameObject(match);
	}
	
	
	

}
