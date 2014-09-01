package control;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import sharedObjects.connectionObjects.interfaces.ServerEntryPoint;

import connectionManagement.ServerEntryPointImplementation;

public class StartServer {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		//Start the Remote Registry
	    LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
	    
	    //Add the needed function to the remote registry
	    ServerEntryPointImplementation impl = new ServerEntryPointImplementation();
	    ServerEntryPoint stub = (ServerEntryPoint) UnicastRemoteObject.exportObject(impl, 0);
	    
	    //Set the Logging
	    //RemoteServer.setLog(System.out);
	    
	    Registry registry = LocateRegistry.getRegistry();
	    registry.rebind( "ServerEntryPoint", stub );
	    
	    
	    System.out.println( "ServerEntryPoint" );

	}

}
