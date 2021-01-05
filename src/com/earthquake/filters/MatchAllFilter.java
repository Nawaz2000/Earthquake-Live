package com.earthquake.filters;
import java.util.ArrayList;

import com.earthquake.pojos.QuakeEntry;

public class MatchAllFilter implements Filter {

	private ArrayList<Filter> filterList;

	public MatchAllFilter() {
		filterList = new ArrayList<Filter>();
	}

	public void addFilter(Filter currFilter) {
		filterList.add(currFilter);
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		for (Filter fl : filterList) {
			if (!fl.satisfies(qe))
				return false;
		}
		return true;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder("Filters used are: ");
		for (Filter f : filterList) {
			sb.append(f.getName());
			sb.append(", ");
		}
		sb.setCharAt(sb.length() - 2, '.');
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
