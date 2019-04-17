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
 * Represents the main class which controls the program.
 */
public class Input {

    private static Backend back;
    
    /**
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 2)
        {
            back = new Backend(args[1], args[0]);
        }
        else
        {
            back = new Backend("SongList2018.csv", "MusicSurveyData2018.csv");
        }
        back.sort(1);
        int[][][] data = retrieveAllData(TypeEnum.HOBBY);
        outputData(data);
    }
    
    private static int[][][] retrieveAllData(TypeEnum arrayType)
    {
        int size = back.getLibrary().size();
        Iterator<Song> itr = back.getLibrary().iterator();
        int[][][] data = new int[size][2][4];
        int i = 0;
        while (itr.hasNext())
        {
            Song curr = itr.next();
            int[][] currData = curr.retrieve(arrayType);
            for (int j = 0; j < 2; j++)
            {
                for (int k = 0; k < 4; k++)
                {
                    data[i][j][k] = (int)((currData[j*2][k] * 100) / currData[j*2 + 1][k]);
                }
            }
            i++;
        }
        return data;
    }
    
    private static void outputData(int[][][] data)
    {
        int idx = 0;
        Iterator<Song> itr = back.getLibrary().iterator();
        while (itr.hasNext())
        {
            Song currSong = itr.next();
            System.out.println(currSong.toString());
            for (int i = 0; i < 2; i++)
            {
                if (i == 0)
                {
                    System.out.println("Heard");
                }
                else
                {
                    System.out.println("Likes");
                }
                StringBuilder builder = new StringBuilder();
                builder.append("reading:");
                builder.append(data[idx][i][0]);
                builder.append(" art:");
                builder.append(data[idx][i][1]);
                builder.append(" sports:");
                builder.append(data[idx][i][2]);
                builder.append(" music:");
                builder.append(data[idx][i][3]);
                System.out.println(builder.toString());
            }
            idx++;
            System.out.println("");
        }
    }

}
