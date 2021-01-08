package com.earthquake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.earthquake.clients.EarthQuakeClient;
import com.earthquake.parser.EarthQuakeParser;
import com.earthquake.pojos.QuakeEntry;

public class Main {

	public static void welcomeMessage() {
		System.out.println(
				"\n-----------------------------------------------------------WELCOME TO EARTHQUAKE LIVE-----------------------------------------------------------");
		System.out.println(
				"----------------------------------------------------------------------v1.3----------------------------------------------------------------------");
		System.out.println(
				"------------------------------------------------Your personal real-time earthquake reporter tool------------------------------------------------");
	}

	public static void help() {
		System.out.printf("Command Usages :-\n\n");
		System.out.printf("$ help				# Opens the help menu\n");
		System.out.printf(
				"$ magnitude-filter		# generates a list of earthquakes based on the minimum and maximum magnitudes you enter.\n");
		System.out.printf(
				"$ depth-filter			# generates a list of earthquakes based on the minimum and maximum depths you enter.\n");
		System.out.printf(
				"$ location-filter		# generates a list of earthquakes close to your provided location bounded by the maximum distance that you provide.\n");
		System.out.printf(
				"$ minMag-filter			# generates a list of earthquakes lower bounded by the minimum magnitude you enter.\n");
		System.out.println("$ exit				# Exit!\n");
	}

	public static void terms() {
		System.out.println("Some help on the above commands used in the program...\n");
		System.out.println("@ depth-filter		# In seismology, the depth of focus or focal depth refers "
				+ "to the distance \n		  	  below the earth's surface at which an earthquake occurs. "
				+ "It is always \n		  	  negative. "
				+ "For example, when asked by the program, setting \n\n		  	  Lower blound "
				+ "depth = -15000 and Upper bound depth = -5000\n\n		  	  will display earthquakes "
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

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		welcomeMessage();
		help();
		terms();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		String choice = scan.nextLine();

		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom";
		ArrayList<QuakeEntry> quakeData = parser.read(source);

		EarthQuakeClient client = new EarthQuakeClient();

		while (!choice.equalsIgnoreCase("exit")) {
			switch (choice) {
			case "magnitude-filter":
				Double minMag = null, maxMag = null;
				System.out.println("Enter minimum magnitude: ");
				if (scan.hasNextDouble()) {
					minMag = scan.nextDouble();
					scan.nextLine();

					System.out.println("Enter maximum magnitude: ");
					if (scan.hasNextDouble()) {
						maxMag = scan.nextDouble();
						scan.nextLine();

						client.magResponse(minMag, maxMag, quakeData);
					} else {
						scan.nextLine();
						System.out.println("Invalid input!");
					}

				} else {
					scan.nextLine();
					System.out.println("Invalid input!");
				}

				break;
			case "depth-filter":
				Integer minDepth = null, maxDepth = null;
				System.out.println("Enter lower-bound depth: ");

				if (scan.hasNextInt()) {
					minDepth = scan.nextInt();
					scan.nextLine();

					System.out.println("Enter upper-bound depth: ");
					if (scan.hasNextInt()) {
						maxDepth = scan.nextInt();
						scan.nextLine();

						client.depthResponse(minDepth, maxDepth, quakeData);
					} else {
						scan.nextLine();
						System.out.println("Invalid input!");
					}

				} else {
					scan.nextLine();
					System.out.println("Invalid input!");
				}

				break;
			case "location-filter":
				Double latitude = null, longitude = null;
				System.out.println("Enter latitude: ");

				if (scan.hasNextDouble()) {
					latitude = scan.nextDouble();
					scan.nextLine();

					System.out.println("Enter longitude: ");
					if (scan.hasNextDouble()) {
						longitude = scan.nextDouble();
						scan.nextLine();

						Integer maxDistance = null;
						System.out.println("Enter max distance in kilometers from your location: ");
						if (scan.hasNextInt()) {
							maxDistance = scan.nextInt();
							scan.nextLine();
							client.locationResponse(latitude, longitude, quakeData, (maxDistance * 1000));
						} else {
							scan.nextLine();
							System.out.println("Invalid input!");
						}
					} else {
						scan.nextLine();
						System.out.println("Invalid input!");
					}

				} else {
					scan.nextLine();
					System.out.println("Invalid input!");
				}

				break;
			case "minMag-filter":
				Double minMagnitude = null;
				System.out.println("Enter minimum magnitude: ");
				if (scan.hasNextDouble()) {
					minMagnitude = scan.nextDouble();
					scan.nextLine();

					client.minMagResponse(minMagnitude, quakeData);
				} else {
					scan.nextLine();
					System.out.println("Invalid input!");
				}
				break;
			case "help":
				help();
				break;
			default:
				System.out.println("Invalid input! Please Enter commands from the usage...");
			}

			System.out.println("\n\nEnter your choice: ");
			choice = scan.nextLine();
		}
		scan.close();
	}

}
