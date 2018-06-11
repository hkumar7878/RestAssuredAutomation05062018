package com.projectname.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.projectname.utilis.EndpointURL;
import com.projectname.utilis.URL;
import com.projectname.webservices.methods.WebServices;

import io.restassured.response.Response;

public class TC_001 {
	
	Response response;
	@BeforeClass
	public void setUp()
	{
		
	}
	
	/*@Test
	public void verifyGetCountries()
	{
		//String url=URL.fixURL+EndpointURL.GET_COUNTRIES.getResourcePath();
		String url=URL.fixURL+EndpointURL.GET_COUNTRIES.getResourcePath();
		response=WebServices.Get(url);
		System.out.println(response.getBody().asString());
	}*/
	
	@Test
	public void verifyGetCountry()
	{
		//String url=URL.fixURL+EndpointURL.GET_COUNTRY_BY_ID.getResourcePath("/?id=2172797");
		String url="api.openweathermap.org/data/2.5/weather?id=2172797";
		response=WebServices.Get(url);
		System.out.println(response.getBody().asString());
		
	}

}
