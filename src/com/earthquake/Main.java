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
	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, InterruptedException {
		CLIFunctions obj = new CLIFunctions();
		obj.welcomeMessage();
		obj.usage();
		obj.documentation();

		Scanner scan = new Scanner(System.in);
		System.out.println("Read the above carefully and press any key to continue: ");
		scan.nextLine();
		obj.clear();
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
				System.out.println("\nEnter minimum magnitude: ");
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
				System.out.println("\nEnter lower-bound depth in (-ve)meters: ");

				if (scan.hasNextInt()) {
					minDepth = scan.nextInt();
					scan.nextLine();

					System.out.println("Enter upper-bound depth in (-ve)meters: ");
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
				System.out.println("\nEnter latitude: ");

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
				System.out.println("\nEnter minimum magnitude: ");
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
				obj.usage();
				break;
			case "clear":
				obj.clear();
				break;
			case "help-docs":
				obj.documentation();
				break;
			default:
				System.out.println("\nInvalid input! Please Enter commands from the usage...");
			}

			System.out.println("\n\nEnter your choice: ");
			choice = scan.nextLine();
		}
		scan.close();
	}

}
