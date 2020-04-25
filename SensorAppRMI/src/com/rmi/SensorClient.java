package com.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SensorClient {
	
	public static void main(String[] args) {
		System.setProperty("java.security.policy", "file:allowall.policy");
		SensorService sensorService = null;
		try {
			sensorService = (SensorService) Naming.lookup("//localhost/SensorService");
			sensorService.increment();
			sensorService.insertSensor();
			sensorService.updateSensor();
			sensorService.deleteSensor();
			sensorService.getSensor();
			sensorService.getAllSensors();
			sensorService.getActiveSensors();
		} catch (NotBoundException notBoundException) {
			System.err.println(notBoundException.getMessage());
		} catch (MalformedURLException malformedURLException) {
			System.err.println(malformedURLException.getMessage());
		} catch (RemoteException remoteException) {
			System.err.println(remoteException.getMessage());
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}
}
