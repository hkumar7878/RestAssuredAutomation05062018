package com.qa.util;

import org.testng.ITestContext;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class API_ActionBeforeTest extends TestBase{
	
//public static void beforeTestAction(String testSuiteName,String testName,String tcDesc)
	//public static void beforeTestAction(ITestContext testContext,String testName,String tcDesc)
	public static void beforeTestAction(String testName,String tcDesc)
	
	{
		String textCtnxt=ITestContext.class.toString();
		
		try{
			
			//this.deviceName;
			
			
			if(testName.equals("users"))
			{
				System.out.println("Inside beforeTestAction method and trying to start the report test");
				//users_logger=usersReport.startTest(textCtnxt, tcDesc);
				System.out.println("logger has initizliaed");
				System.out.println("Inside Before Test class of BASE CLASS: FIREFOX");
                String filePath=System.getProperty("user.dir")+"\\"+ "Users.html";
                usersReport=new ExtentReports(filePath,true, DisplayOrder.OLDEST_FIRST);
                System.out.println("initi");
			}

        else if(testName.equals("countries"))
        {
        	System.out.println("Inside beforeTestAction method and trying to start the report test");
        	//countries_logger=countriesReport.startTest(textCtnxt, tcDesc);
        }
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
