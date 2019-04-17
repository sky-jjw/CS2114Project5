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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
    private Scanner fileData;


    /**
     * sets up any needed variables for test methods
     */
    public void setUp() {

        fileData = null;
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

        Input.main(new String[] { "MusicSurveyDataTest2.csv",
            "SongListTest2.csv" });

        String fileName = "Output2TitleGenreHobby.txt";

        String output2TitleGenreHobby = "";
        fileData = null;
        try {
            // Can throw FileNotFoundException
            fileData = new Scanner(new File(fileName));
        }
        catch (FileNotFoundException e) {
            System.out.println("Scanner error opening the file " + fileName);
            System.out.println(e.getMessage());

        }

        while (fileData.hasNextLine()) {
            output2TitleGenreHobby += fileData.nextLine() + "\n";
        }

        assertFuzzyEquals("Output not as expected for input files "
            + "MusicSurveyDataTest2.csv and SongListTest2.csv",
            output2TitleGenreHobby, systemOut().getHistory());
    }

}
