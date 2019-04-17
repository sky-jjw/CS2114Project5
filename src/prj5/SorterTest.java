/*
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- James Mullen (mullenj)
 */
package prj5;

import student.TestCase;

/**
 * SorterTest will test the sort algorithms
 *
 * @author James Mullen mullenj
 * @version 04/16/2019
 */
public class SorterTest extends TestCase {

    private LinkedList<Song> list;
    private Sorter sorter;


    /**
     * setUp method will set up the sorter test with a sorter and song linked
     * list
     */
    public void setUp() {
        list = new LinkedList<Song>();
        Song song1 = new Song("James", "Rock", 2019, "Three");
        Song song2 = new Song("Jason", "Hip-Hop", 2000, "one");
        Song song3 = new Song("Hayley", "R&B", 2011, "two");
        list.add(song1);
        list.add(song2);
        list.add(song3);
        sorter = new Sorter(list);
    }


    /**
     * testTitleSort will make sure the correct order is outputted
     */
    public void testTitleSort() {
        sorter.inssortTitle();
        list = sorter.getLibrary();
        assertEquals(list.get(0).getTitle(), "one");
        assertEquals(list.get(1).getTitle(), "three");
        assertEquals(list.get(2).getTitle(), "two");
    }
    
    /**
     * testTitleSort will make sure the correct order is outputted
     */
    public void testGenreSort() {
        sorter.inssortGenre();
        list = sorter.getLibrary();
        assertEquals(list.get(0).getGenre(), "Hip-Hop");
        assertEquals(list.get(1).getGenre(), "R&B");
        assertEquals(list.get(2).getGenre(), "Rock");
    }
    
    /**
     * testTitleSort will make sure the correct order is outputted
     */
    public void testYearSort() {
        sorter.inssortYear();
        list = sorter.getLibrary();
        assertEquals(list.get(0).getYear(), 2000);
        assertEquals(list.get(1).getYear(), 2011);
        assertEquals(list.get(2).getYear(), 2019);
    }
    
    /**
     * testTitleSort will make sure the correct order is outputted
     */
    public void testArtistSort() {
        sorter.inssortArtist();
        list = sorter.getLibrary();
        assertEquals(list.get(0).getArtist(), "Hayley");
        assertEquals(list.get(1).getArtist(), "James");
        assertEquals(list.get(2).getArtist(), "Jason");
    }
}
