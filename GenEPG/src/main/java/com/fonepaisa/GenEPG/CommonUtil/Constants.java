package com.fonepaisa.GenEPG.CommonUtil;

public class Constants {
	

	//HTTP request related constants
	public static final String UTF8 					= "utf-8";
	
	
	//DB insert and update flags 
	public static final String INSERT_FLAG  			= "I";
	public static final String UPDTAE_FLAG  			= "U";
	
	
	//Convertion constants 
	public static final float MILITOMINS				= 60000;
	public static final int TIMEOUT_LATENCY			    = 1;
	
	//Deferred results hashmap appends 
	public static final String DEFFERED_OBJ		 		= "DO";
	public static final String PAYMENT_OBJECT		 	= "PO";
	
	
	
	//Date formats 
	public static final String C_DATE_TIME_FORMAT		= "yyyy-MM-dd HH:MM:ss";
	public static final String KOTAK_DATE_FORMAT		= "yyyy-MM-dd";
	public static final String C_DATE_FORMAT		  	= "MMdd";
	public static final String C_TIME_FORMAT		  	= "HHMMss";
	
	//Currency codes 
	public static final String INR					  	= "INR";
	
	//Message Log statuses 
	public static final Character REQUESTED				= 'I';
	public static final Character RESPONDED_SUCCESS		= 'R';
	public static final Character FAILED				= 'F';

	//Exception handlings 
	public static final String CONNECTION_RESET_ERROR	= "HTTP transport error: java.net.SocketException: Connection reset";
    public static final String BANK_ACCT_DETAIL_TAG		 			= "acct_";
	public static final String RESULT_CODE 							= "result_code";
	
	
	
}


