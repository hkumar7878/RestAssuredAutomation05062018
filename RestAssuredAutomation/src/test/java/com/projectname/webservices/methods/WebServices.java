package com.projectname.webservices.methods;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class WebServices {
	
	
	public static Response Get(String uRI)
	{
		RequestSpecification requestSpecification=RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.get(uRI);
		return response;
	}
	
	public static Response Post(String uRI,String stringJSON)
	{
		RequestSpecification requestSpecification=RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.post(uRI);
		return response;
		
	}

	public static Response Put(String uRI,String stringJSON)
	{
		RequestSpecification requestSpecification=RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.put(uRI);
		return response;
		
	}
	
	public static Response Delete(String uRI,String stringJSON)
	{
		RequestSpecification requestSpecification=RestAssured.given().body(stringJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(uRI);
		return response;
		
	}
}
