/**
 * 
 */
package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Wyatt Muller (wtmuller22)
 * @version 2019.04.16
 * 
 * This class will act as as a reader for the two input files.
 * It will parse through the information and correctly implement 
 * it into songs and data.
 */
public class SongReader {

    private LinkedList<Song> library;
    
    /**
     * Constructs a new SongReader and reads the given files.
     * @param songFile the file of songs given.
     * @param surveyFile the file of data given.
     * @throws FileNotFoundException 
     */
    public SongReader(String songFile, String surveyFile) throws FileNotFoundException
    {
        library = new LinkedList<Song>();
        parseSongs(songFile);
        parseSurveys(surveyFile);
    }
    
    private void parseSongs(String fileName) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(fileName));
        file.nextLine(); //Skips the format line.
        while (file.hasNextLine())
        {
            String[] data = file.nextLine().split(",");
            Song song = new Song(data[1], data[3], Integer.parseInt(data[2]), data[0]);
            library.add(song);
        }
        file.close();
    }
    
    private void parseSurveys(String fileName) throws FileNotFoundException
    {
        Scanner file = new Scanner(new File(fileName));
        file.nextLine(); //Skips the formatting
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            Iterator<Song> iterator = library.iterator();
            Song currSong = null;
            lineScanner.next(); //Skips ID
            lineScanner.next(); //Skips Date
            String major = lineScanner.next();
            String region = lineScanner.next();
            String hobby = lineScanner.next();
            int hobbyIdx = getHobbyIdx(hobby);
            int majorIdx = getMajorIdx(major);
            int regionIdx = getRegionIdx(region);
            boolean skipPerson = majorIdx == -1 || regionIdx == -1 || hobbyIdx == -1;
            while (iterator.hasNext())
            {
                String heard = lineScanner.next();
                String likes = lineScanner.next();
                boolean heardYes = "Yes".equals(heard);
                boolean likeYes = "Yes".equals(likes);
                currSong = iterator.next();
                boolean skipSong = !("No".equals(heard) || heardYes) 
                                        || !("No".equals(likes) || likeYes);
                if (!(skipPerson || skipSong))
                {
                    currSong.add(Type.HOBBY, hobbyIdx, heardYes, 0);
                    currSong.add(Type.HOBBY, hobbyIdx, likeYes, 2);
                    currSong.add(Type.MAJOR, majorIdx, heardYes, 0);
                    currSong.add(Type.MAJOR, majorIdx, likeYes, 2);
                    currSong.add(Type.REGION, regionIdx, heardYes, 0);
                    currSong.add(Type.REGION, regionIdx, likeYes, 2);
                }
            }
            lineScanner.close();
        }
        file.close();
    }
    
    /**
     * Accessor method for library.
     * @return library field
     */
    public LinkedList<Song> getLibrary()
    {
        return library;
    }
    
    private int getHobbyIdx(String hobby)
    {
        switch (hobby)
        {
            case "reading":
                return 0;
            case "art":
                return 1;
            case "sports":
                return 2;
            case "music":
                return 3;
            default:
                return -1;
        }
    }
    
    private int getMajorIdx(String major)
    {
        switch (major)
        {
            case "Computer Science":
                return 0;
            case "Other Engineering":
                return 1;
            case "Math or CMDA":
                return 2;
            case "Other":
                return 3;
            default:
                return -1;
        }
    }
    
    private int getRegionIdx(String region)
    {
        switch (region)
        {
            case "Northeast":
                return 0;
            case "Southeast":
                return 1;
            case "United States (other than Southeast or Northwest)":
                return 2;
            case "Outside of United States":
                return 3;
            default:
                return -1;
        }
    }
    
}
