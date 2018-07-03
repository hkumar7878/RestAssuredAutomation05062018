package com.qa.verificationmethods;

import org.testng.Assert;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

import ErrorCollectors.ErrorCollector;

public class GetRequest_VerificationMethod extends TestBase{

	public static void verifyGetStatusCode(int statusCode, String methodName) {
		try {
			Assert.assertEquals(TestBase.RESPONSE_STATUS_CODE_200, statusCode, "Response code is incorrect");
			System.out.println("Reponse code is correct");
			users_logger.log(LogStatus.PASS, "Step1","Status code is matching");
		}

		catch (Exception e) {
			e.getMessage();
			ErrorCollector.addVerificationFailure(e);
		}
	}

}
