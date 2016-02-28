package hometasks.WordSearch;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WikiArticle extends Text {
	private String link;

	public WikiArticle() {
		super();
		this.link = "";
	}

	public WikiArticle(String title, String text, String link) throws NullPointerException {
		super(title, text);
		if (link != null) {
			this.link = link;
		} else {
			NullPointerException  e = new NullPointerException("Link can't be null.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}	
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) throws NullPointerException {
		if (link != null) {
			this.link = link;
		} else {
			NullPointerException  e = new NullPointerException("Link can't be null.");
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);
			throw e;
		}	
	}

}
