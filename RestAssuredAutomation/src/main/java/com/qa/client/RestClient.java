package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient {
	
	public void get(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet(url);
		CloseableHttpResponse closeHttpResponse=httpClient.execute(httpGet);
		
		// 1 To get the status code
		
		int stausCode=closeHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is " + stausCode);
		
		// 2 To get the Json String
		
		String responseString=EntityUtils.toString(closeHttpResponse.getEntity(),"UTF-8");
		System.out.println("Response JSON from API " + responseString);
		
		// 3 To get all the headers
		
		Header [] headerArray=closeHttpResponse.getAllHeaders();
		HashMap<String,String> allheaders=new HashMap<String,String>();
		for(Header header: headerArray)
		{
			allheaders.put(header.getName(), header.getValue());
			System.out.println("All headers arrays are" + allheaders	);
		}
	}

}
