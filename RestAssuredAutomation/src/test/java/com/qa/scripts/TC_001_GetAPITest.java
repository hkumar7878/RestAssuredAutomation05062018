package com.qa.scripts;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class TC_001_GetAPITest extends TestBase {
	
	TestBase testbaseObj;
	String serviceURL;
	String apiURL;
	String url;
	CloseableHttpResponse closeHttpResponseObj;
	
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
		try{
			RestClient restClientObj= new RestClient();
			closeHttpResponseObj=restClientObj.get(url);
			//restClientObj.get(url);
			
			// 1 Get and Verify response code
			int statusCode=closeHttpResponseObj.getStatusLine().getStatusCode();
			System.out.println("Response code is ---" +statusCode);
			Assert.assertEquals(TestBase.RESPONSE_STATUS_CODE_200, statusCode,"Response code is incorrect");
					
			// 2 Get and Verify json string
			String responseString=EntityUtils.toString(closeHttpResponseObj.getEntity(),"UTF-8");
			JSONObject responseJson= new JSONObject(responseString);
			System.out.println("Response JSON from API " + responseString);
			
			// single value assertion:
			// per page
			String perPageValue=TestUtil.getValueByJPath(responseJson, "/per_page");
			System.out.println("Per page value is" +perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 3);
			
			
			// total value assertion
			String totalVal=TestUtil.getValueByJPath(responseJson, "/total");
			System.out.println("Total Value is" + totalVal );
			Assert.assertEquals(Integer.parseInt(totalVal), 12);
			
			
			// get the value of array
			String lastName=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
			String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
			String avatar=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
			String firstName=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
			
			System.out.println(lastName);
			System.out.println(id);
			System.out.println(avatar);
			System.out.println(firstName);
			
			
			
			
			
			
			
			
			// 3 Get and Verify all the headers
			Header [] headerArray=closeHttpResponseObj.getAllHeaders();
			HashMap<String,String> allheaders=new HashMap<String,String>();
			for(Header header: headerArray)
			{
				allheaders.put(header.getName(), header.getValue());
				System.out.println("All headers arrays are" + allheaders);

			}
	
		}
		
		catch(Exception e)
		{
			String err_MSG=e.getMessage();
			System.out.println(e.getMessage());
		}
	}
}
