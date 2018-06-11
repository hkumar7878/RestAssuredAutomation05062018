package com.projectname.utilis;

public enum EndpointURL 
{
	
	ADD_COUNTRY("/Countries/details"),
	UPDATE_COUNTRY(""),
	GET_COUNTRIES("/countries"),
	//GET_COUNTRY_BY_ID("/country/");
	GET_COUNTRY_BY_ID("/country/");
	
	String resourcePath;
	
	EndpointURL(String resourcePath)
	{
		this.resourcePath=resourcePath;
		System.out.println(this.resourcePath);
	}
	
	public String getResourcePath()
	{
		return this.resourcePath;
	}
	
	public String getResourcePath(String data)
	{
		return this.resourcePath;
	}
	
	/*public static void main(String[] args) {
		System.out.println(EndpointURL.GET_COUNTRIES.getResourcePath());
		System.out.println(URL.fixURL+EndpointURL.GET_COUNTRIES.getResourcePath());
	}*/

}
