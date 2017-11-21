Following are the TWO methods to execute the application
  
  Method 1: 
  		
  		./parking_lot.sh file_inputs.txt
 
 
 Above command does the following 
 	1. compiles the code
 	2. runs the test cases
 	3. generates parking-lot-1.0.jar
 	4. processes the commands mentioned in the file_inputs.txt file.  
Please note file_inputs.txt is provided along with the code with the sample data.
 	
  Method 2: 	
  
        ./parking_lot.sh
        
 Above command does the following 
 	1. compiles the code
 	2. runs the test cases
 	3. generates parking-lot-1.0.jar
 	4. waits for user to enter the commands to be executed. Following are the possible command.
 	
 			a. create_parking_lot <total slots to be created>
 			b. park <registration_number in the format "[A-Z]{2}-[0-9]{2}-[A-Z]+-[0-9]+"> <color such as BLUE>  
 			c. leave <slot number of the car>
 			d. status
 			e. registration_numbers_for_cars_with_colour <color such as BLUE>
 			f. slot_numbers_for_cars_with_colour <color such as BLUE>
 			g. slot_number_for_registration_number <registration number in the format  "[A-Z]{2}-[0-9]{2}-[A-Z]+-[0-9]+"> 
 	
 			       
System requirements:

	To run this application you need following system configuration
	
	java 8.
	maven 3.5.2 or 	higher version.		
	Unix/Linux/Mac OS       
 			       