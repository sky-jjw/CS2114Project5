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
    
    /**
     * tests the RetrieveSwitch method, Major enum
     */
    public void testRetrieveSwitchMajor()
    {
        LinkedList<Song> library = b.getLibrary();
        int[][][] res = b.retrieveSwitch(TypeEnum.MAJOR);
        
        for (int i = 0; i < 9; i++)
        {
            Song song = library.get(i);
            int[][] data = song.retrieve(TypeEnum.MAJOR);
            for (int j = 0; j < 2; j++)
            {
                for (int k = 0; k < 4; k++)
                {
                    int result = (int)((data[j * 2][k] * 100)
                        / data[j * 2 + 1][k]);
                    assertEquals(res[i][j][k], result);
                }
            }
        }
    }
    
    /**
    * tests the RetrieveSwitch method, Region enum
    */
   public void testRetrieveSwitchRegion()
   {
       LinkedList<Song> library = b.getLibrary();
       int[][][] res = b.retrieveSwitch(TypeEnum.REGION);
       
       for (int i = 0; i < 9; i++)
       {
           Song song = library.get(i);
           int[][] data = song.retrieve(TypeEnum.REGION);
           for (int j = 0; j < 2; j++)
           {
               for (int k = 0; k < 4; k++)
               {
                   int result = (int)((data[j * 2][k] * 100)
                       / data[j * 2 + 1][k]);
                   assertEquals(res[i][j][k], result);
               }
           }
       }
   }
   
   /**
   * tests the RetrieveSwitch method, Hobby enum
   */
  public void testRetrieveSwitchHobby()
  {
      LinkedList<Song> library = b.getLibrary();
      int[][][] res = b.retrieveSwitch(TypeEnum.HOBBY);
      
      for (int i = 0; i < 9; i++)
      {
          Song song = library.get(i);
          int[][] data = song.retrieve(TypeEnum.HOBBY);
          for (int j = 0; j < 2; j++)
          {
              for (int k = 0; k < 4; k++)
              {
                  int result = (int)((data[j * 2][k] * 100)
                      / data[j * 2 + 1][k]);
                  assertEquals(res[i][j][k], result);
              }
          }
      }
  }
    
    /**
     * test the sort method
     */
    public void testSort0()
    {
        LinkedList<Song> library = b.getLibrary();
        b.sort(0);
        assertEquals(library.get(0).getArtist(), "ABBA");        
    }
    
    /**
     * test the sort method
     */
    public void testSort1()
    {
        LinkedList<Song> library = b.getLibrary();
        b.sort(1);
        assertEquals(library.get(0).getGenre(), "EDM");        
    }
    
    /**
     * test the sort method
     */
    public void testSort2()
    {
        LinkedList<Song> library = b.getLibrary();
        b.sort(2);
        assertEquals(library.get(0).getYear(), 1963);        
    }
    
    /**
     * test the sort method
     */
    public void testSort3()
    {
        LinkedList<Song> library = b.getLibrary();
        b.sort(3);
        assertEquals(library.get(0).getTitle(), "All These Things I've Done");        
    }
    
    /**
     * test the sort method
     */
    public void testSort4()
    {
        boolean res = false;
        
        try {
            b.sort(4);
        }
        catch (IllegalArgumentException e)
        {
            res = true;
            assertTrue(res);
        }
    }
    
    /**
     * Tests the retrievePreviousPage method.
     */
    public void testPreviousStart()
    {
        String[] names = b.retrievePreviousPage();
        String[] answers = {"All These Things I've Done, by The Killers",
                            "All You Need Is Love, by The Beatles", 
                            "American Pie, by Don McLean", 
                            "Anarchy in the UK, by The Sex Pistols", 
                            "Another One Bites the Dust, by Queen", 
                            "Bad Blood, by Taylor Swift", 
                            "Boom Boom Pow, by The Black Eyed Peas", 
                            "Bridge Over Troubled Water, by Simon and Garfunkel", 
                            "Call Me, by Blondie"};
        for (int i = 0; i < 9; i++)
        {
            assertEquals(names[i], answers[i]);
        }
    }
    
    /**
     * Tests the retrievePreviousPage method.
     */
    public void testPreviousBacktrack()
    {
        b.retrieveNextPage();
        String[] names = b.retrievePreviousPage();
        String[] answers = {"All These Things I've Done, by The Killers",
                            "All You Need Is Love, by The Beatles", 
                            "American Pie, by Don McLean", 
                            "Anarchy in the UK, by The Sex Pistols", 
                            "Another One Bites the Dust, by Queen", 
                            "Bad Blood, by Taylor Swift", 
                            "Boom Boom Pow, by The Black Eyed Peas", 
                            "Bridge Over Troubled Water, by Simon and Garfunkel", 
                            "Call Me, by Blondie"};
        for (int i = 0; i < 9; i++)
        {
            assertEquals(names[i], answers[i]);
        }
    }
    
    /**
     * Tests the retrieveNextPage method.
     */
    public void testNextEnd()
    {
        for (int i = 0; i < 5; i++)
        {
            b.retrieveNextPage();
        }
        String[] names = b.retrieveNextPage();
        String[] answers = {"Upside Down, by Diana Ross", 
                            "Watching the Detectives, by Elvis Costello", 
                            "We Belong Together, by Mariah Carey", 
                            "Yeah!, by Usher", 
                            "You Can't Always Get What You Want, by The Rolling Stones", 
                            null, null, null, null};
        for (int i = 0; i < 9; i++)
        {
            assertEquals(names[i], answers[i]);
        }
        names = b.retrieveNextPage();
        for (int i = 0; i < 9; i++)
        {
            assertEquals(names[i], answers[i]);
        }
    }
}
