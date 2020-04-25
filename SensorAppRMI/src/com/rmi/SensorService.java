package com.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SensorService extends Remote {

	public int getAllSensors(int a, int b) throws RemoteException;

	public int subtract(int a, int b) throws RemoteException;

	public int multiply(int a, int b) throws RemoteException;

	public int divide(int a, int b) throws RemoteException;

	public int increment() throws RemoteException;
}
