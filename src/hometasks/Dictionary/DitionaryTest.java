package hometasks.Dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

public class DitionaryTest {

	@Test
	public void getTranslationTest() {
		Dictionary dict = new Dictionary();
		dict.init();
		assertEquals("Сохранить", dict.getTranslation("Save"));
		assertEquals("Нет перевода",dict.getTranslation("Ok"));
		assertEquals("Нет перевода",dict.getTranslation("Rotate"));
	}

}
