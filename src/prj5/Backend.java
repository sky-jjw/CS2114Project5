/**
 * 
 */
package prj5;

import java.io.FileNotFoundException;
import java.util.ListIterator;

/**
 * @author Wyatt Muller (wtmuller22)
 * @version 2019.04.16
 * 
 *          Instantiated and called by the GUI.
 *          Updates everything on the backend accordingly.
 */
public class Backend {

    private LinkedList<Song> library;
    private ListIterator<Song> libraryIter;
    private Song[] displayedSongs;


    /**
     * Constructs a new backend object.
     * 
     * @param songFile
     *            file of songs
     * @param surveyFile
     *            file of survey results
     * @throws FileNotFoundException
     */
    public Backend(String songFile, String surveyFile)
        throws FileNotFoundException {
        SongReader reader = new SongReader(songFile, surveyFile);
        library = reader.getLibrary();
        displayedSongs = new Song[9];
        sort(3);
    }


    /**
     * Returns the data associated with the TypeEnum.
     * Returns 0 in data for null displayed songs.
     * 
     * @param arrayType
     *            to be retrieved
     * @return a 3D array where:
     *         [song: 0-8][listened/liked: 0-1][subset: 0-3]
     */
    public int[][][] retrieveSwitch(TypeEnum arrayType) {
        int[][][] data = new int[9][2][4];
        for (int i = 0; (i < 9 && displayedSongs[i] != null); i++) {
            Song curr = displayedSongs[i];
            int[][] currData = curr.retrieve(arrayType);
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 4; k++) {
                    data[i][j][k] = (int)((currData[j * 2][k] * 100)
                        / currData[j * 2 + 1][k]);
                }
            }
        }
        return data;
    }


    /**
     * Switches to next page of songs.
     * 
     * @return the title and artist of each song
     */
    public String[] retrieveNextPage() {
        if (libraryIter.hasNext()) {
            for (int i = 0; i < 9; i++) {
                if (libraryIter.hasNext()) {
                    displayedSongs[i] = libraryIter.next();
                }
                else {
                    displayedSongs[i] = null;
                }
            }
        }
        return getNames();
    }


    /**
     * Switches to previous page of songs.
     * 
     * @return the title and artist of each song
     */
    public String[] retrievePreviousPage() {
        int numSongs = countSongs();
        for (int i = 0; i < numSongs; i++) {
            libraryIter.previous();
        }
        for (int i = 0; (i < 9 && libraryIter.hasPrevious()); i++) {
            libraryIter.previous();
        }
        return retrieveNextPage();
    }


    /**
     * @return the number of not-null songs being displayed
     */
    private int countSongs() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (displayedSongs[i] != null) {
                count++;
            }
        }
        return count;
    }


    /**
     * retrieves the title and artist of the
     * displayedSongs.
     * 
     * @return string array of songs.
     */
    public String[] getNames() {
        String[] names = new String[9];
        for (int i = 0; (i < 9 && displayedSongs[i] != null); i++) {
            names[i] = displayedSongs[i].getTitle() + ", by " + displayedSongs[i]
                .getArtist();
        }
        return names;
    }


    /**
     * Sorts the library according to the way desired.
     * 
     * @param opt
     *            represents the way to be sorted:
     *            0-artist, 1-genre, 2-year, 3-title
     */
    public void sort(int opt) {
        Sorter sorter = new Sorter(library);
        switch (opt) {
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
                throw new IllegalArgumentException(
                    "You did not enter a vaild argument.");
        }
        library = sorter.getLibrary();
        restart();
    }


    /**
     * restart reinitializes the iterator for use in the next/previous page
     * methods
     */
    private void restart() {
        libraryIter = library.iterator();
        retrieveNextPage();
    }


    /**
     * Accessor method for library.
     * 
     * @return library
     */
    public LinkedList<Song> getLibrary() {
        return library;
    }

}
