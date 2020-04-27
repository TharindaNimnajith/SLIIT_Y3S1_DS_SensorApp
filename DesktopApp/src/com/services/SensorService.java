package com.services;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class SensorService implements ISensorService {

	com.rmi.SensorServerRMI sensorServerRMI;

	public SensorService() throws RemoteException {
		super();
		sensorServerRMI = new com.rmi.SensorServerRMI();
	}

	@Override
	public void addSensor(com.rmi.Sensor sensor) throws RemoteException, IOException {
		sensorServerRMI.addSensor(sensor);
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
		return sensorServerRMI.getSensorsList();
	}

	@Override
	public ArrayList<com.rmi.Sensor> getActiveSensorsList() throws RemoteException, IOException {
		return sensorServerRMI.getActiveSensorsList();
	}
}
