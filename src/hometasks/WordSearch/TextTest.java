package hometasks.WordSearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextTest {

	@Test
	public void printTextWithAllSearchWordsTest() {
		Book[] books = new Book[5];
		String[] chehov = {"А.П.Чехов"};
		String[] rempel = {"Е.А.Ремпель"};
		books[0] = new Book("Книга1", "Улица машина фонарь автомобиль водитель", chehov, "Лыбидь", "Это поучительная история.");
		books[1] = new Book("Книга2", "Улица тротуар гараж светофор автомобиль", chehov, "Лыбидь", "Это поучительная история.");
		
		WikiArticle[] wikiArticles = new WikiArticle[3];
		wikiArticles[0] = new WikiArticle("Улица", "машина пешеход дерево", "https://ru.wikipedia.org/wiki/Улица");
		Article article1 = new Article("Статья о тротуаре", "вечер город автобус", rempel, "Журнал", 2015, "Том 5, №1");
		
		Text[] texts = {books[0], books[1], wikiArticles[0], article1};
		String[] words1 = {"улица"};
		String[] words2 = {"улица", "машина"};
		String[] words3 = {"тротуар"};
		String[] words4 = {"тротуар", "водитель"};
		assertEquals(3,Text.printTextWithAllSearchWords(texts, words1));
		System.out.println();
		assertEquals(2,Text.printTextWithAllSearchWords(texts, words2));
		System.out.println();
		assertEquals(2,Text.printTextWithAllSearchWords(texts, words3));
		System.out.println();
		assertEquals(0,Text.printTextWithAllSearchWords(texts, words4));
	}

}
