package com.earthquake.filters;
import com.earthquake.pojos.QuakeEntry;

public class DepthFilter implements Filter {

	private int minDepth, maxDepth;

	public DepthFilter(int min, int max) {
		minDepth = min;
		maxDepth = max;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		return qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth;
	}

	@Override
	public String getName() {
		return "Depth filter";
	}

}
