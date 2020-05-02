package com.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

// interface for the rmi server with method declarations
public interface ISensorServerRMI extends Remote {

	public boolean addSensor(Sensor sensor) throws RemoteException, IOException;

	public void updateSensor(String sensorId, Sensor sensor) throws RemoteException, IOException;

	public void removeSensor(String sensorId) throws RemoteException, IOException;

	public Sensor getSensor(String sensorId) throws RemoteException, IOException;

	public ArrayList<Sensor> getSensors() throws RemoteException, IOException;

	public int increment() throws RemoteException;
}
