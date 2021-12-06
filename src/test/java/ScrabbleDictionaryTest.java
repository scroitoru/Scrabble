import croitoru.scrabble.ScrabbleDictionary;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ScrabbleDictionaryTest {
    @Test
    public void contains_true() throws IOException {
        // given
        ScrabbleDictionary dictionary = new ScrabbleDictionary();

        // when

        // then
        assertTrue(dictionary.wordPresent("pineapple"));
    }
    @Test
    public void contains_false() throws IOException {
        // given
        ScrabbleDictionary dictionary = new ScrabbleDictionary();

        // when

        // then
        assertFalse(dictionary.wordPresent("asdfasdfasff=d"));
    }

    @Test
    public void size() throws IOException {
        // given
        ScrabbleDictionary dictionary = new ScrabbleDictionary();

        // when

        // then
        assertEquals(167964, dictionary.size());
    }

    @Test
    public void getDefinition() throws IOException {
        // given
        ScrabbleDictionary dictionary = new ScrabbleDictionary();

        // when

        // then
        assertEquals("<pinecone=n> [n]", dictionary.getDefinition("pinecones"));
    }


}
