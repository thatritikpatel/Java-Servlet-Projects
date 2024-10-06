package utils;

import java.io.*;

import java.net.URL;
import java.net.HttpURLConnection;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;

public class GoogleCaptcha{
	public static boolean testReCaptcha(String reCaptchaCode) throws IOException{
		String reCaptchaURL = "https://www.google.com/recaptcha/api/siteverify";
		String params = "secret=6Lcyb5IaAAAAAA-uxqCYHOzRaPfB4fNPO2gEK6Zc&response="+reCaptchaCode;
		
		URL url = new URL(reCaptchaURL+"?"+params);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setDoOutput(true);
				
		DataOutputStream dos = new DataOutputStream(con.getOutputStream());		
		dos.flush();
		dos.close();	

		JsonReader jr = Json.createReader(con.getInputStream());
		JsonObject jo = jr.readObject();		
		
		boolean flag = jo.getBoolean("success");
		
		return flag;
	}
}