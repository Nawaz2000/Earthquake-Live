package com.earthquake.filters;
import com.earthquake.pojos.QuakeEntry;

public class PhraseFilter implements Filter {

	private String whereToSearch;
	private String phraseToSearch;

	public PhraseFilter(String where, String phrase) {
		whereToSearch = where;
		phraseToSearch = phrase;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		if (whereToSearch.equals("start"))
			return qe.getInfo().startsWith(phraseToSearch);
		else if (whereToSearch.equals("end"))
			return qe.getInfo().endsWith(phraseToSearch);
		else if (whereToSearch.equals("any"))
			return qe.getInfo().contains(phraseToSearch);
		else
			return false;
	}

	@Override
	public String getName() {
		return "Phrase Filter";
	}
}
