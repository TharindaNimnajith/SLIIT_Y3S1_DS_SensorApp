package com.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import com.rmi.*;
import com.models.Sensor;

public class SensorService implements ISensorService {

	//ArrayList<Sensor> sensors = new ArrayList<Sensor>();

	@Override
	public void addSensor(Sensor sensor) throws RemoteException, NotBoundException {
		//sensors.add(sensor);
		 Registry registry = LocateRegistry.getRegistry("localhost", 6789);
	        
	       com.rmi.SensorService sensors = (com.rmi.SensorService)registry.lookup("sensor");
	       sensors.increment();
	}

	@Override
	public void updateSensor(String sensorId, Sensor sensor) {

	}

	@Override
	public void removeSensor(String sensorId) {
//		for (Sensor sensor : sensors) {
//			if (sensor.getSensorId().equals(sensorId)) {
//				sensors.remove(sensor);
//			}
//		}
	}

	@Override
	public Sensor getSensor(String sensorId) {
//		for (Sensor sensor : sensors) {
//			if (sensor.getSensorId().equals(sensorId)) {
//				return sensor;
//			}
//		}
		return null;
	}

	@Override
	public ArrayList<Sensor> getSensorsList() {
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
		Sensor sensor = new Sensor("1", "ABC", 12, 13, 1, 6, true);
		Sensor sensor1 = new Sensor("2", "ABC", 15, 13, 1, 1, true);
		Sensor sensor2 = new Sensor("3", "ABC", 12, 17, 10, 1, true);
		Sensor sensor3 = new Sensor("4", "ABC", 12, 13, 1, 2, true);
		sensors.add(sensor);
		sensors.add(sensor1);
		sensors.add(sensor2);
		sensors.add(sensor3);
		return sensors;
	}
}
