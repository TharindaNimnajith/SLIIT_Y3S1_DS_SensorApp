package com.rmi;

import java.rmi.RemoteException;

public class SensorServiceImplementation implements SensorService {

	private int count = 0;

	public SensorServiceImplementation() throws RemoteException {
		super();
	}

	public synchronized void increment() {
		count++;
		System.out.println(count);
	}

	@Override
	public void insertSensor() throws RemoteException {

	}

	@Override
	public void updateSensor() throws RemoteException {

	}

	@Override
	public void deleteSensor() throws RemoteException {

	}

	@Override
	public void getSensor() throws RemoteException {

	}

	@Override
	public void getAllSensors() throws RemoteException {

	}

	@Override
	public void getActiveSensors() throws RemoteException {

	}
}
