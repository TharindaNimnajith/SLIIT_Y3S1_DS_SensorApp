package com.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.rmi.*;
import com.models.Sensor;

public class SensorService implements ISensorService {

	// ArrayList<Sensor> sensors = new ArrayList<Sensor>();

	@Override
	public void addSensor(Sensor sensor) throws Exception {
		// sensors.add(sensor);
//		 Registry registry = LocateRegistry.getRegistry("localhost", 6789);
//	        
//	       com.rmi.SensorService sensors = (com.rmi.SensorService)registry.lookup("sensor");
//	       sensors.increment();

//		URL obj = new URL(url);
//	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//	    System.out.println(con.getResponseCode());
//	    
//	    if(con.getResponseCode() == 200) {
//	    	InputStream im = con.getInputStream();
//	    	StringBuffer sb = new StringBuffer();
//	    	BufferedReader br = new BufferedReader(new InputStreamReader(im));
//	    	String line = br.readLine();
//	    	while (line != null) {
//				System.out.println(line);
//				line = br.readLine();
//			}
//	    	JSONObject jsonObj = new JSONObject(line);
//	    	System.out.println(jsonObj);
//	    }

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
	public Sensor getSensor(String sensorId) throws Exception {

		String url = "http://localhost:5000/api/sensor/"+sensorId;
		URL seatURL = new URL(url);
		// Return the JSON Response from the API
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));
		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		JSONObject jsonObj = new JSONObject(jsonString.toString());
		System.out.println(jsonObj);
//		System.out.println(jsonObj.get("1"));

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

	@Override
	public ArrayList<Sensor> getSensorsList() throws Exception {
		ArrayList<Sensor> sensors = new ArrayList<Sensor>();
//		Sensor sensor = new Sensor("1", "ABC", 12, 13, 1, 6, true);
//		Sensor sensor1 = new Sensor("2", "ABC", 15, 13, 1, 1, true);
//		Sensor sensor2 = new Sensor("3", "ABC", 12, 17, 10, 1, true);
//		Sensor sensor3 = new Sensor("4", "ABC", 12, 13, 1, 2, true);
//		sensors.add(sensor);
//		sensors.add(sensor1);
//		sensors.add(sensor2);
//		sensors.add(sensor3);

		String url = "http://localhost:5000/api/sensor/";
		URL seatURL = new URL(url);
		// Return the JSON Response from the API
		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));
		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		JSONObject jsonObj = new JSONObject(jsonString.toString());
		System.out.println(jsonObj);
//		System.out.println(jsonObj.get("1"));

		for (int i = 0; i < jsonObj.length(); i++) {

			String obj = jsonObj.get(String.valueOf(i + 1)).toString();

			JSONObject jsonObj2 = new JSONObject(obj);

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

		return sensors;
	}
}
