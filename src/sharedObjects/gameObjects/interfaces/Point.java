package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Point extends Remote, Serializable {	

	public abstract double getX() throws RemoteException;
	public abstract void setX(double x) throws RemoteException;

	public abstract double getY() throws RemoteException;
	public abstract void setY(double y) throws RemoteException;
	
}
