/**
 * 
 */
package prj5;

import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author haria
 * @version 4.17.2019
 *
 */
public class BackendTest extends TestCase {

    private Backend b;


    /**
     * setUp will set up a backend for us in tests
     */
    public void setUp() {
        try {
            b = new Backend("SongList.csv", "MusicSurveyData.csv");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Tests the default order of the library.
     */
    public void testDefaultOrder() {
        LinkedList<Song> library = b.getLibrary();
        assertEquals(library.get(0).getArtist(), "The Killers");

    }

}
