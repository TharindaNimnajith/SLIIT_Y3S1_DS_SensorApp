package com.services;

import java.util.ArrayList;

import com.models.Sensor;

public class SensorService implements ISensorService {

	ArrayList<Sensor> sensors = new ArrayList<Sensor>();

	@Override
	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

	@Override
	public void updateSensor(String sensorId, Sensor sensor) {

	}

	@Override
	public void removeSensor(String sensorId) {
		for (Sensor sensor : sensors) {
			if (sensor.getSensorId().equals(sensorId)) {
				sensors.remove(sensor);
			}
		}
	}

	@Override
	public Sensor getSensor(String sensorId) {
		for (Sensor sensor : sensors) {
			if (sensor.getSensorId().equals(sensorId)) {
				return sensor;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Sensor> getSensorsList() {
//		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
//		Sensor sensor = new Sensor("1", "ABC", 12, 13, 1, 6);
//		Sensor sensor1 = new Sensor("2", "ABC", 15, 13, 1, 1);
//		Sensor sensor2 = new Sensor("3", "ABC", 12, 17, 10, 1);
//		Sensor sensor3 = new Sensor("4", "ABC", 12, 13, 1, 2);
//		sensors.add(sensor);
//		sensors.add(sensor1);
//		sensors.add(sensor2);
//		sensors.add(sensor3);
		return sensors;
	}
}
