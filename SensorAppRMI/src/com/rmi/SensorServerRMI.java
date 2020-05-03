package com.rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// rmi server class
public class SensorServerRMI extends UnicastRemoteObject implements ISensorServerRMI {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	public static ArrayList<Sensor> sensors;

	public SensorServerRMI() throws RemoteException {
		super();
	}

	// method implementation to insert a sensor to mongodb
	@Override
	public boolean addSensor(Sensor sensor) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/";
		URL object = new URL(url);

		// creating http post request to send to rest api
		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		// adding attributes of a sensor and creating a json object
		JSONObject obj = new JSONObject();
		obj.put("smokeLevel", sensor.getSmokeLevel());
		obj.put("co2Level", sensor.getCO2Level());
		obj.put("id", sensor.getSensorId());
		obj.put("floorNo", sensor.getFloorNo());
		obj.put("name", sensor.getSensorName());
		obj.put("roomNo", sensor.getRoomNo());
		obj.put("active", sensor.isActive());

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(obj.toString());
		wr.flush();

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			// if http response code for OK (200) returns from the rest api
			// if the inserting operation is successful
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			// return true if the inserting operation is successful
			return true;
		} else {
			// if http response code for OK (200) does not return from the rest api
			// return false if the inserting operation is not successful
			return false;
		}
	}

	// method implementation to update a sensor in mongodb
	@Override
	public void updateSensor(String sensorId, Sensor sensor) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/" + sensorId;
		URL object = new URL(url);

		// creating http put request to send to rest api
		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("PUT");

		// adding attributes of a sensor and creating a json object
		JSONObject obj = new JSONObject();
		obj.put("smokeLevel", sensor.getSmokeLevel());
		obj.put("co2Level", sensor.getCO2Level());
		obj.put("id", sensor.getSensorId());
		obj.put("floorNo", sensor.getFloorNo());
		obj.put("name", sensor.getSensorName());
		obj.put("roomNo", sensor.getRoomNo());
		obj.put("active", sensor.isActive());

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(obj.toString());
		wr.flush();

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			// if http response code for OK (200) returns from the rest api
			// if the update operation is successful
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
		}
	}

	// method implementation to delete a sensor from mongodb
	@Override
	public void removeSensor(String sensorId) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/" + sensorId;
		URL object = new URL(url);

		// creating http delete request to send to rest api
		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("DELETE");

		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.flush();

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			// if http response code for OK (200) returns from the rest api
			// if the delete operation is successful
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
		}
	}

	// method implementation to retrieve a sensor from mongodb
	@Override
	public Sensor getSensor(String sensorId) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/" + sensorId;
		URL seatURL = new URL(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));

		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}

		// filtering the correct sensor instance from sensor id
		// converting the retrieved json object data to correct format to get sensor
		// details
		JSONObject jsonObj = new JSONObject(jsonString.toString());
		String obj = jsonObj.get(sensorId).toString();
		JSONObject jsonObj2 = new JSONObject(obj);

		// assigning the sensor details to a sensor object from the json object
		Sensor s1 = new Sensor();
		s1.setActive(Boolean.parseBoolean(jsonObj2.get("active").toString()));
		s1.setCO2Level(Integer.parseInt(jsonObj2.get("co2Level").toString()));
		s1.setFloorNo(Integer.parseInt(jsonObj2.get("floorNo").toString()));
		s1.setRoomNo(Integer.parseInt(jsonObj2.get("roomNo").toString()));
		s1.setSensorId(jsonObj2.get("id").toString());
		s1.setSensorName(jsonObj2.get("name").toString());
		s1.setSmokeLevel(Integer.parseInt(jsonObj2.get("smokeLevel").toString()));
		// return the sensor object
		return s1;
	}

	// method implementation to retrieve all sensors from mongodb
	public static ArrayList<Sensor> getSensorsList() throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/";
		URL seatURL = new URL(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));

		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		// converting the the rest api response to a json array
		JSONArray jsonObj = new JSONArray(jsonString.toString());

		sensors = new ArrayList<Sensor>();
		for (int i = 0; i < jsonObj.length(); i++) {
			// retrieving sensors to json objects from json array
			JSONObject jsonObj2 = (JSONObject) jsonObj.get(i);
			// assigning sensor details to a sensor object from the json object
			Sensor s1 = new Sensor();
			s1.setActive(Boolean.parseBoolean(jsonObj2.get("active").toString()));
			s1.setCO2Level(Integer.parseInt(jsonObj2.get("co2Level").toString()));
			s1.setFloorNo(Integer.parseInt(jsonObj2.get("floorNo").toString()));
			s1.setRoomNo(Integer.parseInt(jsonObj2.get("roomNo").toString()));
			s1.setSensorId(jsonObj2.get("id").toString());
			s1.setSensorName(jsonObj2.get("name").toString());
			s1.setSmokeLevel(Integer.parseInt(jsonObj2.get("smokeLevel").toString()));
			// adding each sensor object to an arraylist
			sensors.add(s1);
		}
		System.out.println(sensors);
		// return all retrieved sensors in an arraylist
		return sensors;
	}

	// method implementation to increment the number of rmi clients
	@Override
	public synchronized int increment() throws RemoteException {
		count++;
		return count;
	}

	// implementation of the main method
	public static void main(String[] args) {
		// including the allowall.policy file giving all permissions
		System.setProperty("java.security.policy", "file:allowall.policy");
		try {
			// creating and starting the 1099 registry port and binding the created registry
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			SensorServerRMI sensorServerRMI = new SensorServerRMI();
			registry.rebind("rmi://localhost/server", sensorServerRMI);
			System.out.println("Sensor server started...");
		} catch (RemoteException remoteException) {
			System.err.println(remoteException.getMessage());
			remoteException.printStackTrace();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}
		// creating a thread using Thread class and Runnable interface
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						// checking the sensor status every 15 seconds to get the up to date readings
						// from the rest api
						Thread.sleep(15000);
						getSensorsList();
					} catch (RemoteException remoteException) {
						System.err.println(remoteException.getMessage());
						remoteException.printStackTrace();
					} catch (IOException iOException) {
						System.err.println(iOException.getMessage());
						iOException.printStackTrace();
					} catch (InterruptedException interruptedException) {
						System.err.println(interruptedException.getMessage());
						interruptedException.printStackTrace();
					} catch (Exception exception) {
						System.err.println(exception.getMessage());
						exception.printStackTrace();
					}
				}
			}
		});
		// starting the thread
		t.start();
	}
}
