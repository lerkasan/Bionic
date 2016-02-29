package hometasks.WordSearch;

import hometasks.Exceptions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TitledText {
	private String title;
	private String text;
	
	public TitledText() {
		this.title = "";
		this.text = "";
	}
	
	public TitledText(String title, String text) throws NullArgumentException {
		if ( (title == null) || (text == null) ) {
			throw new NullArgumentException("Title and Text can't be null.");
		} else {
			this.title = title;
			this.text = text;
		}
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) throws NullArgumentException {
		if (title != null) {
			this.title = title;
		} else {
			throw new NullArgumentException("Title can't be null.");
		}
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) throws NullArgumentException {
		if (text != null) {
			this.text = text;
		} else {
			throw new NullArgumentException("Text can't be null.");
		}
	}
	
	public boolean containsSearchWordInTitle(String word) {
		if (word == null) {
			return false; 
		}
		return this.title.toLowerCase().contains(word.toLowerCase());
	}
		
	public boolean containsSearchWordInText(String word) {
		if (word == null) {
			return false; 
		}
		return this.text.toLowerCase().contains(word.toLowerCase());
	}
	
	public boolean containsSearchWord(String word) {
		if (word == null) {
			return false; 
		}
		return this.containsSearchWordInTitle(word) || this.containsSearchWordInText(word);
	}
	
	public boolean containsAllSearchWords(String[] words) {
		boolean result = true;
		if (words == null) {
			return false; 
		}
		for (String i : words) {
			result &= this.containsSearchWord(i);
		}
		return result;
	}
	
	public boolean containsAnySearchWords(String[] words) {
		boolean result = true;
		if (words == null) {
			return false; 
		}
		for (String i : words) {
			result |= this.containsSearchWord(i);
		}
		return result;
	}
	
	public static ArrayList<String> getTextWithAllSearchWords(TitledText[] texts, String[] words) {
		ArrayList<String> result = new ArrayList<>(); 
		if ( (texts == null) || (words == null) ) {
			return result;
		}
		for (TitledText aText : texts) {
			if (aText.containsAllSearchWords(words)) {
				String classType = aText.getClass().getSimpleName();
				result.add(classType+" \""+aText.getTitle()+"\"");
			}
		}
		result.trimToSize();
		return result;
	}
	
	public static int printTextWithAllSearchWords(TitledText[] texts, String[] words) {
		ArrayList<String> result = null; 
		if ( (texts != null) && (words != null) ) {
			result = TitledText.getTextWithAllSearchWords(texts, words);
			System.out.print("Total occurence of words ");
			for (String i : words) {
				System.out.print("\""+i+"\" ");;
			}
			System.out.println("found in "+result.size()+" objects:");
			for (String i : result) {
				System.out.println(i);
			}
		}
		if (result != null) {
			return result.size();
		}
		return 0;
	}
	
	public static String readTextFromFile(String filePath) {
		StringBuilder readText = new StringBuilder();
		try (Scanner in = new Scanner(Paths.get(filePath)) ) {
			while (in.hasNext()) {
				readText.append(in.nextLine().toLowerCase());
				readText.append(" ");
			}
		}
		catch (IOException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);	
		}
		return readText.toString();
	}
	
	public static void main(String[] args) {
		String aText1 = TitledText.readTextFromFile("texts\\text1.txt");
		String aText2 = TitledText.readTextFromFile("texts\\text2.txt");
		String aText3 = TitledText.readTextFromFile("texts\\wiki1.txt");
		String aText4 = TitledText.readTextFromFile("texts\\article1.txt");
		Book[] books = new Book[5];
		String[] chehov = {"А.П.Чехов"};
		String[] rempel = {"Е.А.Ремпель"};
		books[0] = new Book("Человек в футляре", aText1, chehov, "Лыбидь", "Это поучительная история о простом учителе гимназии Беликове.");
		books[1] = new Book("Детвора", aText2, chehov, "Лыбидь", "Пока взрослые в гостях, пятеро детей (в том числе сын кухарки) играют в лото на копейки, а старший — Вася — скучает на диване в гостиной.");
		
		WikiArticle[] wikiArticles = new WikiArticle[3];
		wikiArticles[0] = new WikiArticle("Чехов Антон Павлович", aText3, "https://ru.wikipedia.org/wiki/Чехов_Антон_Павлович");
		Article article1 = new Article("Медицинская тема в творчестве А.П. Чехова", aText4, rempel, "Бюллетень медицинских интернет-конференций", 2015, "Том 5, №1");
		
		TitledText[] texts = {books[0], books[1], wikiArticles[0], article1};
		String[] words1 = {"Чехов"};
		String[] words2 = {"человек", "говорит"};
		String[] words3 = {"дети"};
		String[] words4 = {"разговор"};
		TitledText.printTextWithAllSearchWords(texts, words1);
		System.out.println();
		TitledText.printTextWithAllSearchWords(texts, words2);
		System.out.println();
		TitledText.printTextWithAllSearchWords(texts, words3);
		System.out.println();
		TitledText.printTextWithAllSearchWords(texts, words4);
	
	}
}










