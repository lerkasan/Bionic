package hometasks.Dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

public class DitionaryTest {

	@Test
	public void getTranslationTest() {
		Dictionary dict = new Dictionary();
		dict.init();
		assertEquals("���������", dict.getTranslation("Save"));
		assertEquals("��� ��������",dict.getTranslation("Ok"));
		assertEquals("��� ��������",dict.getTranslation("Rotate"));
	}

}
