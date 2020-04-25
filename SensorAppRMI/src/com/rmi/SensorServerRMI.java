package com.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SensorServerRMI {

	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.createRegistry(6789);

			SensorServiceImplementation sensorServiceImplementation = new SensorServiceImplementation();

			SensorServiceRMI sensor = (SensorServiceRMI) UnicastRemoteObject.exportObject(sensorServiceImplementation,
					0);

			registry.rebind("sensor", sensor);

			System.out.println("Server is running");
		} catch (Exception e) {
			
		}
	}
}
