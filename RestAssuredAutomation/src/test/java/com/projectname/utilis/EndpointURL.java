package com.projectname.utilis;

public enum EndpointURL 
{
	
	ADD_COUNTRY("/Countries/details"),
	UPDATE_COUNTRY(""),
	GET_COUNTRIES("/countries");
	
	String resourcePath;
	
	EndpointURL(String resourcePath)
	{
		this.resourcePath=resourcePath;
	}
	
	public String getResourcePath()
	{
		return this.resourcePath;
	}
	
	public static void main(String[] args) {
		System.out.println(EndpointURL.GET_COUNTRIES.getResourcePath());
		System.out.println(URL.fixURL+EndpointURL.GET_COUNTRIES.getResourcePath());
	}

}
