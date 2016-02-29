package hometasks.WordSearch;

import hometasks.Exceptions.*;

public class AuthoredText extends TitledText {
	private String[] authors;

	public AuthoredText() {
		super();
		this.authors = new String[1];
		this.authors[0] = "";
	}

	public AuthoredText(String title, String text, String[] authors) throws NullArgumentException {
		super(title, text);
		if (authors != null) {
			this.authors = new String[authors.length];
			System.arraycopy(authors, 0, this.authors, 0, authors.length);
		} else {
			throw new NullArgumentException("Authors list can't be null.");
		}
	}

	public String[] getAuthors() {
		return this.authors;
	}

	public void setAuthors(String[] authors) throws NullArgumentException {
		if (authors != null) {
			System.arraycopy(authors, 0, this.authors, 0, authors.length);
		} else {
			throw new NullArgumentException("Authors list can't be null.");
		}
	}

}
