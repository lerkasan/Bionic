package hometasks.WordSearch;

import hometasks.Exceptions.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Text {
	private String title;
	private String text;
	
	public Text() {
		this.title = "";
		this.text = "";
	}
	
	public Text(String title, String text) throws NullArgumentException {
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
	
	public static ArrayList<String> getTextWithAllSearchWords(Text[] texts, String[] words) {
		ArrayList<String> result = new ArrayList<>(); 
		if ( (texts == null) || (words == null) ) {
			return null;
		}
		for (Text aText : texts) {
			if (aText.containsAllSearchWords(words)) {
				String classType = aText.getClass().getSimpleName();
				result.add(classType+" \""+aText.getTitle()+"\"");
			}
		}
		result.trimToSize();
		return result;
	}
	
	public static int printTextWithAllSearchWords(Text[] texts, String[] words) {
		ArrayList<String> result = null; 
		if ( (texts != null) && (words != null) ) {
			result = Text.getTextWithAllSearchWords(texts, words);
			System.out.print("Total occurence of words ");
			for (String i : words) {
				System.out.print("\""+i+"\" ");;
			}
			System.out.println("found in "+result.size()+" objects:");
			for (String i : result) {
				System.out.println(i);
			}
		}
		return result.size();
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
		String aText1 = Text.readTextFromFile("texts\\text1.txt");
		String aText2 = Text.readTextFromFile("texts\\text2.txt");
		String aText3 = Text.readTextFromFile("texts\\wiki1.txt");
		String aText4 = Text.readTextFromFile("texts\\article1.txt");
		Book[] books = new Book[5];
		String[] chehov = {"�.�.�����"};
		String[] rempel = {"�.�.�������"};
		books[0] = new Book("������� � �������", aText1, chehov, "������", "��� ������������ ������� � ������� ������� �������� ��������.");
		books[1] = new Book("�������", aText2, chehov, "������", "���� �������� � ������, ������ ����� (� ��� ����� ��� �������) ������ � ���� �� �������, � ������� � ���� � ������� �� ������ � ��������.");
		
		WikiArticle[] wikiArticles = new WikiArticle[3];
		wikiArticles[0] = new WikiArticle("����� ����� ��������", aText3, "https://ru.wikipedia.org/wiki/�����_�����_��������");
		Article article1 = new Article("����������� ���� � ���������� �.�. ������", aText4, rempel, "��������� ����������� ��������-�����������", 2015, "��� 5, �1");
		
		Text[] texts = {books[0], books[1], wikiArticles[0], article1};
		String[] words1 = {"�����"};
		String[] words2 = {"�������", "�������"};
		String[] words3 = {"����"};
		String[] words4 = {"��������"};
		Text.printTextWithAllSearchWords(texts, words1);
		System.out.println();
		Text.printTextWithAllSearchWords(texts, words2);
		System.out.println();
		Text.printTextWithAllSearchWords(texts, words3);
		System.out.println();
		Text.printTextWithAllSearchWords(texts, words4);
	
	}
}










