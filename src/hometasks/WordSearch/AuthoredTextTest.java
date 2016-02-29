package hometasks.WordSearch;

import static org.junit.Assert.*;

import org.junit.Test;

public class AuthoredTextTest {

	@Test
	public void contructorSetCopiedAuthorsTest() {
		String[] authors = {"London", "Sheakspeare", "Gogol"};
		AuthoredText text1 = new AuthoredText("title", "text", authors);
		authors[1] = "Dikens";
		assertEquals("Sheakspeare",text1.getAuthors()[1]);
	}

}
