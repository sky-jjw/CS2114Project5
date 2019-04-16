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
 * Song will contain the data for the artist, name, genre, and year of a song as
 * well as the survey results for the song
 *
 * @author James Mullen mullenj
 * @version 04/10/2019
 */
public class Song {

    private String artist;
    private String genre;
    private int year;
    private String title;
    private int[][] hobby;
    private int[][] major;
    private int[][] region;


    /**
     * The default constructor for song will initialize its properties and the
     * three type arrays
     * 
     * @param artist
     *            name of the artist
     * @param genre
     *            genre of the song
     * @param year
     *            year the song was made
     * @param title
     *            title of the song
     */
    public Song(String artist, String genre, int year, String title) {
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.title = title;
        hobby = new int[4][4];
        major = new int[4][4];
        region = new int[4][4];
    }


    /**
     * retrieve will return the 2D array of the type requested.
     * 
     * @param type
     *            data requested
     * @return int[][] of the type requested
     */
    public int[][] retrieve(TypeEnum type) {
        switch (type) {
            case HOBBY:
                return hobby;
            case MAJOR:
                return major;
            case REGION:
                return region;
            default:
                return null;
        }
    }


    /**
     * add will add to the 2D array of the type requested.
     * 
     * @param type
     *            data requested
     * @param subtype
     *            subtype of the data
     * @param yesNo
     *            whether the person liked/listened the song or not
     * @param listenedLiked
     *            if this data point is for listened or liked (0 or 2 to make
     *            reference in 2D array easy)
     */
    public void add(TypeEnum type, int subtype, boolean yes, int listenedLiked) {

        int[][] typeArray = this.retrieve(type);
        if (yes) {
            typeArray[listenedLiked][subtype]++;
        }
        typeArray[listenedLiked + 1][subtype]++;

    }


    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }


    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }


    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Overrides the toString method.
     * @return the string representation of this song
     */
    @Override
    public String toString()
    {
        /*
        StringBuilder builder = new StringBuilder(artist);
        builder.append(", ");
        builder.append(genre);
        builder.append(", ");
        builder.append(year);
        builder.append(", ");
        builder.append(title);
        return builder.toString();
        */
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                builder.append(hobby[j][i]);
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
