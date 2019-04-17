/**
 * 
 */
package prj5;
import java.io.FileNotFoundException;
import student.TestCase;

/**
 * @author haria
 *
 */
public class BackendTest {
    
    private Backend b;
    
    public void setUp() {
        try {
            b = new Backend("SongList.csv","MusicSuveyData.csv");
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void testAll() {
        b.retrieveSwitch(TypeEnum.HOBBY);
        b.sort(0);
        b.sort(1);
        b.sort(2);
        
    }

}
