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

/**
 * The sorter implements the sort functionality for the LinkedList allowing it
 * to be sorted by Name, Genre, Title, and Year
 *
 * @author James Mullen mullenj
 * @version 04/16/2019
 */
public class Sorter {

    private LinkedList<Song> library;


    /**
     * default constructor will take the library and set it as the field
     * 
     * @param library
     *            library of songs
     */
    public Sorter(LinkedList<Song> library) {
        this.library = library;
    }


    /**
     * getter method for the library
     * 
     * @return library of songs
     */
    public LinkedList<Song> getLibrary() {
        return library;
    }


    public void inssortTitle() {
        for (int i = 0; i < library.size(); i++) { // Insert i'th record
            for (int j = i; (j >= 0) && library.get(j).getTitle()
                .compareToIgnoreCase(library.get(j - 1).getTitle()) < 0; j--) {
                library.swap(j, j - 1);
            }
        }
    }


    public void inssortGenre() {
        for (int i = 0; i < library.size(); i++) { // Insert i'th record
            for (int j = i; (j >= 0) && library.get(j).getGenre()
                .compareToIgnoreCase(library.get(j - 1).getGenre()) < 0; j--) {
                library.swap(j, j - 1);
            }
        }
    }


    public void inssortArtist() {
        for (int i = 0; i < library.size(); i++) { // Insert i'th record
            for (int j = i; (j >= 0) && library.get(j).getArtist()
                .compareToIgnoreCase(library.get(j - 1).getArtist()) < 0; j--) {
                library.swap(j, j - 1);
            }
        }
    }


    public void inssortYear() {
        for (int i = 0; i < library.size(); i++) { // Insert i'th record
            for (int j = i; (j >= 0) && library.get(j).getYear() < (library.get(
                j - 1).getYear()); j--) {
                library.swap(j, j - 1);
            }
        }
    }
}
