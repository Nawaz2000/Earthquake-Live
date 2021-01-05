package com.earthquake.filters;

import com.earthquake.pojos.QuakeEntry;

public class MagnitudeFilter implements Filter {

	private double minMag, maxMag;

	public MagnitudeFilter(double min, double max) {
		minMag = min;
		maxMag = max;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		return qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag;
	}

	@Override
	public String getName() {
		return "Magnitude Filter";
	}

}
