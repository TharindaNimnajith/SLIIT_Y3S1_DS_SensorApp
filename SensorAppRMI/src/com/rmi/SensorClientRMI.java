package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

// rmi client class
public class SensorClientRMI {

	private int clients = 0;

	public SensorClientRMI() {
		super();
		try {
			// accessing the 1099 registry port
			Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
			ISensorServerRMI iSensorServerRMI = (ISensorServerRMI) registry.lookup("rmi://localhost/server");
			// incrementing the number of clients
			clients = iSensorServerRMI.increment();
		} catch (RemoteException remoteException) {
			System.err.println(remoteException.getMessage());
			remoteException.printStackTrace();
		} catch (NotBoundException notBoundException) {
			System.err.println(notBoundException.getMessage());
			notBoundException.printStackTrace();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}
	}

	// method implementation to print the client number
	public void displayClientNo() {
		System.out.println("Clients: " + clients);
	}
}
