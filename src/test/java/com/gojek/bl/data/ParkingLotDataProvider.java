package com.gojek.bl.data;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

/**
 * This is class has the methods to provide test scenarios for various tests.
 * @author mkarni
 *
 */
public class ParkingLotDataProvider {
	private static final String filePath = "testSceanriosfilePath";
	private static final String filePathForPrerequisites = "testPrerequisitesfilePath";
	
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
	
	@DataProvider(name = "statusPrerequisite")
	public static Object[][] getStatusTestPrerequisite(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePathForPrerequisites));
	}
	@DataProvider(name = "regNumbersForCarsWithColorDataProvider")
	public static Object[][] getRegNumbersForCarsWithColor(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
	
	@DataProvider(name = "slotNumbersForCarsWithColorDataProvider")
	public static Object[][] getSlotNumbersForCarsWithColorDataProvider(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
	
	@DataProvider(name = "slotNumbersForRegNumberDataProvider")
	public static Object[][] getSlotNumbersForRegNumberDataProvider(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter(filePath));
	}
	
	
}
