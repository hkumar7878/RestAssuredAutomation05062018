package com.qa.util;

import org.testng.ITestResult;

import com.qa.base.TestBase;

public class API_ActionAfterMethod {
	
	public static void actionAfterMethod(ITestResult result, String methodName)
	{
		System.out.println("After Method Execution");
		int status = result.getStatus();
		switch (status) 
		{
		case ITestResult.SUCCESS:
			TestBase.usersReport.endTest(TestBase.users_logger);
			TestBase.usersReport.flush();
			System.out.println("Extent report is flushed for Users");
			break;
		case ITestResult.FAILURE:
			TestBase.usersReport.endTest(TestBase.users_logger);
			TestBase.usersReport.flush();
			System.out.println("Extent report is flushed for Users");
			break;
		}
	}
}


