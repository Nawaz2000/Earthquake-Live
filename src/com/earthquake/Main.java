package com.earthquake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.earthquake.clients.EarthQuakeClient2;
import com.earthquake.parser.EarthQuakeParser;
import com.earthquake.pojos.QuakeEntry;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		String choice = scan.nextLine();

		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom";
		ArrayList<QuakeEntry> quakeData = parser.read(source);

		EarthQuakeClient2 client = new EarthQuakeClient2();

		while (!choice.equalsIgnoreCase("exit")) {
			switch (choice) {
			case "magnitude-filter":
				System.out.println("Enter lower-bound magnitude: ");
				String minMag = scan.nextLine();
				System.out.println("Enter upper-bound magnitude: ");
				String maxMag = scan.nextLine();

				client.magResponse(Integer.parseInt(minMag), Integer.parseInt(maxMag), quakeData);
				break;
			case "depth-filter":
				System.out.println("Enter lower-bound depth: ");
				String minDepth = scan.nextLine();
				System.out.println("Enter upper-bound depth: ");
				String maxDepth = scan.nextLine();

				client.depthResponse(Integer.parseInt(minDepth), Integer.parseInt(maxDepth), quakeData);
				break;
			case "location-filter":
				System.out.println("Enter latitude: ");
				String latitude = scan.nextLine();
				System.out.println("Enter longitude: ");
				String longitude = scan.nextLine();
				System.out.println("Enter max distance in meters from your location: ");
				String maxDistance = scan.nextLine();

				client.locationResponse(Double.parseDouble(latitude), Double.parseDouble(longitude), quakeData,
						Integer.parseInt(maxDistance));
				break;
			case "minMag-filter":
				System.out.println("Enter min magnitude: ");
				String minMagnitude = scan.nextLine();

				client.minMagResponse(Double.parseDouble(minMagnitude), quakeData);
			}

			System.out.println("Enter your choice: ");
			choice = scan.nextLine();
		}
		scan.close();
	}

}
