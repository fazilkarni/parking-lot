package com.gojek.bl.data;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;


public class ParkingLotDataProvider {
	private static final String filePath = "testSceanriosfilePath";
	
	@DataProvider(name = "createParkingLotDataProvider")
	public static Object[][] getDataProvider(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
	@DataProvider(name = "parkDataProvider")
	public static Object[][] getParkTestScenarios(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
	
	@DataProvider(name = "leaveDataProvider")
	public static Object[][] getLeaveTestScenarios(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
}
