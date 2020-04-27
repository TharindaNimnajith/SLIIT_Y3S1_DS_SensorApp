package com.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SensorClientRMI {

	public SensorClientRMI() {
		super();
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 1099);
			ISensorServerRMI iSensorServerRMI = (ISensorServerRMI) registry.lookup("rmi://localhost/server");
			System.out.println("Clients: " + iSensorServerRMI.increment());
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

	public static void main(String[] args) {
		//
	}
}
