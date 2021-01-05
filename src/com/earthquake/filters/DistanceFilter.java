package com.earthquake.filters;
import com.earthquake.location.Location;
import com.earthquake.pojos.QuakeEntry;

public class DistanceFilter implements Filter {

	private String name;
	private double maxDistance;
	private Location loc;

	public DistanceFilter(Location cLoc, double dist) {
		loc = cLoc;
		maxDistance = dist;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		return loc.distanceTo(qe.getLocation()) < maxDistance;
	}

	@Override
	public String getName() {
//		return "Distance Filter";
		return name;
	}
}
