package com.earthquake;

import java.io.IOException;

public class CLIFunctions {
	public void welcomeMessage() {
		System.out.println(
				"\n-----------------------------------------------------------WELCOME TO EARTHQUAKE LIVE-----------------------------------------------------------");
		System.out.println(
				"----------------------------------------------------------------------v1.3----------------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------Your personal real-time earthquake reporter tool------------------------------------------------");
	}

	public void usage() {
		System.out.printf("Command Usages :-\n\n");
		System.out.printf("$ help				# Opens the usage menu\n");
		System.out.printf(
				"$ magnitude-filter		# generates a list of earthquakes based on the minimum and maximum magnitudes you enter.\n");
		System.out.printf(
				"$ depth-filter			# generates a list of earthquakes based on the minimum and maximum depths you enter.\n");
		System.out.printf(
				"$ location-filter		# generates a list of earthquakes close to your provided location bounded by the maximum distance that you provide.\n");
		System.out.printf(
				"$ minMag-filter			# generates a list of earthquakes lower bounded by the minimum magnitude you enter.\n");
		System.out.println("$ help-docs			# opens up the documentation of some commands");
		System.out.println("$ exit				# Exit!");
		System.out.println("$ clear		  		# clears the screen\n");
	}

	public void documentation() {
		System.out.println("Some documentations on the above commands used in the program...\n");
		System.out.println("@ depth-filter		# In seismology, the depth of focus or focal depth refers "
				+ "to the distance \n		  	  below the earth's surface at which an earthquake occurs. "
				+ "It is always \n		  	  negative. "
				+ "For example, when asked by the program, setting \n\n		  	  Lower blound "
				+ "depth = -15 and Upper bound depth = -5\n\n		  	  will display earthquakes "
				+ "which occured at a depth between 15km and 5km below \n		  	  the earth's surface. "
				+ "When asked, the input values must be integers.");
		System.out.println(
				"\n\n@ location-filter	# Location- Refers to any location in latitudes and longitudes.\n		  	  \""
						+ "location-filter\" asks for your current or any other "
						+ "location's coordinates\n			  i.e., latitude and longitude \n\n		"
						+ "  	  (Ex- 22.572 and 88.363, which "
						+ "are the coordinates of Kolkata, India) \n\n		  	  (The maximum distance "
						+ "in meters- refers to the radius from your location), \n\n		  	"
						+ "  The program takes your given location and displays earthquakes within "
						+ "\n			  your inputted radius. When asked for, "
						+ "the input values must be decimals.");
		System.out.println("\n\n@ magnitude-filter	# Earthquake size is a quantitative measure of the "
				+ "size of the earthquake at \n			  its source. The Magnitude "
				+ "Scale measures the amount of seismic energy \n			  released "
				+ "by an earthquake. Enter the minimum and maximum magnitudes to get "
				+ "\n			  list of earthquakes within that range. When asked, the "
				+ "input values must \n			  be decimals.\n");
	}

	public void clear() throws InterruptedException, IOException {
		if (System.getProperty("os.name").contains("Windows"))
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		else
			Runtime.getRuntime().exec("clear");
		welcomeMessage();
		usage();
	}
}
