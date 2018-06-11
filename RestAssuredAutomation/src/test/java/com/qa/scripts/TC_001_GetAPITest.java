package com.qa.scripts;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class TC_001_GetAPITest extends TestBase {
	
	TestBase testbaseObj;
	String serviceURL;
	String apiURL;
	String url;;
	
	@BeforeMethod
	public void setUp()
	{
		testbaseObj = new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL=prop.getProperty("serviceURL");
		url=serviceURL+apiURL;
		System.out.println("Complete URL is " + url);
	}

	
	@Test
	public void getTest() throws ClientProtocolException, IOException
	{
		RestClient restClientObj= new RestClient();
		restClientObj.get("url");
	}
}
