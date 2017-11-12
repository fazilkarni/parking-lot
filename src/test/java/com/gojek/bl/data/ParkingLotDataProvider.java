package com.gojek.bl.data;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;


public class ParkingLotDataProvider {

	@DataProvider(name = "createParkingLotDataProvider")
	public static Object[][] getDataProvider(ITestContext context) {
		return Utils.dataLoaderFrom(context.getCurrentXmlTest().getParameter("testSceanriosfilePath"));
	}
}
