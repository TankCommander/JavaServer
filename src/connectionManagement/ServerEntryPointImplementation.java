package connectionManagement;

import gameManagement.MatchImplementation;

import java.rmi.RemoteException;
import java.util.LinkedList;
import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.connectionObjects.interfaces.ServerEntryPoint;

public class ServerEntryPointImplementation implements ServerEntryPoint {
	private LinkedList<ClientInterface> waitingClients = new LinkedList<ClientInterface>();
	
	@Override
	public void registerClient (ClientInterface client) throws RemoteException {
		if (waitingClients.size() >= 1)
		{
			System.out.println("Found Partner");
			this.startGame(waitingClients.poll(), client);
		}
		else
		{
			System.out.println("Found NO Partner");
			waitingClients.addLast(client);
		}

	}
	
	private void startGame (ClientInterface firstPlayer, ClientInterface secondPlayer) throws RemoteException
	{
		System.out.println("StartGame");
		MatchImplementation match = new MatchImplementation();
		firstPlayer.gameObject(match);
		secondPlayer.gameObject(match);
	}
	
	
	

}
