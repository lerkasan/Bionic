package hometasks.WordSearch;

import java.time.LocalDate;

import hometasks.Exceptions.*;

public class Article extends AuthoredText {
	private String journalName;
	private int publishYear;
	private String journalNumber;
	
	public Article() {
		super();
		this.journalName = "";
		this.publishYear = 0;
		this.journalNumber = "";
	}
	
	public Article(String title, String text, String[] authors, String journalName, int publishYear, String journalNumber) throws NullPointerException {
		super(title, text, authors);
		if (journalName != null) {
			this.journalName = journalName;
		} else {
			throw new NullArgumentException("Journal name can't be null.");
		}
		if ((publishYear <= LocalDate.now().getYear()) && (publishYear > 0)) {
			this.publishYear = publishYear;
		} else {
			throw new WrongArgumentException("Publish year can't be negative number or time in the future.");
		}
		if (journalNumber != null) {
			this.journalNumber = journalNumber;
		} else {
			throw new NullArgumentException("Journal number can't be null.");
		}
	}

	public String getJournalName() {
		return this.journalName;
	}

	public void setJournalName(String journalName) {
		if (journalName != null) {
			this.journalName = journalName;
		} else {
			throw new NullArgumentException("Journal name can't be null.");
		}
	}

	public int getPublishYear() {
		return this.publishYear;
	}

	public void setPublishYear(int publishYear) {
		if ((publishYear <= LocalDate.now().getYear()) && (publishYear > 0)) {
			this.publishYear = publishYear;
		} else {
			throw new WrongArgumentException("Publish year can't be negative number or time in the future.");
		}
	}

	public String getJournalNumber() {
		return this.journalNumber;
	}

	public void setJournalNumber(String journalNumber) {
		if (journalNumber != null) {
			this.journalNumber = journalNumber;
		} else {
			throw new NullArgumentException("Journal number can't be null.");
		}
	}

}
