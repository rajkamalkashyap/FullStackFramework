package com.fonepaisa.GenEPG.Handler.IBL;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fonepaisa.GenEPG.CommonUtil.CommonUtil;
import com.fonepaisa.GenEPG.CommonUtil.Constants;
import com.fonepaisa.GenEPG.DTO.AuthorizeParsedResponseDTO;
import com.fonepaisa.GenEPG.DTO.PaymentRequestDTO;
import com.fonepaisa.GenEPG.DTO.ReversalRequestDTO;
import com.fonepaisa.GenEPG.DTO.SessionObj;
import com.fonepaisa.GenEPG.DTO.TranInqDTO;
import com.fonepaisa.GenEPG.DTO.VerifyAddressDTO;
import com.fonepaisa.GenEPG.Exception.GenEPGException;

public class IBLUtilTest {
	
	@Test
	public void testPrepareCollectReqForPIPE() {
		PaymentRequestDTO message = new PaymentRequestDTO();
		SessionObj sessionObj =   new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("12345");
		message.setVirtual_address("7204853405@upi");
		message.setAmt(510.05);
		message.setNote("TEST");
		sessionObj.put(Constants.EXPIRY_TIME,"10");
		
		String prepareMsg= IBLUtil.prepareCollectReq(message, sessionObj);
		System.out.println(prepareMsg);
		System.out.println("INDB000000000121|12345|7204853405@upi|510.05|TEST|10|12|||||||||NA|NA");
		if (prepareMsg.equalsIgnoreCase("INDB000000000121|12345|7204853405@upi|510.05|TEST|10|12|||||||||NA|NA")==false){
			fail("Incorrect message prepared");
		}
		
	}
	
