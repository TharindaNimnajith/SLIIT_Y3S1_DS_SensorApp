package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.models.Sensor;

public class SensorService implements ISensorService {

	@Override
	public void addSensor(Sensor sensor) throws IOException {
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
	public void updateSensor(String sensorId, Sensor sensor) throws IOException {
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
	public void removeSensor(String sensorId) throws IOException {
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
	public Sensor getSensor(String sensorId) throws IOException {
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

	@Override
		public ArrayList<Sensor> getSensorsList() throws Exception {
			ArrayList<Sensor> sensors = new ArrayList<Sensor>();
			String url = "http://localhost:5000/api/sensor/";
			URL seatURL = new URL(url);
			// Return the JSON Response from the API
			BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));
			String readAPIResponse = " ";
			StringBuilder jsonString = new StringBuilder();
			while ((readAPIResponse = br.readLine()) != null) {
				jsonString.append(readAPIResponse);
			}
	//		JSONObject jsonObj = new JSONObject();
			
	
			JSONArray jsonObj= new JSONArray(jsonString.toString());
			System.out.println(jsonObj);
	
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
	
			return sensors;
		}
}
