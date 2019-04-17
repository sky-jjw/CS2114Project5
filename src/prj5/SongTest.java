/**
 * 
 */
package prj5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author haria
 *
 */
public class SongTest {
    private Song sing;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        sing = new Song("Post Malone", "rap", 2016, "Congratulations");
    }


    /**
     * tests the getter methods for the song class
     */
    @Test
    public void testGet() {
        assertEquals(sing.getArtist(), "Post Malone");
        assertEquals(sing.getGenre(), "rap");
        assertEquals(sing.getTitle(), "Congratulations");
        assertEquals(sing.getYear(), 2016);
    }


    /**
     * tests the toString method
     */
    @Test
    public void testToString() {
        assertEquals(sing.toString(),
            "Song Title: Congratulations\nSong Artist: Post Malone\nSong Genre: rap\nSong Year: 2016");
    }

}
