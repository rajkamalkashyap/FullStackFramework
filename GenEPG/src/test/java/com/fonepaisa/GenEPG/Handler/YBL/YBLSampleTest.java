package com.fonepaisa.GenEPG.Handler.YBL;

import javax.xml.ws.Holder;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext; 

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;
import java.math.BigInteger;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.Iterator;


import java.security.NoSuchAlgorithmException;
import java.security.KeyStoreException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.KeyManagementException;
import java.security.UnrecoverableKeyException;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;
import javax.xml.soap.SOAPFault;
import javax.xml.namespace.QName;
import javax.xml.soap.DetailEntry;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fonepaisa.GenEPG.YBL.WSDL.FundsTransferByCustomerService2;
import com.fonepaisa.GenEPG.YBL.WSDL.FundsTransferByCustomerService2HttpService;
import com.fonepaisa.GenEPG.YBL.WSDL.TransferTypeType;
import com.fonepaisa.GenEPG.YBL.WSDL.TransactionStatusType;
import com.fonepaisa.GenEPG.YBL.WSDL.BeneficiaryType;
import com.fonepaisa.GenEPG.YBL.WSDL.BeneficiaryDetailType;
import com.fonepaisa.GenEPG.YBL.WSDL.NameType;
import com.fonepaisa.GenEPG.YBL.WSDL.ContactType;
import com.fonepaisa.GenEPG.YBL.WSDL.AddressType;
import com.fonepaisa.GenEPG.YBL.WSDL.CurrencyCodeType;




@SuppressWarnings("unchecked")
public class YBLSampleTest {

	@org.junit.Test
   public void doMain() throws NoSuchAlgorithmException, KeyStoreException, FileNotFoundException, IOException, KeyStoreException, KeyStoreException, CertificateException, UnrecoverableKeyException, KeyManagementException {


      enableTrace();
      setClientCertificate();

      Path currentRelativePath = Paths.get("");
      String wsdlFilePath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/YBL.wsdl";

      FundsTransferByCustomerService2HttpService svc =  new FundsTransferByCustomerService2HttpService(new URL("file:///" + wsdlFilePath));
      FundsTransferByCustomerService2 client = svc.getFundsTransferByCustomerService2HttpPort();
 

      Holder<String> version                          = new Holder<String>();
      Holder<TransferTypeType> transferType           = new Holder<TransferTypeType>();
      Holder<String> uniqueResponseNo                 = new Holder<String>();
      Holder<BigInteger> attemptNo                    = new Holder<BigInteger>();
      Holder<Boolean> lowBalanceAlert                 = new Holder<Boolean>();
      Holder<TransactionStatusType> transactionStatus = new Holder<TransactionStatusType>();
      Holder<String> nameWithBeneficiaryBank          = new Holder<String>();
      Holder<String> requestReferenceNo               = new Holder<String>();
      Holder<String> beneficiaryBank               = new Holder<String>();
      Holder<CurrencyCodeType> transferredCurrencyCode = new Holder<CurrencyCodeType>();
      Holder<CurrencyCodeType> accountCurrencyCode    = new Holder<CurrencyCodeType>();
      Holder<Float>  accountBalanceAmount             = new Holder<Float>();
      Holder<Float>  transferredAmount                = new Holder<Float>();
      Holder<XMLGregorianCalendar> transactionDate    = new Holder<XMLGregorianCalendar>();
      BeneficiaryType beneficiary                     = new BeneficiaryType();
      BeneficiaryDetailType beneficiaryDetail         = new BeneficiaryDetailType();
      AddressType address                             = new AddressType();
      ContactType contact                             = new ContactType();
      NameType name                                   = new NameType();

      String uniqueRequestNo, appID, purposeCode, customerID, debitAccountNo, remitterToBeneficiaryInfo;
      CurrencyCodeType transferCurrencyCode;

      version.value                          = "1.0";
      uniqueRequestNo                        = String.valueOf(UUID.randomUUID()).replaceAll("-","");
      appID                                  = "299915";
      customerID                             = "299915";
      debitAccountNo                         = "000380800000781";
      transferType.value                     = TransferTypeType.NEFT;
      transferCurrencyCode                   = CurrencyCodeType.INR;
      float transferAmount                   = 10;
      remitterToBeneficiaryInfo              = "OnBoarding";
      purposeCode                            = null;
      String beneficiaryAccountNo = "026291800001191";
      String beneficiaryIFSC = "HDFC0000001";
      String beneficiaryMobileNo = "9869581569";
      String beneficiaryMMID = "9532870";
     // beneficiaryBank.value = "HDFC";
      address.setAddress1("Wilston Road");
      name.setFullName("Quantiguous Solutions");

      beneficiaryDetail.setBeneficiaryName(name);
      beneficiaryDetail.setBeneficiaryAddress(address);
      beneficiaryDetail.setBeneficiaryContact(contact);
      beneficiaryDetail.setBeneficiaryAccountNo(beneficiaryAccountNo);
      beneficiaryDetail.setBeneficiaryIFSC(beneficiaryIFSC);
      beneficiaryDetail.setBeneficiaryMobileNo(beneficiaryMobileNo);
      beneficiaryDetail.setBeneficiaryMMID(beneficiaryMMID);

      beneficiary.setBeneficiaryDetail(beneficiaryDetail);

      // set the url, the URL for clientAuth (2-way SSL) & simple SSL are different
        ((BindingProvider)client).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "https://uatsky.yesbank.in:444/app/uat/ssl/fundsTransferByCustomerSevice2");

