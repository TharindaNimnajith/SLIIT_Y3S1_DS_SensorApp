package com.services;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SensorService implements ISensorService {

	private com.rmi.SensorServerRMI sensorServerRMI;

	private ArrayList<com.rmi.Sensor> sensorsList;

	public SensorService() throws RemoteException {
		super();
		sensorServerRMI = new com.rmi.SensorServerRMI();
	}

	@Override
	public boolean addSensor(com.rmi.Sensor sensor) throws RemoteException, IOException {
		return sensorServerRMI.addSensor(sensor);
	}

	@Override
	public void updateSensor(String sensorId, com.rmi.Sensor sensor) throws RemoteException, IOException {
		sensorServerRMI.updateSensor(sensorId, sensor);
	}

	@Override
	public void removeSensor(String sensorId) throws RemoteException, IOException {
		sensorServerRMI.removeSensor(sensorId);
	}

	@Override
	public com.rmi.Sensor getSensor(String sensorId) throws RemoteException, IOException {
		return sensorServerRMI.getSensor(sensorId);
	}

	@Override
	public ArrayList<com.rmi.Sensor> getSensorsList() throws RemoteException, IOException {
		while (true) {
			try {
				Thread.sleep(15000);
				sensorsList = sensorServerRMI.getSensorsList();
				return sensorsList;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
