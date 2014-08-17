package sharedObjects.gameObjects.interfaces;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface FlightPath extends Remote, Serializable {

	public abstract ArrayList<TimePoint> getTimePoints() throws RemoteException;
	public abstract void setTimePoints(ArrayList<TimePoint> timePoints) throws RemoteException;

	public abstract ArrayList<Hit> getHits() throws RemoteException;
	public abstract void setHits(ArrayList<Hit> hits) throws RemoteException;

	public abstract Player getOrigin() throws RemoteException;
	public abstract void setOrigin(Player origin) throws RemoteException;
	
	public Point getStartPoint() throws RemoteException;
	
	public void setHit(Hit hit) throws RemoteException;

}