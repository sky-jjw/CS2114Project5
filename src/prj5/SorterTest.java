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


    public void setUp() {
        list = new LinkedList<Song>();
        Song song1 = new Song("James", "Rock", 2019, "Song1");
        Song song2 = new Song("Jason", "Hip-Hop", 2011, "Song2");
        Song song3 = new Song("John", "R&B", 2000, "Song3");
        list.add(song1);
        list.add(song2);
        list.add(song3);
    }

}
