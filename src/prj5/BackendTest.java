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
     * testAll will test all of the backend methods
     */
    public void testAll() {
        b.retrieveSwitch(TypeEnum.HOBBY);
        b.sort(0);
        b.sort(1);
        b.sort(2);

    }

}
