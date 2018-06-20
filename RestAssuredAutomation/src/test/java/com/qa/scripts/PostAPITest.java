package com.qa.scripts;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{
	TestBase testbaseObj;
	String serviceURL;
	String apiURL;
	String url;
	CloseableHttpResponse closeHttpResponseObj;
	RestClient restClient;
	
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
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException
	{
		restClient=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type", "application/json");
		
		
		// jackson API's..It is used to marshalling i.e to convert java class to json
		
		ObjectMapper mapper= new ObjectMapper();
		Users users= new Users("morpheus","leader");
		
		// Now covert java class to json
		
		mapper.writeValue(new File("C://Users//hiteshg//git//RestAssuredAutomation05062018//RestAssuredAutomation//src//main//java//com//qa//data//users.json"), users);
		
		// object to json in String
		
		String userJsonString=mapper.writeValueAsString(users);
		System.out.println(userJsonString);
		closeHttpResponseObj=restClient.post(url, userJsonString, headerMap);
		
		
		// 1 . Status code verification
		
		int statusCode=closeHttpResponseObj.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testbaseObj.RESPONSE_STATUS_CODE_201);
		
		
		// 2. Validate json String
		
		String responseString=EntityUtils.toString(closeHttpResponseObj.getEntity(),"UTF-8");
		
		JSONObject responseJson= new JSONObject(responseString);
		System.out.println("The response from API is--->" + responseJson);
		Users usersResObj=mapper.readValue(responseString, Users.class);  // actual users object
		System.out.println("users object is " + usersResObj);
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
