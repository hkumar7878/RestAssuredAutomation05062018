package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public static int RESPONSE_STATUS_CODE_200=200;
	public static int RESPONSE_STATUS_CODE_500=500;
	public static int RESPONSE_STATUS_CODE_400=400;
	public static int RESPONSE_STATUS_CODE_401=401;
	public static int RESPONSE_STATUS_CODE_201=201;
	
	public TestBase()
	{
		try
		
		{
			prop = new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir") + "//src//main//java//com//qa//properties//config.properties");
			prop.load(ip);
		}
		
		catch(Exception e)
		{
			e.getMessage();
		}
	}

}
