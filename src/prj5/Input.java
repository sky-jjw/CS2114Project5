/**
 * 
 */
package prj5;

import java.io.FileNotFoundException;

/**
 * @author Edward Tyles (dtedwa5)
 * @version 2019.04.29
 * 
 *          Represents the main class which runs the program.
 */
public class Input {
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 2) {
            new GUI(args[1], args[0]);
        }
        else {
            new GUI("SongList.csv", "MusicSurveyData.csv");
        }
    }
}
