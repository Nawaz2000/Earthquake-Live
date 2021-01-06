package com.earthquake.clients;

import java.util.*;

import com.earthquake.comparators.DepthComparator;
import com.earthquake.comparators.DistanceComparator;
import com.earthquake.comparators.MagnitudeComparator;
import com.earthquake.filters.DepthFilter;
import com.earthquake.filters.DistanceFilter;
import com.earthquake.filters.Filter;
import com.earthquake.filters.MagnitudeFilter;
import com.earthquake.filters.MatchAllFilter;
import com.earthquake.filters.MinMagFilter;
import com.earthquake.filters.PhraseFilter;
import com.earthquake.location.Location;
import com.earthquake.parser.EarthQuakeParser;
import com.earthquake.pojos.QuakeEntry;

//import edu.duke.*;

public class EarthQuakeClient {
	public EarthQuakeClient() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		for (QuakeEntry qe : quakeData) {
			if (f.satisfies(qe)) {
				answer.add(qe);
			}
		}

		return answer;
	}

	public void quakesWithFilter() {
		EarthQuakeParser parser = new EarthQuakeParser();
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		String source = "data/nov20quakedata.atom";
		ArrayList<QuakeEntry> list = parser.read(source);
		System.out.println("read data for " + list.size() + " quakes");

		Filter f = new MagnitudeFilter(3.5, 4.5);
		ArrayList<QuakeEntry> magF = filter(list, f);

		f = new DepthFilter(-55000, -20000);
		ArrayList<QuakeEntry> depF = filter(magF, f);
		System.out.println("Results found: " + depF.size());
		for (QuakeEntry qe : depF)
			System.out.println(qe.toString());

//		Location cLoc = new Location(39.7392, -104.9903);
//		Filter f = new DistanceFilter(cLoc, 1000000);
//		ArrayList<QuakeEntry> disF = filter(list, f);
//
//		f = new PhraseFilter("end", "a");
//		ArrayList<QuakeEntry> phraseF = filter(disF, f);
//		System.out.println("Results found: " + phraseF.size());
//		for (QuakeEntry qe : phraseF)
//			System.out.println(qe.toString());

	}

	public void testMatchAllFilters() {
		String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		EarthQuakeParser parser = new EarthQuakeParser();
		ArrayList<QuakeEntry> list = parser.read(source);

//		for (QuakeEntry qe : list)
//			System.out.println(qe.toString());
		System.out.println("# quakes = " + list.size());

		MatchAllFilter maf = new MatchAllFilter();
		Filter mf = new MagnitudeFilter(1.0, 4.0);
		Filter df = new DepthFilter(-180000, -30000);
		Filter pf = new PhraseFilter("any", "o");
		maf.addFilter(mf);
		maf.addFilter(df);
		maf.addFilter(pf);

		ArrayList<QuakeEntry> answers = filter(list, maf);
		System.out.println("Results found: " + answers.size());
		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());

		System.out.println(maf.getName());
	}

	public void testMatchAllFilters2() {
		String source = "data/nov20quakedata.atom";
		// String source =
		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
		EarthQuakeParser parser = new EarthQuakeParser();
		ArrayList<QuakeEntry> list = parser.read(source);

//		for (QuakeEntry qe : list)
//			System.out.println(qe.toString());
		System.out.println("# quakes = " + list.size());

		MatchAllFilter maf = new MatchAllFilter();
		Filter mf = new MagnitudeFilter(0.0, 5.0);
		Location cLoc = new Location(55.7308, 9.1153);
		Filter df = new DistanceFilter(cLoc, 3000000);
		Filter pf = new PhraseFilter("any", "e");
		maf.addFilter(mf);
		maf.addFilter(df);
		maf.addFilter(pf);

		ArrayList<QuakeEntry> answers = filter(list, maf);
		System.out.println("Results found: " + answers.size());
		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());
		System.out.println(maf.getName());
	}

//	public void createCSV() {
//		EarthQuakeParser parser = new EarthQuakeParser();
//		// String source = "../data/nov20quakedata.atom";
//		String source = "data/nov20quakedatasmall.atom";
//		// String source =
//		// "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//		ArrayList<QuakeEntry> list = parser.read(source);
//		dumpCSV(list);
//		System.out.println("# quakes read: " + list.size());
//	}

//	public void dumpCSV(ArrayList<QuakeEntry> list) {
//		System.out.println("Latitude,Longitude,Magnitude,Info");
//		for (QuakeEntry qe : list) {
//			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n", qe.getLocation().getLatitude(), qe.getLocation().getLongitude(),
//					qe.getMagnitude(), qe.getInfo());
//		}
//	}

	public void magResponse(double min, double max, ArrayList<QuakeEntry> quakeData) {
		Filter mf = new MagnitudeFilter(min, max);
		ArrayList<QuakeEntry> answers = filter(quakeData, mf);
		System.out.println("Results found: " + answers.size());
		Collections.sort(answers, new MagnitudeComparator());

		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());
	}

	public void depthResponse(int min, int max, ArrayList<QuakeEntry> quakeData) {
		Filter df = new DepthFilter(min, max);
		ArrayList<QuakeEntry> answers = filter(quakeData, df);
		System.out.println("Results found: " + answers.size());
		Collections.sort(answers, new DepthComparator());

		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());
	}

	public void locationResponse(double latitude, double longitude, ArrayList<QuakeEntry> quakeData, int distance) {
		Location currLocation = new Location(latitude, longitude);
		Filter lf = new DistanceFilter(currLocation, distance);
		ArrayList<QuakeEntry> answers = filter(quakeData, lf);
		System.out.println("Results found: " + answers.size());
		Collections.sort(answers, new DistanceComparator(currLocation));

		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());
	}

	public void minMagResponse(double minMagnitude, ArrayList<QuakeEntry> quakeData) {
		Filter mmf = new MinMagFilter(minMagnitude);
		ArrayList<QuakeEntry> answers = filter(quakeData, mmf);
		System.out.println("Results found: " + answers.size());
		Collections.sort(answers, new MagnitudeComparator());

		for (QuakeEntry qe : answers)
			System.out.println(qe.toString());
	}

}
