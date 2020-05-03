package com.services;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.rmi.SensorServerRMI;

// sensor service class
public class SensorService implements ISensorService {

	private com.rmi.SensorServerRMI sensorServerRMI;

	// default constructor to create an object of the rmi server class
	public SensorService() throws RemoteException {
		super();
		sensorServerRMI = new com.rmi.SensorServerRMI();
	}

	// method implementation to call rmi server method for inserting a sensor
	@Override
	public boolean addSensor(com.rmi.Sensor sensor) throws RemoteException, IOException {
		return sensorServerRMI.addSensor(sensor);
	}

	// method implementation to call rmi server method for updating an existing
	// sensor
	@Override
	public void updateSensor(String sensorId, com.rmi.Sensor sensor) throws RemoteException, IOException {
		sensorServerRMI.updateSensor(sensorId, sensor);
	}

	// method implementation to call rmi server method for deleting a sensor
	@Override
	public void removeSensor(String sensorId) throws RemoteException, IOException {
		sensorServerRMI.removeSensor(sensorId);
	}

	// method implementation to call rmi server method for retrieving a sensor by
	// sensor id
	@Override
	public com.rmi.Sensor getSensor(String sensorId) throws RemoteException, IOException {
		return sensorServerRMI.getSensor(sensorId);
	}

	// method implementation to call rmi server method for retrieving a arraylist of
	// all sensors in mongodb
	@Override
	public ArrayList<com.rmi.Sensor> getSensorsList() throws RemoteException, IOException {
		System.out.println(SensorServerRMI.getSensorsList());
		return SensorServerRMI.getSensorsList();
	}
}
