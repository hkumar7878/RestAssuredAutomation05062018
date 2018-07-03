package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.qa.util.API_ActionBeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	public static ExtentReports usersReport;
	public static ExtentReports countriesReport;
    public static ExtentTest users_logger;
    public static ExtentTest countries_logger;
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
	
	@BeforeTest
	public void beforeTestInit()
	{
		
	}
	
	@AfterTest
	public void afterTest()
	{
		
	}

}
