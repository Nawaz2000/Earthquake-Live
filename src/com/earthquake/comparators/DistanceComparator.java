package com.earthquake.comparators;

import java.util.Comparator;

import com.earthquake.location.Location;
import com.earthquake.pojos.QuakeEntry;

public class DistanceComparator implements Comparator<QuakeEntry> {

	Location fromWhere;

	public DistanceComparator(Location where) {
		fromWhere = where;
	}

	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		double dist1 = q1.getLocation().distanceTo(fromWhere);
		double dist2 = q2.getLocation().distanceTo(fromWhere);
		return Double.compare(dist1, dist2);
	}

}
