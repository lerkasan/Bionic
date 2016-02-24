package hometasks;

public class Word {
	private String word;
	
	public Word() {
		this.word = "";
	}
	
	public Word(String str) {
		this.word = new String(str);
	}
	
	public boolean isPolindrom() {
		String upperWord = new String(this.word.toLowerCase());
		StringBuilder reversed = new StringBuilder(upperWord).reverse();
		return upperWord.equals(reversed.toString());
	}

	public static void main(String[] args) {
		Word word1 = new Word("Agarana2434");
		System.out.println(word1.isPolindrom());
	}
}
