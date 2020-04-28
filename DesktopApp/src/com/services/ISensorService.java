package com.services;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISensorService {

	public void addSensor(com.rmi.Sensor sensor) throws RemoteException, IOException;

	public void updateSensor(String sensorId, com.rmi.Sensor sensor) throws RemoteException, IOException;

	public void removeSensor(String sensorId) throws RemoteException, IOException;

	public com.rmi.Sensor getSensor(String sensorId) throws RemoteException, IOException;

	public ArrayList<com.rmi.Sensor> getSensorsList() throws RemoteException, IOException;
}
