package com.projectname.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetStatusCodeTest {
	
	@BeforeClass
	public void setBaseURL()
	{
		RestAssured.baseURI="https://maps.googleapis.com";
	}
	
	
}
