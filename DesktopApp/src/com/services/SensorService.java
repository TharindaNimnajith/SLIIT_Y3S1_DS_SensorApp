package com.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONObject;

import com.models.Sensor;

public class SensorService implements ISensorService {

	@Override
	public void addSensor(Sensor sensor) throws Exception {
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
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage() + " eror");
		}
	}

	@Override
	public void updateSensor(String sensorId, Sensor sensor) throws Exception {
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
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage() + " eror");
		}
	}

	@Override
	public void removeSensor(String sensorId) throws Exception {
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
			System.out.println("" + sb.toString());
		} else {
			System.out.println(con.getResponseMessage() + " eror");
		}
	}

	@Override
	public Sensor getSensor(String sensorId) throws Exception {
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

		BufferedReader br = new BufferedReader(new InputStreamReader(seatURL.openStream(), Charset.forName("UTF-8")));
		String readAPIResponse = " ";
		StringBuilder jsonString = new StringBuilder();
		while ((readAPIResponse = br.readLine()) != null) {
			jsonString.append(readAPIResponse);
		}
		JSONObject jsonObj = new JSONObject(jsonString.toString());

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
