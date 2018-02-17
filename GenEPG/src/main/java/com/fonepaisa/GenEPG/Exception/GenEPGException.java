package com.fonepaisa.GenEPG.Exception;

import java.util.HashMap;

import org.apache.log4j.Logger;


public class GenEPGException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(GenEPGException.class.getName());
	
	//Add all The Error Codes here 
	public static final String SUCCESS 								= "0";
	public static final String NOT_SETUP 							= "1";
	public static final String VALIDATION_FAILED					= "2";
	public static final String HTTP_REQUEST_FAILURE					= "3";
	public static final String BANK_DETAILS_NOT_FOUND				= "14";
	public static final String REQUEST_FAILED						= "15";
	
	public static final String UNEXPECTED_SERVICE_ERROR 			= "998";
	public static final String SYSTEM_ERROR 						= "999";
	
	//global Hash map to store error code and description
	private static final HashMap<String,String> response_hashmap;
	
	//set error descriptions 
	static {
		response_hashmap=new HashMap<String, String>();
		
		//Add new error description for the error code added above 
		response_hashmap.put(SUCCESS								, "Success");
		response_hashmap.put(NOT_SETUP								, "Data not setup for the transaction");
		response_hashmap.put(VALIDATION_FAILED						, "Not a valid Request");
		response_hashmap.put(HTTP_REQUEST_FAILURE					, "Failure in  channel");
		response_hashmap.put(BANK_DETAILS_NOT_FOUND					, "Bank Details not found .Internal Error!! ");
		response_hashmap.put(REQUEST_FAILED							, "Request Failed");
		
		response_hashmap.put(UNEXPECTED_SERVICE_ERROR				, "Unexpected Failure occured ");
		response_hashmap.put(SYSTEM_ERROR							, "Failed to process Please try again later");	
	}
	
	//Constructor to throw a new error
	public GenEPGException(String error) {
		super(error);
	}

	// function to Return the error code 
	//By default 5000 will be added to the number when it is returned 
	public static String getCode(String message){

		try {
			log.error("Integer" + Integer.parseInt(message));

			return String.valueOf(8000 + Integer.parseInt(message));
		} catch (Exception e) {
			log.error("UNKNOWN Message" + message);
			e.printStackTrace();
			return "-1";
		}
	} 
	
	//Function to Return the error description for a particular Error code 
	public static String getDescription(String message) {
		try {
			return response_hashmap.get(message);
		} catch (Exception e) {
			log.error("UNKNOWN Message" + message);
			e.printStackTrace();
			return "Unknown Error";
		}
	}
}
