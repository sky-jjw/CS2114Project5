/**
 * 
 */
package prj5;

import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * @author Wyatt Muller (wtmuller22)
 * @version 2019.04.16
 * 
 * Instantiated and called by the GUI.
 * Updates everything on the backend accordingly.
 */
public class Backend {

    private LinkedList<Song> library;
    private Iterator<Song> libraryIter;
    private Song[] displayedSongs;
    
    /**
     * Constructs a new backend object.
     * @param songFile
     * @param surveyFile
     * @throws FileNotFoundException
     */
    public Backend(String songFile, String surveyFile) throws FileNotFoundException
    {
        SongReader reader = new SongReader(songFile, surveyFile);
        library = reader.getLibrary();
        sort(3);
        displayedSongs = new Song[9];
        restart();
    }
    
    /**
     * Returns the data associated with the TypeEnum.
     * @param arrayType to be retrieved
     * @return a 3D array where:
     * [song: 0-8][listened/liked: 0-1][subset: 0-3]
     */
    public int[][][] retrieveSwitch(TypeEnum arrayType)
    {
        int[][][] data = new int[9][2][4];
        for (int i = 0; i < 9; i++)
        {
            Song curr = displayedSongs[i];
            int[][] currData = curr.retrieve(arrayType);
            for (int j = 0; j < 2; j++)
            {
                for (int k = 0; k < 4; k++)
                {
                    data[i][j][k] = (int)((currData[j*2][k] * 100) / currData[j*2 + 1][k]);
                }
            }
        }
        return data;
    }
    /*
    public String[] retrieveNextPage()
    {
        if (libraryIter.hasNext())
        {
            for (int i = 0; i < 9; i++)
            {
                if (libraryIter.hasNext())
                {
                    displayedSongs[i] = libraryIter.next();
                }
                else
                {
                    displayedSongs[i] = null;
                }
            }
        }
    }
    */
    /*
    private String[] getNames()
    {
        String[] names = new String[9];
        for (int i = 0; i < display)
    }
    */
    
    /**
     * Sorts the library according to the way desired.
     * @param opt represents the way to be sorted:
     * 0-artist, 1-genre, 2-year, 3-title
     */
    public void sort(int opt)
    {
        Sorter sorter = new Sorter(library);
        switch (opt)
        {
            case 0:
                sorter.inssortArtist();
                break;
            case 1:
                sorter.inssortGenre();
                break;
            case 2:
                sorter.inssortYear();
                break;
            case 3:
                sorter.inssortTitle();
                break;
            default:
                throw new IllegalArgumentException("You did not enter a vaild argument.");
        }
        library = sorter.getLibrary();
        restart();
    }
    
    private void restart()
    {
        libraryIter = library.iterator();
        for (int i = 0; i < 9; i++)
        {
            if (libraryIter.hasNext())
            {
                displayedSongs[i] = libraryIter.next();
            }
        }
    }
    
    /**
     * Accessor method for library.
     * @return library
     */
    public LinkedList<Song> getLibrary()
    {
        return library;
    }
    
    
}
