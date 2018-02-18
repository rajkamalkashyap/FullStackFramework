package com.fonepaisa.GenEPG.CommonUtil;

import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class CommonUtilTest {

	@Test
	public void testparsing() {
		/*String test = "fonepaisa.ABCDEF@indus";
		String refrence = test.substring(test.indexOf(".") + 1,
				test.indexOf("@"));*/
		//System.out.println(System.currentTimeMillis());
		String test = "123456";
		String test_id = "123456787204853405";
		System.out.print(test_id.substring(test.length()+2));
	}

	@Test
	public void testdate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				Constants.KOTAK_DATE_FORMAT);
		SimpleDateFormat timeFormat = new SimpleDateFormat(
				Constants.C_TIME_FORMAT);
		System.out.println(dateFormat.format(date));
		/*System.out.println(timeFormat.format(date));
		String msg = "12345.123.1";
		System.out.println("requested Msg " + msg);*/
	}

	@Test
	public void testSocketException(){
		try{
			throw new SocketException("Connection reset");
		}catch(SocketException e){
			System.out.print(e.getMessage());
			e.printStackTrace();
		 
		}
	}
}