      // set the user & password
      ((BindingProvider)client).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "testclient");
      ((BindingProvider)client).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "test@123");

      // set the headers
      Map<String, List<String>> headers = new HashMap<String, List<String>>();
      headers.put("X-IBM-Client-Id", Collections.singletonList("44f0178b-9537-414c-bafd-8b2f99a9c6af"));
      headers.put("X-IBM-Client-Secret", Collections.singletonList("L3qH4fB1nF7kM6dM3iK5jQ1cP3gI8eV6wS3eH4eP4wW0sB4lO8"));
      ((BindingProvider)client).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);

      // send the request
      try {
/*         client.transfer(version, uniqueRequestNo, appID, purposeCode, customerID, debitAccountNo, 
                         beneficiary, transferType, transferCurrencyCode, transferAmount, remitterToBeneficiaryInfo,
                         uniqueResponseNo, attemptNo, lowBalanceAlert, transactionStatus, nameWithBeneficiaryBank, requestReferenceNo,
                         beneficiaryBank);
         System.out.println("tran resp");
         System.out.println("version "+version.value);
         System.out.println("uniqueRequestNo "+uniqueRequestNo);
         System.out.println("appID "+appID);
         System.out.println("purposeCode "+purposeCode);
         System.out.println("customerID "+customerID);
         System.out.println("debitAccountNo "+debitAccountNo);
         System.out.println("beneficiary "+beneficiary);
         System.out.println("transferType "+transferType.value);
         System.out.println("transferCurrencyCode "+transferCurrencyCode);
         System.out.println("transferAmount "+transferAmount);
         System.out.println("remitterToBeneficiaryInfo "+remitterToBeneficiaryInfo);
         System.out.println("uniqueResponseNo "+uniqueResponseNo.value);
         System.out.println("attemptNo "+attemptNo.value);
         System.out.println("lowBalanceAlert "+lowBalanceAlert.value);
         System.out.println("transactionStatus "+transactionStatus.value);
         System.out.println("nameWithBeneficiaryBank "+nameWithBeneficiaryBank.value);
         System.out.println("requestReferenceNo "+requestReferenceNo.value);
         System.out.println("beneficiaryBank "+beneficiaryBank.value);
        
         
         parseTransferRestul(uniqueResponseNo, attemptNo, lowBalanceAlert, transferType, transactionStatus, nameWithBeneficiaryBank, requestReferenceNo);*/
         client.getBalance(version.value, appID, customerID, debitAccountNo, version, accountCurrencyCode, accountBalanceAmount, lowBalanceAlert);

         System.out.println("get balance resp");
         System.out.println("version.value, "+version.value);
         System.out.println("appID, "+appID);
         System.out.println("customerID, "+customerID);
         System.out.println("debitAccountNo, "+debitAccountNo);
         System.out.println("version, "+version.value);
         System.out.println("accountCurrencyCode, "+accountCurrencyCode.value);
         System.out.println("accountBalanceAmount, "+accountBalanceAmount.value);
         System.out.println("lowBalanceAlert "+lowBalanceAlert.value);
		 
        /* client.getStatus(version, appID, customerID, uniqueRequestNo, transferType ,transferType, transactionDate, transferredAmount, transferredCurrencyCode, transactionStatus,beneficiaryBank );
         System.out.println("get status ");
         System.out.println("version "+version.value);
         System.out.println("appID "+appID);
         System.out.println("customerID "+customerID);
         System.out.println("uniqueRequestNo "+uniqueRequestNo);
         System.out.println("transferType "+transferType.value);
         System.out.println("transferType "+transferType.value);
         System.out.println("transactionDate "+transactionDate.value);
         System.out.println("transferredAmount "+transferredAmount.value);
         System.out.println("transferredCurrencyCode "+transferredCurrencyCode.value);
         System.out.println("transactionStatus "+transactionStatus.value);
         System.out.println("beneficiaryBank "+beneficiaryBank.value);*/

      }
      catch(SOAPFaultException e) {
        printFault(e.getFault());
      }
      catch(Exception e) {
        e.printStackTrace(); 
      };

    
   }

   private void parseTransferRestul(Holder<String> uniqueResponseNo, 
                                           Holder<BigInteger> attemptNo, 
                                           Holder<Boolean>lowBalanceAlert, 
                                           Holder<TransferTypeType> transferType,
                                           Holder<TransactionStatusType> transactionStatus, 
                                           Holder<String> nameWithBeneficiaryBank, 
                                           Holder<String> requestReferenceNo) 
    {
     System.out.println("Transfer Type Used (when you pass ANY, the bank choses the transfer type)" + transferType.value);

     switch(transactionStatus.value.getStatusCode()) {
       case "COMPLETED":
         System.out.println("Transfer Completed (IMPS/FT)");
         System.out.println("RRN: " + transactionStatus.value.getBankReferenceNo());
         break;
       case "SENT_TO_BENEFICIARY":
         System.out.println("Funds sent to beneficiary bank, final status will be known after 2 hours (NEFT/RTGS)");
         System.out.println("UTR: " + transactionStatus.value.getBankReferenceNo());
         break;
       case "SCHEDULED_FOR_NEXT_WORKDAY":
         System.out.println("The transaction will be sent to the beneficiary bank the next working day, (NEFT/RTGS)");
         break;
       case "IN_PROCESS":
         System.out.println("The transaction is not yet processed, can pass or fail, status will be known after 30 mins");
         break;
       case "RETURNED_FROM_BENEFICIARY":
         System.out.println("The funds sent to the beneficiary bank have been returned (NEFT/RTGS)");
         System.out.println("Return Code: " + transactionStatus.value.getSubStatusCode());
         System.out.println("Return Reason: " + transactionStatus.value.getReason());
         break;
       case "FAILED":
         System.out.println("The transaction failed");
         System.out.println("Failure Code: " + transactionStatus.value.getSubStatusCode());
         System.out.println("Failure Reason: " + transactionStatus.value.getReason());
         break;
     }

   }

   private String parseQName(QName val) {
      if ( val != null ) {
         if ( val.getNamespaceURI() == "http://www.quantiguous.com/services" ) {
            return "ns:" + val.getLocalPart();
         }
         return val.toString();
      }
      return null;
   }

   private ApiBankingFault parseFault(SOAPFault f) {
      boolean first = false;
      ApiBankingFault apiFault = new ApiBankingFault();

      for (Iterator<QName> subCodesIterator = (Iterator<QName>)f.getFaultSubcodes(); subCodesIterator.hasNext();) {
         if (first == false) { 
            apiFault.faultCode = parseQName(subCodesIterator.next());
            first = true;
         } else {
           apiFault.faultSubCode = parseQName(subCodesIterator.next());
         }
      }
      try {
         for (Iterator<String> reasonTextsIterator = (Iterator<String>)f.getFaultReasonTexts(); reasonTextsIterator.hasNext();) {
            apiFault.faultReason = reasonTextsIterator.next();
         }
      } catch (SOAPException x) {
         x.printStackTrace(System.out); 
      }
      if ( f.hasDetail() ) {
         for (Iterator<DetailEntry> detailEntriesIterator = (Iterator<DetailEntry>)f.getDetail().getDetailEntries(); detailEntriesIterator.hasNext();) {
            System.out.println(detailEntriesIterator.next());
         }
      }
 
      return apiFault;
   }

   private void printFault(SOAPFault f) {
      ApiBankingFault apiFault = parseFault(f);

      System.out.println(apiFault.faultCode);
      System.out.println(apiFault.faultSubCode);
      System.out.println(apiFault.faultReason);
   }

   private void enableTrace() {
     System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
     System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
     System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
     System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
   }

   private void setClientCertificate() {
     System.setProperty("javax.net.ssl.keyStore", "test.jks");
     System.setProperty("javax.net.ssl.keyStorePassword", "fonepaisa");
   }


 }