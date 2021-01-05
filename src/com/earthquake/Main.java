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
//		EarthQuakeParser xp = new EarthQuakeParser();
		// String source = "data/2.5_week.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//		String source = "data/nov20quakedatasmall.atom";
//		ArrayList<QuakeEntry> list = xp.read(source);
//		Collections.sort(list);
//		for (QuakeEntry loc : list) {
//			System.out.println(loc);
//		}
//		System.out.println("# quakes = " + list.size());
//		EarthQuakeClient2 obj = new EarthQuakeClient2();
//		obj.testMatchAllFilters2();
//		obj.quakesWithFilter();

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		String choice = scan.nextLine();

		EarthQuakeParser parser = new EarthQuakeParser();
		String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_day.atom";
		ArrayList<QuakeEntry> quakeData = parser.read(source);

		while (!choice.equalsIgnoreCase("exit")) {
			switch (choice) {
			case "magnitude-filter":
				System.out.println("Enter lower-bound magnitude: ");
				String min = scan.nextLine();
				System.out.println("Enter upper-bound magnitude: ");
				String max = scan.nextLine();

				EarthQuakeClient2 client = new EarthQuakeClient2();
				client.magResponse(Integer.parseInt(min), Integer.parseInt(max), quakeData);
				break;

			}

			System.out.println("Enter your choice: ");
			choice = scan.nextLine();
		}
		scan.close();
	}

}
