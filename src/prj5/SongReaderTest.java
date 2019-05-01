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

import java.io.FileNotFoundException;
import student.TestCase;
import student.testingsupport.annotations.Hint;

/**
 * Tests for the songReader adapted from inputReferenceTest3
 *
 * @author James Mullen mullenj
 * @version 04/17/2019
 * @author margaretellis
 * @version 11-12-2015
 */
public class SongReaderTest extends TestCase {

    /**
     * sets up any needed variables for test methods
     */
    public void setUp() {
        // intentionally left blank
    }


    /**
     * Test the program with the class survey data from 6 students about only
     * 1 song.
     * Gathers the output from StdOut and compares it to
     * the expect output as stored in Output2TitleGenreHobby.txt
     * 
     * @throws FileNotFoundException
     * 
     */
    @Hint("main working properly with MusicSurveyDataTest2, SongListTest2")
    public void testMain2() throws FileNotFoundException {
        Song testS = new Song("t", "t", 1000, "t");
        Input.main(new String[] { "MusicSurveyDataTest2.csv",
            "SongListTest2.csv" });
        assertNull(testS.retrieve(TypeEnum.OTHER));
    }

}
