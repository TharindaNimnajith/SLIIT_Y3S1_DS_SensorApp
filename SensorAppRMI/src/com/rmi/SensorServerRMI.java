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

public class SensorServerRMI extends UnicastRemoteObject implements ISensorServerRMI {

	private static final long serialVersionUID = 1L;

	private int count = 0;

	private static ArrayList<Sensor> sensors;

	public SensorServerRMI() throws RemoteException {
		super();
	}

	@Override
	public boolean addSensor(Sensor sensor) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/";
		URL object = new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

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
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void updateSensor(String sensorId, Sensor sensor) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/" + sensorId;
		URL object = new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("PUT");

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
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
		}
	}

	@Override
	public void removeSensor(String sensorId) throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/" + sensorId;
		URL object = new URL(url);

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
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
		}
	}

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

		JSONObject jsonObj = new JSONObject(jsonString.toString());
		String obj = jsonObj.get(sensorId).toString();
		JSONObject jsonObj2 = new JSONObject(obj);

		Sensor s1 = new Sensor();
		s1.setActive(Boolean.parseBoolean(jsonObj2.get("active").toString()));
		s1.setCO2Level(Integer.parseInt(jsonObj2.get("co2Level").toString()));
		s1.setFloorNo(Integer.parseInt(jsonObj2.get("floorNo").toString()));
		s1.setRoomNo(Integer.parseInt(jsonObj2.get("roomNo").toString()));
		s1.setSensorId(jsonObj2.get("id").toString());
		s1.setSensorName(jsonObj2.get("name").toString());
		s1.setSmokeLevel(Integer.parseInt(jsonObj2.get("smokeLevel").toString()));
		return s1;
	}

	public static void getSensorsList() throws RemoteException, IOException {
		String url = "http://localhost:5000/api/sensor/";
		URL seatURL = new URL(url);
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));

		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		JSONArray jsonObj = new JSONArray(jsonString.toString());

		sensors = new ArrayList<Sensor>();
		for (int i = 0; i < jsonObj.length(); i++) {
			JSONObject jsonObj2 = (JSONObject) jsonObj.get(i);
			Sensor s1 = new Sensor();
			s1.setActive(Boolean.parseBoolean(jsonObj2.get("active").toString()));
			s1.setCO2Level(Integer.parseInt(jsonObj2.get("co2Level").toString()));
			s1.setFloorNo(Integer.parseInt(jsonObj2.get("floorNo").toString()));
			s1.setRoomNo(Integer.parseInt(jsonObj2.get("roomNo").toString()));
			s1.setSensorId(jsonObj2.get("id").toString());
			s1.setSensorName(jsonObj2.get("name").toString());
			s1.setSmokeLevel(Integer.parseInt(jsonObj2.get("smokeLevel").toString()));
			sensors.add(s1);
		}
		
		System.out.println("ABC");
	}

	@Override
	public ArrayList<Sensor> getSensors() throws RemoteException, IOException {
		return sensors;
	}

	@Override
	public synchronized int increment() throws RemoteException {
		count++;
		return count;
	}

	public static void main(String[] args) {
		System.setProperty("java.security.policy", "file:allowall.policy");
		try {
			Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			SensorServerRMI sensorServerRMI = new SensorServerRMI();
			registry.rebind("rmi://localhost/server", sensorServerRMI);
			System.out.println("Sensor server started...");
			while (true) {
				Thread.sleep(15000);
				getSensorsList();
			}
		} catch (RemoteException remoteException) {
			System.err.println(remoteException.getMessage());
			remoteException.printStackTrace();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}
	}
}
