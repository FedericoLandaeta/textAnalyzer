package ravenReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ReaderTest {

	@Test
	void readerTest() {
		String words = null;
		Reader reader = new Reader();
		words = reader.raven();
		assertNotNull (words);
	}
	
	void buttonTest() {
		
	}
}
