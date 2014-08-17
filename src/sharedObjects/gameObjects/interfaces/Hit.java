package sharedObjects.gameObjects.interfaces;

import java.rmi.RemoteException;


public interface Hit extends TimePoint{

	public abstract Player getTarget() throws RemoteException;
	public abstract void setTarget(Player target) throws RemoteException;

	public abstract double getPercent() throws RemoteException;
	public abstract void setPercent(double percent) throws RemoteException;

}