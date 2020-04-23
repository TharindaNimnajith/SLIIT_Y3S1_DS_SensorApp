package com.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class SensorServer extends UnicastRemoteObject implements SensorService {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	public SensorServer() throws RemoteException {
		super();
	}

	public synchronized int increment() {
		count++;
		return count;
	}

	public int add(int a, int b) throws RemoteException {
		System.out.println("Adding " + a + " and " + b + " in the Server");
		return a + b;
	}

	public int subtract(int a, int b) throws RemoteException {
		System.out.println("Subtracting " + a + " and " + b + " in the Server");
		return a - b;
	}

	public int multiply(int a, int b) throws RemoteException {
		System.out.println("Multiplying " + a + " and " + b + " in the Server");
		return a * b;
	}

	public int test(int a) {
		System.out.println("this is a test");
		return 0;
	}

	public int divide(int a, int b) throws RemoteException {
		System.out.println("Dividing " + a + " and " + b + " in the Server");
		for (double i = 0; i < 10000000000000000.0; i++) {
			System.out.println("I'm doing something that takes a long time.");
		}
		return a / b;
	}

	public static void main(String[] args) {
		System.setProperty("java.security.policy", "file:allowall.policy");
		try {
			SensorServer svr = new SensorServer();
			Registry registry = LocateRegistry.getRegistry();
			registry.bind("SensorService", svr);
			System.out.println("Sensor service started...");
		} catch (RemoteException re) {
			System.err.println(re.getMessage());
		} catch (AlreadyBoundException abe) {
			System.err.println(abe.getMessage());
		}
	}
}
