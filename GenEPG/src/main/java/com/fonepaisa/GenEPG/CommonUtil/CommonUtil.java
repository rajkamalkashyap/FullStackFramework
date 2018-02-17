package com.fonepaisa.GenEPG.CommonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fonepaisa.GenEPG.Exception.GenEPGException;
import com.fonepaisa.GenEPG.WEBRequest.GenEPGHttpRequestHandler;

public class CommonUtil {
	static Logger log = Logger.getLogger(CommonUtil.class.getName());
	public static String testMethod(){
		return "SUCCESS";
	}
	
	public static String decimalString(double amt) {
		DecimalFormat df2 = new DecimalFormat("###.##");
		return df2.format(amt);
	}
	public static String createJsonStrFromObj(Object message) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(message);
	}
	public static JSONObject createJsonObjFromObj(Object message){
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json_str 	= mapper.writeValueAsString(message);
			JSONObject json 	= new JSONObject();
			JSONParser parser   = new JSONParser();
			json = (JSONObject) parser.parse(json_str);
			return json;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Long deferTimeoutInMiliSecs(Integer timeout){
		int timeoutInMins     = timeout + Constants.TIMEOUT_LATENCY;
		int timeoutMiliSecs   = timeoutInMins * 60 * 1000;
		return Long.valueOf(timeoutMiliSecs);
	}
	
	public static String generateRandomString(int length){
		String arr[] = {"A","B","C","D","E","F","G","H","I","J","K",
                "L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "1","2","3","4","5","6","7","8","9","0"};
		String random_num = "";
		for(int i=0;i<length;i++){
			random_num += arr[(int)(Math.round(Math.random()*35))];
		}
		return random_num;
	}
	public static JSONArray readJsonArrayString(String jsonString) throws ParseException   {
        JSONParser parser = new JSONParser();
        JSONArray outJson = new JSONArray();
        Object obj = parser.parse(jsonString);

        outJson = (JSONArray) obj;

        return outJson;
	}

	public static String getStringValueFromJson(JSONObject responseJson, String key) {
		String value = (String)responseJson.get(key);
		if (value == null){
			value="";
		}
		return value;
	}
	public static double getDoubleValueFromJson(JSONObject responseJson,
			String key) {
		String value = (String)responseJson.get(key);
		if (value == null || value.isEmpty()==true){
			value="0.0";
		}
		return Double.parseDouble(value);
	}
	
	public static String getRRN(String date, String time, String stan) {
		String rrn=date+time.substring(0, Math.min(time.length(),2))+stan;
		return rrn;
	}

	public static boolean isSet(String inp){
		if (inp == null || inp.isEmpty()==true){
			return false;
		}
		return true;
	}

}
