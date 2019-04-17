package prj5;

import java.awt.Color;
import java.io.FileNotFoundException;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;

/**
 * @author Edward Tyles (dtedwa5)
 * @version 2019.04.16
 * 
 *          Instantiated and called by the Project Runner "Input".
 *          Updates everything on the front end / window accordingly.
 */
public class GUI {
    private Window window;
    private Backend backend;
    private final String[][] represent = { { "Read", "Art", "Sports", "Music" },
        { "Computer Science", "Other Engineering", "Math or CMDA", "Other" }, {
            "Northeast US", "Southeast US", "The Rest of US",
            "Outside the US" } };
    private int curRepresent;
    private TextShape[] texts;
    private int width;
    private int height;
    /**
     * padding
     */
    private final int textPad = 15;


    /**
     * Initializes the front end, creating the GUI and calling the backend to
     * create the data for later reference.
     * 
     * @param songF
     *            the song file
     * @param surveyF
     *            the survey file
     * @throws FileNotFoundException
     */
    public GUI(String songF, String surveyF) throws FileNotFoundException {
        backend = new Backend(songF, surveyF);
        curRepresent = 0;
        texts = new TextShape[4];
        window = new Window();
        window.setSize(1600, 900);
        window.setTitle("Project 5");
        width = window.getGraphPanelWidth();
        height = window.getGraphPanelHeight();
        Button tempB = new Button("<- Prev");
        tempB.onClick(this, "prevPage");
        window.addButton(tempB, WindowSide.NORTH);
        tempB = new Button("Sort by Artist Name");
        tempB.onClick(this, "sortArtist");
        window.addButton(tempB, WindowSide.NORTH);
        tempB = new Button("Sort by Song Title");
        tempB.onClick(this, "sortSong");
        window.addButton(tempB, WindowSide.NORTH);
        tempB = new Button("Sort by Release Year");
        tempB.onClick(this, "sortYear");
        window.addButton(tempB, WindowSide.NORTH);
        tempB = new Button("Sort by Genre");
        tempB.onClick(this, "sortGenre");
        window.addButton(tempB, WindowSide.NORTH);
        tempB = new Button("Next ->");
        tempB.onClick(this, "nextPage");
        window.addButton(tempB, WindowSide.NORTH);
        //
        tempB = new Button("Represent Hobby");
        tempB.onClick(this, "repHobby");
        window.addButton(tempB, WindowSide.SOUTH);
        tempB = new Button("Represent Major");
        tempB.onClick(this, "repMajor");
        window.addButton(tempB, WindowSide.SOUTH);
        tempB = new Button("Represent Region");
        tempB.onClick(this, "repRegion");
        window.addButton(tempB, WindowSide.SOUTH);
        tempB = new Button("Quit");
        tempB.onClick(this, "exit");
        window.addButton(tempB, WindowSide.SOUTH);

        renderLegend();
        renderSongs();
    }


    /**
     * renders the legend
     */
    private void renderLegend() {
        int x = (int)(width * 0.8);
        int y = (int)(height * 0.3);
        texts[0] = new TextShape(x, y, represent[curRepresent][0], Color.red);
        texts[1] = new TextShape(x, y + textPad, represent[curRepresent][1],
            Color.green);
        texts[2] = new TextShape(x, y + textPad * 2, represent[curRepresent][2],
            Color.blue);
        texts[3] = new TextShape(x, y + textPad * 3, represent[curRepresent][3],
            Color.orange);
        for (int i = 0; i < 4; i++) {
            texts[i].setBackgroundColor(Color.WHITE);
            window.addShape(texts[i]);
        }

        TextShape tempTxt = new TextShape(x, y - textPad, "Hobby Legend");
        tempTxt.setBackgroundColor(Color.WHITE);
        window.addShape(tempTxt);
        tempTxt = new TextShape(x + textPad, y + textPad * 4 + 2, "Song Title");
        tempTxt.setBackgroundColor(Color.WHITE);
        window.addShape(tempTxt);
        tempTxt = new TextShape(x + (int)(textPad / 2.0), y + textPad * 6,
            "Heard");
        tempTxt.setBackgroundColor(Color.WHITE);
        window.addShape(tempTxt);
        tempTxt = new TextShape(x + textPad * 3 + 10, y + textPad * 6, "Likes");
        tempTxt.setBackgroundColor(Color.WHITE);
        window.addShape(tempTxt);

        Shape rectangle = new Shape(x - 3, y - textPad - 3, (int)(width * 0.1),
            (int)(height * 0.3));
        rectangle.setBackgroundColor(Color.WHITE);
        rectangle.setForegroundColor(Color.BLACK);
        window.addShape(rectangle);
        window.moveToBack(rectangle);
        Shape bar = new Shape(x + textPad * 3 + 5, y + textPad * 6 - 5, 5, 30,
            Color.BLACK);
        window.addShape(bar);
        window.moveToFront(bar);
    }


    /**
     * renders the grid of songs
     */
    private void renderSongs() {
        int padLeft = (int)(width * 0.05);
        int padTop = 20;
        int songWidth = (int)(width * 0.1);
        int songTextHeight = 35;
        int barWidth = songWidth / 2;

        // example one
        TextShape songTitle = new TextShape(padLeft + songWidth / 2, padTop,
            "Song Ex Title");
        songTitle.move(-songTitle.getWidth() / 2, 0);
        songTitle.setBackgroundColor(Color.WHITE);
        window.addShape(songTitle);
        TextShape artist = new TextShape(padLeft + songWidth / 2, padTop
            + textPad, "by Ex Artist");
        artist.move(-artist.getWidth() / 2, 0);
        artist.setBackgroundColor(Color.WHITE);
        window.addShape(artist);

        int percentSize = 55;
        Shape heard1 = new Shape(padLeft + (barWidth - percentSize), padTop
            + songTextHeight, percentSize, textPad / 2, Color.red);
        window.addShape(heard1);
        percentSize = 65;
        Shape heard2 = new Shape(padLeft + (barWidth - percentSize), padTop
            + songTextHeight + textPad / 2, percentSize, textPad / 2,
            Color.green);
        window.addShape(heard2);
        percentSize = 35;
        Shape heard3 = new Shape(padLeft + (barWidth - percentSize), padTop
            + songTextHeight + textPad, percentSize, textPad / 2, Color.blue);
        window.addShape(heard3);
        percentSize = 15;
        Shape heard4 = new Shape(padLeft + (barWidth - percentSize), padTop
            + songTextHeight + textPad + textPad / 2, percentSize, textPad / 2,
            Color.orange);
        window.addShape(heard4);

        padLeft *= 2;
        percentSize = 35;
        Shape like1 = new Shape(padLeft, padTop + songTextHeight, percentSize,
            textPad / 2, Color.red);
        window.addShape(like1);
        percentSize = 25;
        Shape like2 = new Shape(padLeft, padTop + songTextHeight + textPad / 2,
            percentSize, textPad / 2, Color.green);
        window.addShape(like2);
        percentSize = 55;
        Shape like3 = new Shape(padLeft, padTop + songTextHeight + textPad,
            percentSize, textPad / 2, Color.blue);
        window.addShape(like3);
        percentSize = 75;
        Shape like4 = new Shape(padLeft, padTop + songTextHeight + textPad
            + textPad / 2, percentSize, textPad / 2, Color.orange);
        window.addShape(like4);

        padLeft /= 2;
        Shape middle = new Shape(padLeft + songWidth / 2 - 2, padTop
            + songTextHeight, 4, textPad * 2, Color.BLACK);
        window.addShape(middle);
        window.moveToFront(middle);
    }
}
