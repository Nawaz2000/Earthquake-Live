package com.earthquake.filters;
import com.earthquake.pojos.QuakeEntry;

/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter {
	private double magMin;

	public MinMagFilter(double min) {
		magMin = min;
	}

	public boolean satisfies(QuakeEntry qe) {
		return qe.getMagnitude() >= magMin;
	}

	@Override
	public String getName() {
		return "Minimum magnitude Filter";
	}

}
