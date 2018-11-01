package com.kjh.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonCaller {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:18080/api/");
		HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();			
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setRequestMethod("POST");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("c", "index");
		jsonObject.put("v", "index");
		
		List<String> list = new ArrayList<>();
		list.add("TYX_member_s");
		list.add("null");
		list.add("__CURSOR__");
		list.add("");
		list.add("GK");
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(list);
		jsonObject.put("jobs", jsonArray);
		jsonObject.put("ds", "jdbc/TYX");
		String json = jsonObject.toString();
		//String json = "{\"c\":\"index\",\"v\":\"index\",\"jobs\":[[\"TYX_member_s\",null,\"__CURSOR__\",\"\",\"\"]],\"ds\":\"jdbc\\/TYX\"}";
		
		httpURLConnection.setRequestProperty("Accept", "appliication/json");
		httpURLConnection.setRequestProperty("Content-type", "appliication/json");
		
		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(json.getBytes());
		outputStream.flush();
		outputStream.close();
		
		InputStream inputStream = httpURLConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String read;
		StringBuilder stringBuilder = new StringBuilder();
		while((read=bufferedReader.readLine()) != null) {
			stringBuilder.append(read);
		}
		bufferedReader.close();
		inputStream.close();
		System.out.println(stringBuilder.toString());
	}
}
