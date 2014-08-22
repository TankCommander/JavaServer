package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import sharedObjects.connectionObjects.interfaces.ClientInterface;

public interface Player extends Remote, Serializable {
	
	public String getName() throws RemoteException;
	
	public double getDamage() throws RemoteException;
	public void addDamage(double damage) throws RemoteException;

	public Match getMatch() throws RemoteException;
	public void setMatch(Match match) throws RemoteException;
	
	public Point getPosition() throws RemoteException;
	public void setPosition(Point position) throws RemoteException;
	
	public double getAngele() throws RemoteException;
	public void setAngle(double angle) throws RemoteException;
	
	public ClientInterface getClientInterface() throws RemoteException;
	public void setClientInterface(ClientInterface cInterface) throws RemoteException;
	
	public String getID() throws RemoteException;
	
	public boolean comparePlayers(Player comparePlayer) throws RemoteException;

}
