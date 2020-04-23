package com.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SensorClient {
	
	public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:allowall.policy");
        SensorService service = null;
        try {
            service = (SensorService) Naming.lookup("//localhost/SensorService");
            System.out.println("Clients: " + service.increment());
            System.out.println("Add : " + service.add(2, 2));
            System.out.println("Subtract : " + service.subtract(5, 2));
            System.out.println("Multiply : " + service.multiply(2, 6));
            System.out.println("Divide : " + service.divide(4, 2));
        } catch (NotBoundException ex) {
            System.err.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (RemoteException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
