package hometasks.WordSearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class TextTest {

	@Test
	public void printTextWithAllSearchWordsTest() {
		Book[] books = new Book[5];
		String[] chehov = {"�.�.�����"};
		String[] rempel = {"�.�.�������"};
		books[0] = new Book("�����1", "����� ������ ������ ���������� ��������", chehov, "������", "��� ������������ �������.");
		books[1] = new Book("�����2", "����� ������� ����� �������� ����������", chehov, "������", "��� ������������ �������.");
		
		WikiArticle[] wikiArticles = new WikiArticle[3];
		wikiArticles[0] = new WikiArticle("�����", "������ ������� ������", "https://ru.wikipedia.org/wiki/�����");
		Article article1 = new Article("������ � ��������", "����� ����� �������", rempel, "������", 2015, "��� 5, �1");
		
		Text[] texts = {books[0], books[1], wikiArticles[0], article1};
		String[] words1 = {"�����"};
		String[] words2 = {"�����", "������"};
		String[] words3 = {"�������"};
		String[] words4 = {"�������", "��������"};
		assertEquals(3,Text.printTextWithAllSearchWords(texts, words1));
		System.out.println();
		assertEquals(2,Text.printTextWithAllSearchWords(texts, words2));
		System.out.println();
		assertEquals(2,Text.printTextWithAllSearchWords(texts, words3));
		System.out.println();
		assertEquals(0,Text.printTextWithAllSearchWords(texts, words4));
	}

}
