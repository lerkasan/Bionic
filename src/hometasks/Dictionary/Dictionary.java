package hometasks.Dictionary;

import java.util.HashMap;

import hometasks.Exceptions.NullArgumentException;

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
			return "Нет перевода";
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
	
	public static void main(String[] args) {
		Dictionary dict = new Dictionary();
		dict.init();
		System.out.println(dict.getTranslation("Save"));
		System.out.println(dict.getTranslation("Ok"));
		System.out.println(dict.getTranslation("Rotate"));
	}

}
