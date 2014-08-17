package sharedObjects.gameObjects.interfaces;

import java.rmi.RemoteException;

public interface TimePoint extends Point {
	
	public double getT ()  throws RemoteException;
	public void setT(double t) throws RemoteException;
}
