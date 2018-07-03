package com.qa.scripts;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.API_ActionAfterMethod;
import com.qa.util.API_ActionBeforeTest;
import com.qa.util.API_DataProviderUtil;
import com.qa.util.TestUtil_API;
import com.qa.verificationmethods.GetRequest_VerificationMethod;

public class TC_001_GetAPITest extends TestBase {
	
	TestBase testbaseObj;
	String serviceURL;
	String apiURL;
	String url;
	String methodName;
	CloseableHttpResponse closeHttpResponseObj;
	
	@DataProvider(name="userData")
    public String[][] getTestData()
    {
    	String[][] loginData = API_DataProviderUtil.getUsersData("UsersURIs.xlsx", "Users URIs");
    	return loginData;
    }
	
	@BeforeTest
	public void beforeTest(ITestContext testContext)
	{
		String testName=testContext.getName();
		System.out.println("Inside before test of users" + testName);
		API_ActionBeforeTest.beforeTestAction(testName, "Verify Get request for users API");
	}
	
	@BeforeMethod
	public void setUp(Method method)
	{
		testbaseObj = new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL=prop.getProperty("serviceURL");
		//url=serviceURL+apiURL;
		methodName=method.getName();
		System.out.println("Complete URL is " + url);
		users_logger=usersReport.startTest(methodName);
	}

	
	@Test (dataProvider="userData")
	public void getRequestTest_SpecificUser(String condition,String uRIVal) throws ClientProtocolException, IOException
	{
		try{
			//String methodName=method.getName();
			RestClient restClientObj= new RestClient();
			url=serviceURL+uRIVal;
			closeHttpResponseObj=restClientObj.get(url);
			//restClientObj.get(url);
			
			// 1 Get and Verify response code
			int statusCode=closeHttpResponseObj.getStatusLine().getStatusCode();
			System.out.println("Response code is ---" +statusCode);
			GetRequest_VerificationMethod.verifyGetStatusCode(statusCode, methodName);
			//Assert.assertEquals(TestBase.RESPONSE_STATUS_CODE_200, statusCode,"Response code is incorrect");
					
			// 2 Get and Verify json string
			String responseString=EntityUtils.toString(closeHttpResponseObj.getEntity(),"UTF-8");
			JSONObject responseJson= new JSONObject(responseString);
			System.out.println("Response JSON from API " + responseString);
			
			// single value assertion:
			// per page
			String perPageValue=TestUtil_API.getValueByJPath(responseJson, "/per_page");
			System.out.println("Per page value is" +perPageValue);
			Assert.assertEquals(Integer.parseInt(perPageValue), 3);
			
			
			// total value assertion
			String totalVal=TestUtil_API.getValueByJPath(responseJson, "/total");
			System.out.println("Total Value is" + totalVal );
			Assert.assertEquals(Integer.parseInt(totalVal), 12);
			
			
			// get the value of array
			String lastName=TestUtil_API.getValueByJPath(responseJson, "/data[0]/last_name");
			String id=TestUtil_API.getValueByJPath(responseJson, "/data[0]/id");
			String avatar=TestUtil_API.getValueByJPath(responseJson, "/data[0]/avatar");
			String firstName=TestUtil_API.getValueByJPath(responseJson, "/data[0]/first_name");
			
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
	
	@AfterMethod
	public void afterMethod(ITestResult result)
	{
		System.out.println("Inside After Method of test case and result is " + result.getStatus());
		try
        {

            System.out.println("Inside After Method of test case");
            API_ActionAfterMethod.actionAfterMethod(result, methodName);
           // logger.info("###############Finished Leave Verification Test ################");
        }

        catch (Exception e)
        {
            System.out.println("Excpetion is " + e.getMessage());
        }
	}
}
