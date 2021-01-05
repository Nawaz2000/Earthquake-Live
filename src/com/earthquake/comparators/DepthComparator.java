package com.earthquake.comparators;

import java.util.Comparator;

import com.earthquake.pojos.QuakeEntry;

public class DepthComparator implements Comparator<QuakeEntry> {

	@Override
	public int compare(QuakeEntry o1, QuakeEntry o2) {
		return Double.compare(o1.getDepth(), o2.getDepth());
	}

}
