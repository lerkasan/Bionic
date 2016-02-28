package hometasks.TextProperties;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.nio.file.Paths;

public class TextProperties {
	private String filePath;
	private TreeMap<Character, Double> charFrequency;
	private TreeMap<String, Integer> wordOccurrence;

	public TextProperties() throws FileNotFoundException {
		throw new FileNotFoundException("File path is missed");
	}
	
	public TextProperties(String filePath){
		this.setFilePath(filePath);
		this.charFrequency = new TreeMap<>();
		this.wordOccurrence = new TreeMap<>();
	}
	
	public TextProperties(TextProperties textProp){
		TreeMap<Character, Double> charFrequency = new TreeMap<>(textProp.getCharFrequency());
		TreeMap<String, Integer> wordOccurrence = new TreeMap<>(textProp.getWordOccurrence());
		this.filePath = new String(textProp.getFilePath());
		this.charFrequency = charFrequency;
		this.wordOccurrence = wordOccurrence;
	}
	
	public TextProperties copy() {
		return new TextProperties(this);
	}	
	
	public static TextProperties copy(TextProperties textProp) {
		String filePath = new String(textProp.getFilePath());
		TreeMap<Character, Double> charFrequency = new TreeMap<>(textProp.getCharFrequency());
		TreeMap<String, Integer> wordOccurrence = new TreeMap<>(textProp.getWordOccurrence());
		TextProperties result = new TextProperties(filePath);
		result.charFrequency = charFrequency;
		result.wordOccurrence = wordOccurrence;
		return result;
	}
	
	public boolean isEqual(TextProperties textProp) {
		if (this == textProp) {  
        	return true;
        }
        if (textProp == null) {
        	return false;
        }
        if (this.getClass() != textProp.getClass()) {
        	return false;
        }
		boolean result = this.filePath.equals(textProp.getFilePath());
		result = result && this.charFrequency.equals(textProp.getCharFrequency());
		result = result && this.wordOccurrence.equals(textProp.getWordOccurrence());
		return result;
	}
	
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
		this.charFrequency = null;
		this.wordOccurrence = null;
	}
	
	public Map<String, Integer> getWordOccurrence() {
		if (!this.wordOccurrence.isEmpty()) {
			return this.wordOccurrence;
		} else {
			this.getCharFrequency();
			return this.wordOccurrence;
		}
	}

	public Map<Character, Double> getCharFrequency() {
		int allCharsAmount = 0;
		int wordAmount;
		char[] special = {'.', ',', '!', '?', ':', ';', '"', '(', ')', '—', ' '};
		double sum = 0.0;
		if (!this.charFrequency.isEmpty()) {
			return this.charFrequency;
		}
		try (Scanner in = new Scanner(Paths.get(filePath)) ) {
			while (in.hasNext()) {
				String str = in.next().toLowerCase();
				allCharsAmount+=str.length();
				String word = new String(str);
				for (char c : special) {
					if (word.endsWith(c+"")) {
						word = word.substring(0, word.length()-1);
					}
					if (word.startsWith(c+"")) {
						word = word.substring(1, word.length());
					}
				}
				wordAmount = this.wordOccurrence.getOrDefault(word, 0);
				this.wordOccurrence.put(word, wordAmount+1);
				

				char[] chars = str.toCharArray();
				for (char ch : chars) {
					double charAmount = this.charFrequency.getOrDefault(ch, 0.0);
					this.charFrequency.put(ch, charAmount+1);
				}
			}
		}
		catch (IOException e) {
			Logger logger = Logger.getAnonymousLogger();
			logger.log(Level.SEVERE, "The following exception was thrown:", e);	
		}
			System.out.println("Statistics for: "+filePath);
			System.out.println("Text size in characters: "+allCharsAmount);
			System.out.println("Amount of each word occurance:\n" +this.wordOccurrence.toString());
			System.out.println("Amount of each character occurance:\n" +this.charFrequency.toString());
			System.out.println("\nEach character frequency in %:");
			for (Map.Entry<Character, Double> entry : this.charFrequency.entrySet()) {
				double frequency = (1.0*entry.getValue())/allCharsAmount;
				int temp = (int)Math.round(frequency*100000);
				double roundedFrequency = 100*(double)temp/100000;
				entry.setValue(roundedFrequency);
				sum+=roundedFrequency;
			    System.out.println(entry.getKey() + "\t\t" + roundedFrequency);
			}
			int temp = (int)Math.round(sum*10);
			sum = (double)temp/10;
			System.out.println("Sum in % = "+sum);
			System.out.println();
		return charFrequency;
	}
	
	public static String[] getCommonWords(TextProperties text1, TextProperties text2) {
		String[] tempResult;
		String[] result;
		int occurrenceAmount = 0;
		Map<String, Integer> words1 = text1.getWordOccurrence();
		Map<String, Integer> words2 = text2.getWordOccurrence();
		if (words1.size() <= words2.size()) {
			tempResult = new String[words1.size()];
		} else {
			tempResult = new String[words2.size()];
		}
		for (Map.Entry<String, Integer> entry : words1.entrySet()) {
			if (words2.containsKey(entry.getKey())) {
				tempResult[occurrenceAmount] = entry.getKey();
				occurrenceAmount++;
			}
		}
		result = new String[occurrenceAmount];
		System.out.println("Amount of common words in two texts are: " + occurrenceAmount);
		System.out.println("Common words are:  Amount in Text1    Amount in Text2");
		for (int i = 0; i < occurrenceAmount; i++) {
			result[i] = tempResult[i];
			System.out.println((i+1)+"   "+result[i] + "\t\t\t" + words1.get(result[i]) + "\t\t\t" + words2.get(result[i]));
		}
		return result;
	}
	 
	public static void main(String[] args) {
		TextProperties text1 = new TextProperties("texts\\text1.txt");
		TextProperties text2 = new TextProperties("texts\\text2.txt");
		text1.getCharFrequency();
		text2.getCharFrequency();
		TextProperties.getCommonWords(text1, text2);
	}
}
