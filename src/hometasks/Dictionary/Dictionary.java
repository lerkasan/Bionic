package hometasks.Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import hometasks.Exceptions.*;

public class Dictionary {
	private HashMap<String, String> words;
	
	public Dictionary() {
		this.words = new HashMap<String, String>();
	}

	public HashMap<String, String> getWords() {
		return words;
	}

	public void setWords(HashMap<String, String> words) {
		if (words == null) {
			throw new NullArgumentException();
		}
		this.words = words;
	}
	
	public void putTranslation(String word, String translation) {
		if (word == null) {
			throw new NullArgumentException("Word can't be null.");
		}
		this.words.put(word, translation);
	}
	
	public String getTranslation(String word) {
		String translation = this.words.getOrDefault(word, "Нет перевода");
		if (translation == null) {
			return word;
		}
		return translation;
	}
	
	public void init() {
		this.putTranslation("Save", "Сохранить");
		this.putTranslation("Remove", "Удалить");
		this.putTranslation("Cancel", "Отменить");
		this.putTranslation("Create", "Создать");
		this.putTranslation("Ok", null);	
	}
	
	public void readFile(String filePath) {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(filePath))) { 
        	String line; 
            while ((line = inputStream.readLine()) != null) { 
            	String[] translation = line.split(" ");
            	switch (translation.length) {
            	case 2:	
            		putTranslation(translation[0], translation[1]);
            		break;
            	case 1:	
            		putTranslation(translation[0], null);
            		break;
            	default:
            		throw new WrongArgumentException("Bad file format");
            	}
            }	   
		} 
        catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.readFile("D:\\dict.txt");
		System.out.println(dict.getTranslation("Save"));
		System.out.println(dict.getTranslation("Ok"));
		System.out.println(dict.getTranslation("Rotate"));
	}

}
