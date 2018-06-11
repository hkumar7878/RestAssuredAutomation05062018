package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase()
	{
		try
		
		{
			prop = new Properties();
			FileInputStream ip= new FileInputStream(System.getProperty("user.dir") + "src//main//java//com//qa//properties//config.properties");
			prop.load(ip);
		}
		
		catch(Exception e)
		{
			e.getMessage();
		}
	}

}
