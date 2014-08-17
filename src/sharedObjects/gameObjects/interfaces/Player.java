package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

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

}
