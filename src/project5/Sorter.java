/*
 * Virginia Tech Honor Code Pledge:
 *
 * As a Hokie, I will conduct myself with honor
 * and integrity at all times.
 * I will not lie, cheat, or steal, nor will I
 * accept the actions of those who do.
 * -- James Mullen (mullenj)
 */
package project5;

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
        for (int i=1; i<library.size(); i++) { // Insert i'th record
          for (int j=i; (j>0) && library.get(j).getTitle().compareToIgnoreCase(library.get(j-1).getTitle()) < 0; j--) {
            library.swap(j, j-1);
          }
        }
            
      }(A[j].compareTo(A[j-1])
// private class TitleComparator implements Comparator<Song> {
// @Override
// public int compare(Song a, Song b) {
// return ;
// }
// }
//
//
// private class GenreComparator implements Comparator<Song> {
// @Override
// public int compare(Song a, Song b) {
// return a.getGenre().compareToIgnoreCase(b.getGenre());
// }
// }
//
//
// private class ArtistComparator implements Comparator<Song> {
// @Override
// public int compare(Song a, Song b) {
// return a.getArtist().compareToIgnoreCase(b.getArtist());
// }
// }
//
//
// private class YearComparator implements Comparator<Song> {
// @Override
// public int compare(Song a, Song b) {
// return a.getYear() < b.getYear()
// ? -1
// : a.getYear() == b.getYear() ? 0 : 1;
// }
// }

}
