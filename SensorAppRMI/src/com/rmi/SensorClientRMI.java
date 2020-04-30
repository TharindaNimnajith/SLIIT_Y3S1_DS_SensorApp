package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SensorClientRMI {

	private int clients = 0;

	public SensorClientRMI() {
		super();
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", Registry.REGISTRY_PORT);
			ISensorServerRMI iSensorServerRMI = (ISensorServerRMI) registry.lookup("rmi://localhost/server");
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

	public void displayClientNo() {
		System.out.println("Clients: " + clients);
	}
}