	@Test
	public void testEncrption(){
		String reqMsg 	 = "INDB000000000121|12346|7204853405@upi|510.05|TEST|10|12|||||||||NA|NA";
		//String reqMsg  = "INDB000000000121|12345|7204853405@upi|510.05|TEST|10|12|||||||||NA|NA"; 
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
			String encMsg;
			try {
				encMsg = IBLUtil.encryptString(reqMsg, encKey);
				System.out.println(encMsg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("failed");
			}
			
	} 
/*	@Test 
    public void testForwardReqPIPE(){
    	String encMsg     = "808B52580C3064D7ECAA3FF5B15ED123464FE2388EEA22C8225041CA2A0F354FC23529F6305B0AE6D8F3A38EC58ED9DF6A23D6A223D05990DD383F956BC58CDC1244B0B70FBEE509FD1BB082E951D444C186F00E0D4915FF2CE19B8793E5BA968902D27DFD2288093FD9775C5ED65BA361B406A027E23926DBFFB49206B445ADAE347C6059967B3F3C1CFC9CBA37D6CE3EFC375C3611372F688121563A150253EBCB7E0D38F6081E70CBCEE02B6D1D78E1EDF3B6187587DF629AC307633401545DF4CDA6F2332DFA8772DCD10D9EDED3AD442C2D3B48E26859ACC7C3E0ED9BF996102C0616B9FC65518CD1E4E7D73C89";
    	String merchantId = "INDB000000000121";
    	String url        = "https://ibluatapig.indusind.com/app/uat/upi/meCollectInitiateWeb";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "7521483d-4853-4612-a5c9-d6810b6da693");
    	headerData.put("x-ibm-client-secret", "H6fY8wU8pU1wD1qY0gU2vK4hJ5lC7wA3kE6jE8xP5aD8kA3vF0");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void testFailedRespDecrption(){
    	String msg = "CE3217E679C80441DCBDF0EE1AA32F42C688BB8D4AF9CCDAA9999E0A7E4A2EEAF6F078609FA554EF5A04D6BA2F461EB66857FCC65DE7924F54C20A76B165F133EEFEAD0FD1D1C9FFD71689A29A55220ECA18ED83B02E3E151A6A732A4027A28E5F5B7E5F094C6E4654FB43FCBC7DF76D2BE079C3BE6155CE30246A133EAC57A6CD4225E649422189A72949C550A373C43D24758E05DFB76110AB06EA9CC8911ACC0DA0D1EA27E78FF053F06EF0BECA198D189C98DDBF1FF49999243940EB272A52BF396393277FB76F8C816CFB5C9C783D1D6B181345C40ED22448683FEBA2265C38C3B4BFFE7162D1EB593D4856179CA19F6FB4AAA4E204B175BA495627CCB418E40E49C73EBB33FDC9D98269EE609D";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			
			//failed response NA|0|0.00|FAILED|Transaction request already processed for order id :12345|NA|NA|NA|NA|NA|NA|NA|NA|NA|NA|NA|NA
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
			
	}*/
    @Test
    public void testSucRespDecrption(){
    	String msg = "444C7ABD91AA6C9D8D0C98D01165115DF4AADF68A9F966A59774C69CABD670A4E691A3E53813552987F66EB09258623A987BB16DCA09BDF483A1E901BF91A7FE2CE7DA81CA3812D356115A027DFEE7F1EFB711454F28EBA88488E70ED3BB8A28A9517F7F9A1C2691D6945EE327411098AA41F53982E99A9FDF84A89496B5A457";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			
			//success response 12345|13812|510.05|SUCCESS|Transaction Collect request initiated successfully|7204853405@upi|tatapowerfonepaisa@indus|NA|NA|NA|NA|NA|NA|NA|NA|NA|NA
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
			
	} 
    @Test
    public void testCreatingClobObject (){
    	PaymentRequestDTO message = new PaymentRequestDTO();
    	message.setPg_txn_id("12345");
		message.setVirtual_address("7204853405@upi");
		message.setAmt(510.05);
		message.setNote("TEST");
		String jsonStr;
		try {
			jsonStr = CommonUtil.createJsonStrFromObj(message);
			System.out.print(jsonStr);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
	public void testPrepareChkStatusReq() {
		TranInqDTO message = new TranInqDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		sessionObj.put(Constants.PARTNER_REQ_ID,"13812");
		sessionObj.put(Constants.REFERENCE_ID,"");
		message.setPg_txn_id("12345");
		
		String prepareMsg= IBLUtil.prepareChkStatusObj(message, sessionObj);
		System.out.println(prepareMsg);
		System.out.println("INDB000000000121|12345|13812||||||||||NA|NA");
		//System.out.println("UPI000000000086|20160728111155|65437829217889||||||||||NA|NA");
		//UPI000000000086|20160728111155|65437829217889|||||||||NA|NA
		if (prepareMsg.equalsIgnoreCase("UPI000000000086|20160728111155|65437829217889||||||||||NA|NA")==false){
			fail("Incorrect message prepared");
		}
	}
    @Test
	public void testPrepareChkStatusReqJSON() {
    	TranInqDTO message = new TranInqDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		sessionObj.put(Constants.PARTNER_REQ_ID,"13812");
		sessionObj.put(Constants.REFERENCE_ID,"");
		message.setPg_txn_id("12345");
		
		String prepareMsg= IBLUtil.prepareChkStatusObj(message, sessionObj);
		System.out.println(prepareMsg);
		System.out.println("INDB000000000121|12345|13812||||||||||NA|NA");
		//System.out.println("UPI000000000086|20160728111155|65437829217889||||||||||NA|NA");
		//UPI000000000086|20160728111155|65437829217889|||||||||NA|NA
		if (prepareMsg.equalsIgnoreCase("UPI000000000086|20160728111155|65437829217889||||||||||NA|NA")==false){
			fail("Incorrect message prepared");
		}
	}
    @Test
	public void testchkStatusEncrption(){
		String reqMsg  = "INDB000000000121|12345|13812||||||||||NA|NA"; 
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
		String encMsg;
			try {
				encMsg = IBLUtil.encryptString(reqMsg, encKey);
				System.out.println(encMsg);
				//14B08D7C8ACB1B11B575E08F29009BDB291788D7AE502A4BD08096B4A93899F311907DF2F5F3701314E920A4A7DE54C3
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("failed");
			}
	}
    @Test 
    public void testChkStatusReq(){
    	String encMsg     = "14B08D7C8ACB1B11B575E08F29009BDB291788D7AE502A4BD08096B4A93899F3236B3EF41399499E088937D872F09D46";
    	String merchantId = "INDB000000000121";
    	String url        = "https://ibluatapig.indusind.com/app/uat/test/transactionstatus ";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "adc8fa8a-938f-4f00-9eef-feb85f5510be");
    	headerData.put("x-ibm-client-secret", "N8yK5qK4rB3eP1lP2uF5dQ8mN8wX5oM1cT2uT5oC8kH2vH1gU2");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void testchkStatusDecrption(){
    	String msg = "EE5160C31AA70094558FB9BD6227BDDFC15CF0C016708FE9EDF88C0C06514BE6B12D86876A12180DF639A96C5A915DFC1D2C2BF5C5BAD01FA84A2788895513662726A9E84C40A70F6E3C4598E041FDF4249C81EB52019AE1FF07367C583D94DF45A60BCC2C6F493AACF8B079FBEED6ABC32B2B1DFEAFB4D4D6D6A41AC289CC1FFB03F5503F0F3D296121549DCE9D1BEACEDBA13CCB2E51AD10FE6FC49DD587A57ED644C486BEA2B16350B2464E3CE288";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			
			//success response 152217|12345|510|null|FAILED|Transaction fail|null|null|7204853405@upi|INDB4E351A105691075DE0539F42180A79B|null|null|null|null|null|null|null|null|null|null|null
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    
    
    
    @Test
	public void testPrepareJSONCollectReqWithEnc() {
		PaymentRequestDTO message = new PaymentRequestDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("1223");
		message.setVirtual_address("7204853405@upi");
		message.setAmt(510.05);
		message.setNote("TEST");
		message.setRemarks(IBLUtil.generateNote(message.getPg_txn_id(),"Test"));
		sessionObj.put(Constants.EXPIRY_TIME,"10");
		
		String prepareMsg= IBLUtil.prepareCollectReq(message, sessionObj);
		System.out.println(prepareMsg);
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
		String encMsg;
		try {
			encMsg = IBLUtil.encryptString(prepareMsg, encKey);
			System.out.println(encMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test 
    public void testCollectForwardReqJSON(){
    	String encMsg     = "0069B4D6C77F8BC24099E8D0491E936A5E0E1F0D18DE494E2E2AC56BFF055F500F267291E1D7ADC0FFEAE5D652570D92E3BAD3145DD726F93C17B35C06E7FEB604EDF1B389DDD357FAD4E0C0F9D8764A3266FA473FDBBE1BEA6ECC7FA40A2879BC8E342B1D794EDE95518A45500726622AB09B8EB1F1D2B1C7F81C0944F8986A9DA08325BD76009745A14AA401BED66DB6F063B6CA8851BD713FDFC9398946CF52591B4B62783B540926EC7DD465D5C1F71B32EF2E5557DA131F0FD839BA77B521B471202E499FB630694E478728FA6CC78E4101F0AE2D4A2822E02C9597FA3EF53A4B14AB592172D6263F903B79EA32BB4579EBD47EA09A7F466E4BDD93D54C";
    	String merchantId = "INDB000000000121";
    	String url        = "https://ibluatapig.indusind.com/app/uat/upi/meCollectInitiateWeb";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "7521483d-4853-4612-a5c9-d6810b6da693");
    	headerData.put("x-ibm-client-secret", "H6fY8wU8pU1wD1qY0gU2vK4hJ5lC7wA3kE6jE8xP5aD8kA3vF0");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
			
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void testCollectJSONDecryption(){
    	PaymentRequestDTO message = new PaymentRequestDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("101");
		message.setVirtual_address("7204853405@upi");
		message.setAmt(510.05);
		message.setNote("TEST");
		sessionObj.put(Constants.EXPIRY_TIME,"10");
    	String msg = "{\"resp\":\"CE3217E679C80441DCBDF0EE1AA32F42BCED9EE09D8F2FC855FCD32E78966FDADFD3024FDA0A903926814A2418395F7347C5698D4C7ACA4C36EA1F7DDB3843F0BCE8EB3B8A26198759FA18066D239AC7FFCCC120E509D9707E28FB57A04808F429D1CFBF921FA6BB9D1E6AF2558878C2176B2D6E762B80CA3AF56F850D745CEEED4933C1659208844B2C1474C0370DC741C3481E77B47EFE9AAFDC8C486A4C1234BB36F1C24761F83158D9AF1BAF22AAAD8E7498A1FDA74983865E5BCB9738DEC2D7CF85ABDC73BDEE7573D77F720A1A931B70C0E0E7F5213152C861C33CD465CAFE843C790025338E403CECB4E0465237161EFA2C9C98ECD82AAD7A0BEDB7365DCC0E0A5A121B819D45CCD948422F50322E1754EEBCD4C6BC4D4DCDC4C9671E1C091C32040C9A474571874298717DDE22E1E192945D1B7A16C33E501B501220A3DE9D8404C64CD8E74205A754AE6D9332B72131CE9CB9D993CD447230D9AED1ECCB2D1077B43916DE8579CAC73CA3C58FD01BDCB3DE13AA8F0EAEBD4B66B13CA7495E6F15D62196FE23157DAB70E2D3CF67681776362F919997D7C54BCD63DE\"}";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		try {
			JSONParser parser        = new JSONParser();
    		JSONObject responseJSON  = (JSONObject) parser.parse(msg);
    		String decryptedResponse = IBLUtil.decryptString((String) responseJSON.get(Constants.IBL_JSON_RESPONSE), decKey);
    		System.out.println("decrypted response "+decryptedResponse);
    		message = IBLUtil.parseCollectResp(decryptedResponse, sessionObj, message);
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test
	public void testJSONCheckStatusReqWithEnc() {
		TranInqDTO message = new TranInqDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000001027");
		message.setPg_txn_id("26473199");
		//message.setPg_txn_id("A210171026220829431");
		
   		
		String prepareMsg= IBLUtil.prepareChkStatusObj(message, sessionObj);
		System.out.println(prepareMsg);
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
		//String encKey  = "a6381393c6677e46e7f2a28c9cd3c43e";
		String encMsg;
		try {
			encMsg = IBLUtil.encryptString(prepareMsg, encKey);
			System.out.println(encMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test 
    public void testChkStatusForwardReqJSON(){
    	String encMsg     = "7A9E9D7B2BCAAD9A72033B56BFA47A6556F024F4691A87F77763689ADD93E63703869653F0C21828E39013E23B2F171C21389F640F3E408F287D992B07283421563514A1D38A576F3CE1F7921C5D79F8";
    	String merchantId = "INDB000000000121";
    	String url        = "https://ibluatapig.indusind.com/app/uat/upi/meTranStatusQueryWeb";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "7521483d-4853-4612-a5c9-d6810b6da693");
    	headerData.put("x-ibm-client-secret", "H6fY8wU8pU1wD1qY0gU2vK4hJ5lC7wA3kE6jE8xP5aD8kA3vF0");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void testChkStatusJSONDecrption(){
    	TranInqDTO message = new TranInqDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("131");

    	
    	String msg = "{\"resp\":\"CE3217E679C80441DCBDF0EE1AA32F42E85FDD55EEE077495EACED2BA8D35275C0A4FC866E60ADD43049977D1F5B9401EB38ED4AB4DCBA8A836A5A42F2039CD049CA5728E797D940FF0263F638464F3A0091889ADF5298B7AEF963B77DFC10DDBEC41FFFDA58DE1CB23AF18DC9576B1E98885DA53881735BC4E27E493F8486D9299CA5262DD3F0A5CD5AFE9E2C36769DCDB78ECD0D02245DC2642852013B06616918C0192B05DCD56D55206DAB07C0BC\"}";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		try {
			JSONParser parser        = new JSONParser();
    		JSONObject responseJSON  = (JSONObject) parser.parse(msg);
    		String decryptedResponse = IBLUtil.decryptString((String) responseJSON.get(Constants.IBL_JSON_RESPONSE), decKey);
    		System.out.println("decrypted response "+decryptedResponse);
    		message = IBLUtil.parseTranInqResp(decryptedResponse, sessionObj, message);
			System.out.println(message.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test
	public void testJSONRefundReqWithEnc() {
		ReversalRequestDTO message = new ReversalRequestDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.setAttributes(new JSONObject());
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("NewTest5");
		message.setRev_txn_id("224");
		message.setNote("Refund");
		message.setAmt(1.0);
		//message.setUpi_txn_id("801510018157");
		message.setCust_ref("801510018157");
		String prepareMsg= IBLUtil.prepareReversalObj(message, sessionObj);
		System.out.println(prepareMsg);
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
		String encMsg;
		try {
			encMsg = IBLUtil.encryptString(prepareMsg, encKey);
			System.out.println(encMsg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test 
    public void testRevrsalReqForwardReqJSON(){
    	String encMsg     = "E6E9CDF5DE3B8C8BCAAD622A7EE968B8CAD5AA2C620C9F98D61458F34AE032D31C1BE4983C150F913348C268F4B7CF06694044D9E078EB2B26E6C7F7485A1D9E57C65FF93FC9C82EC782F21F4E6552D2DD2FC3DE938E2C07BE7162C20819C602D5EF4D2FB2685530BE63988ED4392E95340C326BB645A0C454F3B16EF6AC623E9696EEEB5C01648C104240266BB33CAB8FD01BDCB3DE13AA8F0EAEBD4B66B13CA7495E6F15D62196FE23157DAB70E2D38DFE623D50985C8732BADF1C88F4A709925FB2B0BC3F1C4AA432D665CBF051C5";
    	String merchantId = "INDB000000000121";
    	String url        = "https://ibluatapig.indusind.com/app/uat/upi/meRefundJsonService";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "7521483d-4853-4612-a5c9-d6810b6da693");
    	headerData.put("x-ibm-client-secret", "H6fY8wU8pU1wD1qY0gU2vK4hJ5lC7wA3kE6jE8xP5aD8kA3vF0");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
			
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void testReversalJSONDecrption(){
    	String msg = "60F6FB617D4E9506C8F6A0CD0868017D4E42705DCEBA345407800635C369DA4B66461DC665DCF1A166D07CC6C3BDABCE6FB2F9E1B2C4E41AD56990FC202982711EF6C2B58923BE63E3D66F56B5238901B0D92DEBBB4C22BF68BA1727D71206BEBEED9FAEE5CD59C57AD3796931513A7B293BE1FC574015FF535A97B30C9E3519A1E4925E30E8D99DA56B041BD278C22EFDE45FD9041E8A85E5B5DF05853ED4FECE68826AC8081805BA4C3B6FCF38F560678A661588A9ACF166F74CE751695F9B6E1499F7063D802CECAD9262522C2841C01F0EC5FBFFA0B4CC1E00C9A44EDEFAE4DF56F24BCA2400F02F00052050C6BBA3203A7C9DC83BE1EEE37219446FA76CFB87CA5C0670B52F2E004F54092FF931B07079959E566C8CF48345CF10D4404B89CF82208C15B86E2C433634011B67CCDF588DCF9F4A3CC79713813889073B69";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		SessionObj sessionObj =    new SessionObj();
		sessionObj.setAttributes(new JSONObject());
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		ReversalRequestDTO message = new ReversalRequestDTO();
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			message= IBLUtil.parseReversalResp(respMsg, sessionObj, message);
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    
    @Test
    public void testVerifyAddress(){
    	VerifyAddressDTO message = new VerifyAddressDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setVirtual_address("nischal@indus");
		//message.setCust_ref("719318010287");
		String prepareMsg= IBLUtil.prepareChkVirtualAddObj(message, sessionObj);
		System.out.println(prepareMsg);
		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
		String encMsg;
		try {
			encMsg = IBLUtil.encryptString(prepareMsg, encKey);
			System.out.println(encMsg);
	    	String merchantId = "INDB000000000121";
	    	String url        = "https://ibluatapig.indusind.com/app/uat/upi/validateVPAWeb";
	    	HashMap<String, String> headerData = new HashMap<String,String>();
	    	headerData.put("x-ibm-client-id", "7521483d-4853-4612-a5c9-d6810b6da693");
	    	headerData.put("x-ibm-client-secret", "H6fY8wU8pU1wD1qY0gU2vK4hJ5lC7wA3kE6jE8xP5aD8kA3vF0");
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    
    @Test
    public void testVerifyAddressJSONDecrption(){
    	String msg = "7A9E9D7B2BCAAD9A72033B56BFA47A65F097198493412637F0CEEB7555B6108365275F975DA53A93DE8A2EB82A3AF017F39EC6B9A01CAE215051EABF2C459236C186F00E0D4915FF2CE19B8793E5BA96E6EF6C930F95C947470BA54AD60A505D4A5A728BD93C396DE695F213219EA3514ECB06599824AF32862EEDF32A580C80F5DEFDF2A9899B0ADF3F5A20289D18FD9F28211470FCDDCC5F428FFBB65EF20FD788C80D49908A2E649CBB72E29B9EA1925FB2B0BC3F1C4AA432D665CBF051C5";
		String decKey = "caf7bdef57677d6a90692c5e7c4aa01e";
		VerifyAddressDTO message = new VerifyAddressDTO();
		SessionObj sessionObj =    new SessionObj();
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			message = IBLUtil.parseChkVPAResp(respMsg, sessionObj, message);
			System.out.println(message.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    @Test
   	public void genEncForCallback(){
   		String reqMsg  = "{\"apiResp\":{\"pspRefNo\":\"NA\",\"upiTransRefNo\":0,\"npciTransId\":\"1-ZZZZ\",\"custRefNo\":\"736012869344\",\"amount\":\"1.0\",\"txnAuthDate\":\"2017:12:26 12:16:52\",\"responseCode\":\"00\",\"approvalNumber\":\"NA\",\"status\":\"SUCCESS\",\"payerVPA\":\"nisha.m5@axisbank\",\"payeeVPA\":\"fonepaisa@indus\",\"orderNo\":\"736012869344\",\"currentStatusDesc\":\"Transaction success\",\"txnNote\":\"1WMPNP\",\"txnType\":\"PAY\",\"refUrl\":\"http://axis.com/upi\"}}"; 
   		String encKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
   		String encMsg;
   			try {
   				encMsg = IBLUtil.encryptString(reqMsg, encKey);
   				System.out.println(encMsg);
   				//14B08D7C8ACB1B11B575E08F29009BDB291788D7AE502A4BD08096B4A93899F311907DF2F5F3701314E920A4A7DE54C3
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   				fail("failed");
   			}
   	}
    @Test
   	public void decryptAndParse(){
   		String enMsg  = "330D1EB2DD10EF17D54C444E61A75BE875DE57D5D40E1F56CCB036D16197CB9A4DB313A1703F7A40FFF10342681C04E7B0F58B7610EF490A50BB45C1B27FC7B2E8689E2FB9DEB7659B46058553A58AE07641BB09C4305FA416875D5BF5815638186D94544645C3F615152ECE9CC6BAC5E6CB492FA66AEAD27048E9E79E120A5B3AD6C705F8DCDBDA70EFAB53F7E43A85C60258B6CC9C4C6BEB8B37A5B602E9BB881D824D741DD31F4B94E386BF8DF58837C8C87ACC275782608EEA7CE2D03126E20F8722FAEAC1CF6288EAB42A193A2F3EA61651CC6FABA102AA45C82508F2ADFAC8114E34F08625104AA02946664F6533E9B49F83423BA3A52FCC1CD7D960C5EFA890692B46D6D2CCE51DAE736CE223D82BDD4ADC71E631453FFF5C23C5E336570E7200B24F9BFEC708A2536264883539B5131FCF73C594194C2ACEF85E4AC006C08F33ED7C272CD3DD8BFB01A0AFB2194E593C6BB22812311683CA955434852D5068077735EBA4CF4AD4BF3B0F404FA9236119D56F3CAA459D3301BAC747C998E5FAB42C5AE20B434076F6392256208053C980DFE6F437AC3D93335099324ACE5719F8C0C24D8A280F2582098FBFD554B9551F935A386826AE70D2B0FBAE169BD4D6E21345F77F9C9572A2E6C1980E691FC67359243A2F8560CC05C93D7CB2C76CAB6074A775CB9240D19D6D3329B858DFC10FC63737D8EEC8F0550819F0A1FA80F3AA8803FA637714F7665FE49E1BAF7B4877287D3CADFCB3AD5C5FF2DFA8"; 
   		String decKey  = "caf7bdef57677d6a90692c5e7c4aa01e";
   		AuthorizeParsedResponseDTO message;
   		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
   			try {
   	    		String decryptedResponse = IBLUtil.decryptString(enMsg, decKey);
   	    		System.out.println("decrypted response "+decryptedResponse);
   	    		message = IBLUtil.parseAuthorize(decryptedResponse, sessionObj);
   				System.out.println(message.toString());
   				//14B08D7C8ACB1B11B575E08F29009BDB291788D7AE502A4BD08096B4A93899F311907DF2F5F3701314E920A4A7DE54C3
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   				fail("failed");
   			}
   	}

    @Test 
    public void testGenNote(){
    	String remarks = IBLUtil.generateNote("123457892973535783", "THALLY OUTBACK AGRI PRIVATE LIMITED");
    	System.out.println(remarks);
    }
    
    @Test
   	public void prodPrepareJSONCollectReqWithEnc() {
   		PaymentRequestDTO message = new PaymentRequestDTO();
   		SessionObj sessionObj =    new SessionObj();
   		sessionObj.put(Constants.MY_ID,"INDB000000001027");
   		message.setPg_txn_id("122");
   		message.setVirtual_address("7204853405@upi");
   		message.setAmt(1.0);
   		message.setNote("TEST");
   		message.setRemarks(IBLUtil.generateNote(message.getPg_txn_id(),"Test"));
   		sessionObj.put(Constants.EXPIRY_TIME,"5");
   		
   		String prepareMsg= IBLUtil.prepareCollectReq(message, sessionObj);
   		System.out.println(prepareMsg);
   		String encKey  = "a6381393c6677e46e7f2a28c9cd3c43e";
   		String encMsg;
   		try {
   			encMsg = IBLUtil.encryptString(prepareMsg, encKey);
   			System.out.println(encMsg);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			fail("failed");
   		}
   	}
    @Test 
    public void prodCollectForwardReqJSON(){
    	String encMsg     = "079B0A69F5522A534B89B681565573E9A15D14F1961F6081ECFFACDB568B5173CC678364A492308538045D94DAADDB12FF9A1200C959DCEA23960F9B108102F36D9EC3A704CBC96080399900CBA296A922EF04A1FAC3F07F81CB1CC10317DF55C77D3189917F29A556DFD7DCF2F33F704D47A24B0669F78471314AD17EED97ADEFA4AD4CF324E1223B3CDA4784A958E1AB31C9F3BC2BA0F7ACBEBDC7302C4717D4DE807AE83FB66BEEFB22DD80D959AEF056B3066237FA442C0FBA0F9A0C767DC85CE4F79132C0415F966C029902DBCCB26297A236CDD40675040DCED3FC829A63C9F52A2AD722672DFA9F6B4DC88409AE989DF1CD94E43A1D08372F726A030E";
    	String merchantId = "INDB000000001027";
    	String url        = "https://apig.indusind.com/ibl/prod/upi/meTransCollectSvc";
    	HashMap<String, String> headerData = new HashMap<String,String>();
    	headerData.put("x-ibm-client-id", "5014e971-a731-461b-99cb-6e2dd3113935");
    	headerData.put("x-ibm-client-secret", "A2oV5lI8gR7kD6rC5qY3tJ5kA6eR8tG4mD2gF8pQ1mW8eT4pY3");
    	try {
			String resp = IBLUtil.forwardReq(encMsg, merchantId, url,headerData);
			System.out.print(resp);
			
		} catch (GenEPGException | URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
    }
    @Test
    public void prodCollectJSONDecryption(){
    	PaymentRequestDTO message = new PaymentRequestDTO();
		SessionObj sessionObj =    new SessionObj();
		sessionObj.put(Constants.MY_ID,"INDB000000000121");
		message.setPg_txn_id("101");
		message.setVirtual_address("7204853405@upi");
		message.setAmt(510.05);
		message.setNote("TEST");
		sessionObj.put(Constants.EXPIRY_TIME,"10");
    	String msg = "{\"resp\":\"ECA2B69CC99A55D18BB9C849F10294A1267BF9E4FD9AF9302893E8A173DB81FB9922A2B029AE93B03DC8D9D22BF1C49ED754449BE49D6D232616D37980DBEF5EDE921EE421D5CF9F9BA5D902125A3BEB014CAB88662D1D07CE7DE694ED4ECAA4627AB21BF972D28297F73599CCB8962998119C146B9C333E9A29227872552CFBB1256BE9CF0ED38E0A84C4A405F51D6005D4C21CADF5B22EA8E207DD8ADC5D224AE483E5A7ECF1F1BCC85CF797D21F5560B88DDC9C9F899A789857472529D82084627E85675FD049E03B828924D53DA0435C48D1537BAC79EC8EF280AD21B45A59B056B746A22BFF96CB6601A6DA39558A9F0DA445D0354FBF69BF044D4D0FD074B7D1A385EAB4BA1416039EC8B17AFFA93AB282BEA9544E7B6152BD484F17BA93CB48CA07CEF297817E00A06E1FDF9C38DD75E6928EDF03B5122117E1BF8BE95930BDA27108CBD2B0966211DB326926\"}";
		String decKey = "a6381393c6677e46e7f2a28c9cd3c43e";
		try {
			JSONParser parser        = new JSONParser();
    		JSONObject responseJSON  = (JSONObject) parser.parse(msg);
    		String decryptedResponse = IBLUtil.decryptString((String) responseJSON.get(Constants.IBL_JSON_RESPONSE), decKey);
    		System.out.println("decrypted response "+decryptedResponse);
    		message = IBLUtil.parseCollectResp(decryptedResponse, sessionObj, message);
			System.out.println(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    
    @Test
    public void prodStatusDecrption(){
    				  
    	String msg = "ECA2B69CC99A55D18BB9C849F10294A1C651E8A4A73B44A60E58B215A2BE1247379EEEC43EF09F5AC1CDA24A344E5B9BDA3C43701241637A05CCFBB39F8E039271EE4B297858EF1B399839A49BAE6D6ED6C5B0614A0A41AEDC69B45F6EE6379E3E5DCA3AA10EF77902C5AAC62C6AD9349F713F91FA169B0D41DD4FD6C8005E22DC77400EC90A6BE0535DFD9B2166D4743606BCED38CC190CCC40FF8F1B3B3ABA95C24CF312CA7076FA44E905014BB8D2CB3D4F1A9072138F0A0A6B9D41C6B1CC645267CD4473C9A656B806D967A73810E3D22AC6F03678C36B716E666199335959E3E85B1C8D2AE942D75135A7F1CE90024B359EA52EB86D1A91FCC10F406329DA15E75F57EE7EB865E93D07C0BF08C0D6ADEC36A642FF7DE10A972683AC3A9B956C8FBFE9AEB8E6D527C1F0B54A4A0508D0726341F1BE4901D28CC68CAE2592";
    	//String msg = "94CA826FAB6F55AC23720D60F9548A9E8C1CA307BC15513BD47FE9D691B5495152D4742B6353E3FD90B0B5B18A8A3C76E966301C1276D690CFF1224227C580C7251522F50BE4F0FDF98BE9B57BF4404B7EB677855A6C5778B853A941D1A9A317A6A417150E8EB86FD01509F477D6A851F1EE5629086DF14B5B9F7B909763EA164230CEF361B4F74DCFBB281FC9820BE7430420FF99291CF431F3EF08C205DEED5EAFFDE653059D6C6427C7B95DFFFA1E79D71AB6555FFD1684969F47E1732EBA00CAF3B23B352C775F1A963FFDBC1532B1F91F2C45097CD35946CE83B8C56BAFE3A8773EABA172E2DCA13D66D3A77DF9938AEC469632A659AD47B5B405B8EEDB67228C7929C8EACC48B8246128AB958737F3F8A42C28EA85697F1B2B749580853BA067235C487FE7CD0883242D327C9E66BF444CA23953ED6DD7849E54D7BE4405BDADD212D1DAC7204118EEFFC9BEF322E829F50062C4280E3E384E4193FC38D0A4C0829118B948F215BE56A1C45AA0242FDB338983C50561A97FFCC02E6BCA84FB6E5033C06392E75B779E73D132A2546E73EC57EE9B0DFC004D3095B214B98FFE0059E437B2A988E2845FA88D6CAD2CD5314D277031537E02B3455DB943EA989564A9CE907D12025DC3DDD6EAEC4D53A7ACD8883E264B9C307CA84FDD461C";
		String decKey = "a6381393c6677e46e7f2a28c9cd3c43e";
		try {
			String respMsg = IBLUtil.decryptString(msg, decKey);
			System.out.println(respMsg);
			
			//success response 152217|12345|510|null|FAILED|Transaction fail|null|null|7204853405@upi|INDB4E351A105691075DE0539F42180A79B|null|null|null|null|null|null|null|null|null|null|null
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("failed");
		}
	}
    
    
}

