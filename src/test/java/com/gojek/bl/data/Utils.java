package com.gojek.bl.data;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains helper methods to read and create data providers.
 * @author mkarni
 *
 */
public class Utils {
	
	public static Object[][] dataLoaderFrom(String fileName) {
		Object[][] dataProvider = null;
		int headerCount = 0;
		List<ArrayList<String>> testScenarios = new ArrayList<ArrayList<String>>();
		Path pathToFile = Paths.get(fileName);
		try {
			BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);

			// skip the line till test_scenarios row in the input file.
			String line = br.readLine();
			while (!line.contains("test_scenarios")) {
				line = br.readLine();
			}

			// read the first line of data from the text file
			line = br.readLine();
			// Compute total headers
			String[] headers = line.split(",");

			for (String header : headers) {
				if (!header.equals(""))
					headerCount++;
			}
			// System.out.println("Header" + line);
			line = br.readLine();// this is required to get first line of the
									// data.
			String[] input_output = null;
			ArrayList<String> testScenario = null;
			// loop until all lines are read
			while (line != null) { // use string.split to load a string array
									// with the values from // each line of //
									// the file, using a comma as the delimiter
				input_output = line.split(",");
				testScenario = new ArrayList<String>();
				for (int index = 0; index < headerCount; index++) {
					testScenario.add(input_output[index]);
				}
				testScenarios.add(testScenario);
				// read next line before looping // if end of file reached, line
				// would be null
				line = br.readLine();
			}
			dataProvider = new String[testScenarios.size()][headerCount];
			int index1 = 0;
			int scenarioInOutputSize = 0;
			for (ArrayList<String> scenario : testScenarios) {
				scenarioInOutputSize = scenario.size();
				for (int index2 = 0; index2 < scenarioInOutputSize; index2++) {
					dataProvider[index1][index2] = scenario.get(index2);
				}
				index1++;

			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
			dataProvider = null;
		}
		// Convert the List<ArrayList<String>> to an array of arrays.

		return dataProvider;
	}

}
