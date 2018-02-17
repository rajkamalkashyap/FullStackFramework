package com.fonepaisa.GenEPG.WEBRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import com.fonepaisa.GenEPG.Exception.GenEPGException;
import com.fonepaisa.GenEPG.CommonUtil.Constants;

public class GenEPGHttpRequestHandler {
	
	static Logger log = Logger.getLogger(GenEPGHttpRequestHandler.class.getName());
	
	public static String sendHttpPOSTRequest(String messageString,String Url,HashMap<String, String> header)
			throws GenEPGException, URISyntaxException, IOException {
		
		final HttpClientBuilder hcBuilder = HttpClientBuilder.create();
        final RequestBuilder reqBuilder = RequestBuilder.post();
        final RequestConfig.Builder rcBuilder = RequestConfig.custom();
        try {
        	URL url = new URL(Url);
        	reqBuilder.setUri(url.toURI());
        	HttpClientContext hccContext = HttpClientContext.create();
        	HttpEntity entity = new StringEntity(messageString,ContentType.APPLICATION_JSON.withCharset(Charset.forName(Constants.UTF8)));
        	reqBuilder.setEntity(entity);
        	
        	// Execute:
        	RequestConfig rc = rcBuilder.build();
        	reqBuilder.setConfig(rc);
        	HttpClient hc = hcBuilder.build();
        	HttpUriRequest req = reqBuilder.build();

        	if(header != null && !header.isEmpty()){
        	  Iterator<String> itr =  header.keySet().iterator();
        	  while(itr.hasNext()){
        		 String name = itr.next();
        		 String val  = header.get(name);
        		 req.addHeader(name, val);
        	  }
        	}

        	HttpResponse response = hc.execute(req, hccContext);
        	if (response.getStatusLine().toString().contains("OK") != true) {
        		throw new GenEPGException(GenEPGException.HTTP_REQUEST_FAILURE);
        	}

        	HttpEntity body = response.getEntity();
        	InputStream is = body.getContent();
        	BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(Constants.UTF8)));
        	String line;
        	StringBuilder sb = new StringBuilder();
        	while ((line = br.readLine()) != null) {
        			sb.append(line);
        	}
        	log.debug("Response "+sb.toString() );
        	String responseString=sb.toString();
        	return responseString;
        }
        catch( URISyntaxException e){
        	e.printStackTrace();  
        	throw e;
        }
        catch(ClientProtocolException e){
        	e.printStackTrace();
        	throw e;
        }
        catch (IOException e) {
        	e.printStackTrace();	
        	throw e;
        }
	}
	
}
