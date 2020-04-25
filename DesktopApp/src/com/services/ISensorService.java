package com.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.models.Sensor;

public interface ISensorService {

	public void addSensor(Sensor sensor) throws RemoteException, NotBoundException;

	public void updateSensor(String sensorId, Sensor sensor);

	public void removeSensor(String sensorId);

	public Sensor getSensor(String sensorId);

	public ArrayList<Sensor> getSensorsList();
}
