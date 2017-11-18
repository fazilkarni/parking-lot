package com.gojek.parking.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import com.gojek.parking.util.Utils;


public class CommandExecutor {

	private static Map<String, Command> availableCommands;
	private static String fileBasePath = "";
	public static void main(String[] args) {
		availableCommands = Utils.initAvailableCommands();
		if (args.length != 0) {
			processInputFile(args[0]);
		} else {
			System.out.println("Input: ");
			Scanner scanner = new Scanner(System.in);
			String commandString = scanner.nextLine();
			String[] commandParams = commandString.split(" ");
			while (!commandString.equalsIgnoreCase("bye")) {
				processCommands(commandParams);
				//Read next command
				System.out.println("Input: ");
				commandString = scanner.nextLine();
				commandParams = commandString.split(" "); 
			}
			scanner.close();
		}
	}

	private static void processInputFile(String inputFile) {
		
		System.out.println("Processing file"+inputFile+" in java process");
		BufferedReader br = null;
		try {
			String[] commandAndParams = null;
			br = new BufferedReader(new FileReader(fileBasePath+inputFile));
		    String line = br.readLine();
		    while (line != null) {
		    	commandAndParams = line.split(" ");//
		        processCommands(commandAndParams);
		        line = br.readLine();
		    }
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void processCommands(String[] commandParams){
		Command comand = availableCommands.get(commandParams[0]);
		if(comand==null){
			System.out.println(commandParams[0]+"is invalid Command.");
		}else{
			comand.execute(commandParams);
		}
	}
}
