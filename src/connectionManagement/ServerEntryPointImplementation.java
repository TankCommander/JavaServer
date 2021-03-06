package connectionManagement;

import gameManagement.Consts;
import gameManagement.MatchImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import sharedObjects.connectionObjects.interfaces.ClientInterface;
import sharedObjects.connectionObjects.interfaces.ServerEntryPoint;
import sharedObjects.gameObjects.interfaces.Match;
import sharedObjects.gameObjects.interfaces.Player;

public class ServerEntryPointImplementation implements ServerEntryPoint {
	
	private Hashtable<Player, Long> keepAliveMap = new Hashtable<Player, Long>();

	private void broadcastPlayerLost(Player lostPlayer) throws RemoteException {
		Match match = null;
		
		for (Player player : keepAliveMap.keySet()) {
			try {
				match = player.getMatch();
				break;
			} catch (RemoteException e) {
				synchronized (keepAliveMap) {
					keepAliveMap.remove(player);
				}
			}
		}
		for (Player player : match.getPlayers()) {
				try {
					player.getClientInterface().connectionLost(true);
				} catch (Exception e) {
					
				}
		}
	};
	
	public ServerEntryPointImplementation(){
		Timer keepAliveTimer = new Timer();
		keepAliveTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				for (Entry<Player, Long> entry : keepAliveMap.entrySet()) {
					if (System.currentTimeMillis() > (entry.getValue() + Consts.TIME_OUT_TIME)){
						System.out.println("Player lost Conneciton");
						try {
							broadcastPlayerLost(entry.getKey());
						} catch (RemoteException e) {
							System.out.println(e.getLocalizedMessage());
						}
					}
				} 				
			}
		}, 0, 1000);
	}
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

	@Override
	public void receiveKeepAlive(Player player) throws RemoteException {
		synchronized (keepAliveMap) {
			keepAliveMap.put(player, System.currentTimeMillis());
		}
	}
	
	
	

}
