package com.fonepaisa.GenEPG.Handler.YBL;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fonepaisa.GenEPG.CommonUtil.Constants;
import com.fonepaisa.GenEPG.DTO.GetBalanceReqDTO;
import com.fonepaisa.GenEPG.DTO.SessionObj;
import com.fonepaisa.GenEPG.DTO.TranInqDTO;
import com.fonepaisa.GenEPG.DTO.TransferReqDTO;
import com.fonepaisa.GenEPG.YBL.WSDL.FundsTransferByCustomerService2;

public class YBLHandlerTest {

	@Test
	public void testTransfer(){
		   try{
			   System.out.println("Client data fetched");
			   SessionObj sessionObj = new SessionObj();

			   sessionObj.put(Constants.PARTNER_URL, 		"https://uatsky.yesbank.in:444/app/uat/ssl/fundsTransferByCustomerSevice2");
			   sessionObj.put(Constants.USERNAME,			"testclient");
			   sessionObj.put(Constants.PASSWORD,			"test@123");
			   sessionObj.put(Constants.CLIENT_ID, 			"44f0178b-9537-414c-bafd-8b2f99a9c6af");
			   sessionObj.put(Constants.CLIENT_SECRET_KEY,  "L3qH4fB1nF7kM6dM3iK5jQ1cP3gI8eV6wS3eH4eP4wW0sB4lO8");
			   Map<String, List<String>> headers = new HashMap<String, List<String>>();
			   
			   headers.put("X-IBM-Client-Id", 	   Collections.singletonList((String)sessionObj.get(Constants.CLIENT_ID)));
			   headers.put("X-IBM-Client-Secret",  Collections.singletonList((String)sessionObj.get(Constants.CLIENT_SECRET_KEY)));
		   	   sessionObj.put(Constants.HEADER_DATA, 		headers);
		   	   System.out.println("Client data fetched");
		   	   YBLUtil.setSystemPorps();
		   	   String key_file 						= "test.jks";
		   	   String key_pass 						= "fonepaisa";
		   	   String key_path						="src/main/resources"+"/"+key_file;
		   	   YBLUtil.setClientCertificate(key_path, key_pass);
		   	   

		   	   System.out.println("setting headers done");

		   	   System.out.println("System props set ");
		   	   Path currentRelativePath = Paths.get("");
		   	   String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";
	
		   	   URL url 		 						= new URL("file:///" + wsdlFilePath);
		   	   System.out.println("file URL "+url.getPath());
		   	   FundsTransferByCustomerService2 client = YBLUtil.prepareYblWsdlObj(url);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   
		   	   client = YBLUtil.setHeaders(sessionObj);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   System.out.println("headers set ");
		   	   
		   	   TransferReqDTO message                 = new TransferReqDTO();
		   	   message.setDebit_acc_no("000380800000781");
		   	   message.setReq_id(System.currentTimeMillis()+"FP");
		   	   message.setCust_id("299915");
		   	   message.setBene_name("Quantiguous Solutions");
		   	   message.setBene_acc_no("026291800001191");
		   	   message.setBene_ifsc("HDFC0000001");
		   	   message.setBene_address1("...");
		   	   message.setBene_mbl_num("7204853405");
		   	   message.setTran_amt(100.00);
		   	   message.setTran_type("N");
		   	   message.setRemarks("Hello");
		   	   message.setRequest_type("O");
		   	   YBLUtil.prepareTransferReqObj(message,sessionObj);
		   	   System.out.println("session object set "+sessionObj.toString());
		   	   message  = YBLUtil.ReqAndRespTransfer(message, sessionObj);
		   	   System.out.println("message processed "+message.toString());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
	
	@Test
	public void testStatus(){
		   try{
			   System.out.println("Client data fetched");
			   SessionObj sessionObj = new SessionObj();

			   sessionObj.put(Constants.PARTNER_URL, 		"https://uatsky.yesbank.in:444/app/uat/ssl/fundsTransferByCustomerSevice2");
			   sessionObj.put(Constants.USERNAME,			"testclient");
			   sessionObj.put(Constants.PASSWORD,			"test@123");
			   sessionObj.put(Constants.CLIENT_ID, 			"44f0178b-9537-414c-bafd-8b2f99a9c6af");
			   sessionObj.put(Constants.CLIENT_SECRET_KEY,  "L3qH4fB1nF7kM6dM3iK5jQ1cP3gI8eV6wS3eH4eP4wW0sB4lO8");
			   Map<String, List<String>> headers = new HashMap<String, List<String>>();
			   
			   headers.put("X-IBM-Client-Id", 	   Collections.singletonList((String)sessionObj.get(Constants.CLIENT_ID)));
			   headers.put("X-IBM-Client-Secret",  Collections.singletonList((String)sessionObj.get(Constants.CLIENT_SECRET_KEY)));
		   	   sessionObj.put(Constants.HEADER_DATA, 		headers);
		   	   System.out.println("Client data fetched");
		   	   YBLUtil.setSystemPorps();
		   	   String key_file 						= "test.jks";
		   	   String key_pass 						= "fonepaisa";
		   	   String key_path						= "src/main/resources"+"/"+key_file;
		   	   YBLUtil.setClientCertificate(key_path, key_pass);
		   	   

		   	   System.out.println("setting headers done");

		   	   System.out.println("System props set ");
		   	   Path currentRelativePath = Paths.get("");
		   	   String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";
	
		   	   URL url 		 						= new URL("file:///" + wsdlFilePath);
		   	   System.out.println("file URL "+url.getPath());
		   	   FundsTransferByCustomerService2 client = YBLUtil.prepareYblWsdlObj(url);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   
		   	   client = YBLUtil.setHeaders(sessionObj);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   System.out.println("headers set ");
		   	   
		   	   TranInqDTO message                 = new TranInqDTO();
		   	   message.setPg_txn_id("1505997053874FP");
		   	   message.setCust_id("299915");
		   	   YBLUtil.prepareChkStatusObj(message,sessionObj);
		   	   System.out.println("session object set "+sessionObj.toString());
		   	   message  = YBLUtil.ReqAndRespChkSTatus(message, sessionObj);
		   	   System.out.println("message processed "+message.toString());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
	@Test
	public void testBalance(){
		   try{ 
			   System.out.println("Client data fetched");
			   SessionObj sessionObj = new SessionObj();

			   sessionObj.put(Constants.PARTNER_URL, 		"https://uatsky.yesbank.in:444/app/uat/ssl/fundsTransferByCustomerSevice2");
			   sessionObj.put(Constants.USERNAME,			"testclient");
			   sessionObj.put(Constants.PASSWORD,			"test@123");
			   sessionObj.put(Constants.CLIENT_ID, 			"44f0178b-9537-414c-bafd-8b2f99a9c6af");
			   sessionObj.put(Constants.CLIENT_SECRET_KEY,  "L3qH4fB1nF7kM6dM3iK5jQ1cP3gI8eV6wS3eH4eP4wW0sB4lO8");
			   Map<String, List<String>> headers = new HashMap<String, List<String>>();
			   
			   headers.put("X-IBM-Client-Id", 	   Collections.singletonList((String)sessionObj.get(Constants.CLIENT_ID)));
			   headers.put("X-IBM-Client-Secret",  Collections.singletonList((String)sessionObj.get(Constants.CLIENT_SECRET_KEY)));
		   	   sessionObj.put(Constants.HEADER_DATA, 		headers);
		   	   System.out.println("Client data fetched");
		   	   YBLUtil.setSystemPorps();
		   	   String key_file 						= "test.jks";
		   	   String key_pass 						= "fonepaisa";
		   	   String key_path						= "src/main/resources"+"/"+key_file;
		   	   YBLUtil.setClientCertificate(key_path, key_pass);
		   	   

		   	   System.out.println("setting headers done");

		   	   System.out.println("System props set ");
		   	   Path currentRelativePath = Paths.get("");
		   	   String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";
	
		   	   URL url 		 						= new URL("file:///" + wsdlFilePath);
		   	   System.out.println("file URL "+url.getPath());
		   	   FundsTransferByCustomerService2 client = YBLUtil.prepareYblWsdlObj(url);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   
		   	   client = YBLUtil.setHeaders(sessionObj);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   System.out.println("headers set ");
		   	   
		   	   GetBalanceReqDTO message                 = new GetBalanceReqDTO();
		   	   message.setAcc_no("000380800000781");
		   	   message.setCust_id("299915");
		   	   YBLUtil.prepareGetBalanceObj(message,sessionObj);
		   	   System.out.println("session object set "+sessionObj.toString());
		   	   message  = YBLUtil.ReqAndRespGetBalance(message, sessionObj);
		   	   System.out.println("message processed "+message.toString());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	@Test
	public void prodBalance(){
		   try{ 
			   System.out.println("Client data fetched");
			   SessionObj sessionObj = new SessionObj();

			   sessionObj.put(Constants.PARTNER_URL, 		"https://sky.yesbank.in:444/app/live/fundsTransferByCustomerService2");
			   sessionObj.put(Constants.USERNAME,			"6852446");
			   sessionObj.put(Constants.PASSWORD,			"Yesbank@2609");
			   sessionObj.put(Constants.CLIENT_ID, 			"8b90112a-4e02-4467-a0c5-f20963cccb70");
			   sessionObj.put(Constants.CLIENT_SECRET_KEY,  "B2pN2wI8gD3cD8fH5yE8cN4bB7bW8tQ0uT0sA1fS2sI1mY1kT1");
			   Map<String, List<String>> headers = new HashMap<String, List<String>>();
			   
			   headers.put("X-IBM-Client-Id", 	   Collections.singletonList((String)sessionObj.get(Constants.CLIENT_ID)));
			   headers.put("X-IBM-Client-Secret",  Collections.singletonList((String)sessionObj.get(Constants.CLIENT_SECRET_KEY)));
		   	   sessionObj.put(Constants.HEADER_DATA, 		headers);
		   	   System.out.println("Client data fetched");
		   	   YBLUtil.setSystemPorps();
		   	   String key_file 						= "test_prod.jks";
		   	   String key_pass 						= "fonepaisa";
		   	   String key_path						= "src/main/resources"+"/"+key_file;
		   	   YBLUtil.setClientCertificate(key_path, key_pass);
		   	   

		   	   System.out.println("setting headers done");

		   	   System.out.println("System props set ");
		   	   Path currentRelativePath = Paths.get("");
		   	   String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";
	
		   	   URL url 		 						= new URL("file:///" + wsdlFilePath);
		   	   System.out.println("file URL "+url.getPath());
		   	   FundsTransferByCustomerService2 client = YBLUtil.prepareYblWsdlObj(url);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   
		   	   client = YBLUtil.setHeaders(sessionObj);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   System.out.println("headers set ");
		   	   
		   	   GetBalanceReqDTO message                 = new GetBalanceReqDTO();
		   	   message.setAcc_no("002281300003993");
		   	   message.setCust_id("6852446");
		   	   YBLUtil.prepareGetBalanceObj(message,sessionObj);
		   	   System.out.println("session object set "+sessionObj.toString());
		   	   message  = YBLUtil.ReqAndRespGetBalance(message, sessionObj);
		   	   System.out.println("message processed "+message.toString());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	@Test
	public void prodTranInq(){
		   try{ 
			   System.out.println("Client data fetched");
			   SessionObj sessionObj = new SessionObj();

			   sessionObj.put(Constants.PARTNER_URL, 		"https://sky.yesbank.in:444/app/live/fundsTransferByCustomerService2");
			   sessionObj.put(Constants.USERNAME,			"6852446");
			   sessionObj.put(Constants.PASSWORD,			"Yesbank@2609");
			   sessionObj.put(Constants.CLIENT_ID, 			"8b90112a-4e02-4467-a0c5-f20963cccb70");
			   sessionObj.put(Constants.CLIENT_SECRET_KEY,  "B2pN2wI8gD3cD8fH5yE8cN4bB7bW8tQ0uT0sA1fS2sI1mY1kT1");
			   Map<String, List<String>> headers = new HashMap<String, List<String>>();
			   
			   headers.put("X-IBM-Client-Id", 	   Collections.singletonList((String)sessionObj.get(Constants.CLIENT_ID)));
			   headers.put("X-IBM-Client-Secret",  Collections.singletonList((String)sessionObj.get(Constants.CLIENT_SECRET_KEY)));
		   	   sessionObj.put(Constants.HEADER_DATA, 		headers);
		   	   System.out.println("Client data fetched");
		   	   YBLUtil.setSystemPorps();
		   	   String key_file 						= "test_prod.jks";
		   	   String key_pass 						= "fonepaisa";
		   	   String key_path						= "src/main/resources"+"/"+key_file;
		   	   YBLUtil.setClientCertificate(key_path, key_pass);
		   	   

		   	   System.out.println("setting headers done");

		   	   System.out.println("System props set ");
		   	   Path currentRelativePath = Paths.get("");
		   	   String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";
	
		   	   URL url 		 						= new URL("file:///" + wsdlFilePath);
		   	   System.out.println("file URL "+url.getPath());
		   	   FundsTransferByCustomerService2 client = YBLUtil.prepareYblWsdlObj(url);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   
		   	   client = YBLUtil.setHeaders(sessionObj);
		   	   sessionObj.put(Constants.YBL_FUNDS_TRAN_OBJ, client);
		   	   System.out.println("headers set ");
		   	   
		   	   TranInqDTO message                 = new TranInqDTO();
		   	   message.setPg_txn_id("9918");
		   	   message.setCust_id("6852446");
		   	   YBLUtil.prepareChkStatusObj(message,sessionObj);
		   	   System.out.println("session object set "+sessionObj.toString());
		   	   message  = YBLUtil.ReqAndRespChkSTatus(message, sessionObj);
		   	   System.out.println("message processed "+message.toString());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
}
