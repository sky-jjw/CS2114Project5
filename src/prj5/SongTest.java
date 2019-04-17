/**
 * 
 */
package prj5;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author harianisha
 * @version 4/16/2019
 *
 */
public class SongTest {
    private Song sing;
    private int[][] hobby;
    private int[][] major;
    private int[][] region;


    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        sing = new Song("Post Malone", "rap", 2016, "Congratulations");
        hobby = new int[4][4];
        major = new int[4][4];
        region = new int[4][4];
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
            "Song Title: Congratulations\nSong Artist: "
                + "Post Malone\nSong Genre: rap\nSong Year: 2016");
    }


    /**
     * tests retrieve method
     * the hobby statement
     */
    @Test
    public void testRetriveHobby() {
        int[][] res = sing.retrieve(TypeEnum.HOBBY);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                assertEquals(hobby[i][j], res[i][j]);
            }
        }
    }


    /**
     * tests retrieve method
     * the major statement
     */
    @Test
    public void testRetriveMajor() {
        int[][] res = sing.retrieve(TypeEnum.MAJOR);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                assertEquals(major[i][j], res[i][j]);
            }
        }
        assertNull(sing.retrieve(TypeEnum.OTHER));
    }


    /**
     * tests retrieve method
     * the region method
     */
    @Test
    public void testRetriveRegion() {
        int[][] res = sing.retrieve(TypeEnum.REGION);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                assertEquals(region[i][j], res[i][j]);
            }
        }
    }


    /**
     * tests the add method for liked
     */
    @Test
    public void testAdd() {
        int[][] res = sing.retrieve(TypeEnum.REGION);
        assertEquals(0, res[2][2]);
        assertEquals(0, res[3][2]);
        sing.add(TypeEnum.REGION, 2, true, 2);
        res = sing.retrieve(TypeEnum.REGION);
        assertEquals(1, res[2][2]);
        assertEquals(1, res[3][2]);
    }


    /**
     * tests add method of disliked
     */
    @Test
    public void testAddFalse() {
        sing.add(TypeEnum.REGION, 2, false, 2);
        int[][] res = sing.retrieve(TypeEnum.REGION);
        assertEquals(0, res[2][2]);
        assertEquals(1, res[3][2]);
    }
}
