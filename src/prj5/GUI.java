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
        window.setSize(1280, 720);
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

        render();
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
    private void render() {
        int padLeft = (int)(width * 0.05);
        int padTop = 50;
        int songWidth = (int)(width * 0.1);
        int songTextHeight = 35;
        int barWidth = songWidth / 2;
        int curLeft = padLeft;
        int curTop = padTop;

        window.removeAllShapes();
        renderLegend();
        int[][][] data = backend.retrieveSwitch(getRType());
        String[] names = backend.getNames();
        String[] nameParts = { "", "" };
        int percentSize = 0;
        for (int i = 0; i < 9; i++) {
            nameParts = names[i].split(", ");
            curLeft = padLeft + (padLeft + songWidth) * (i % 3);
            curTop = padTop + (songTextHeight + padTop + textPad * 2) * (i / 3);
            TextShape songTitle = new TextShape(curLeft + songWidth / 2, curTop,
                nameParts[0]);
            songTitle.move(-songTitle.getWidth() / 2, 0);
            songTitle.setBackgroundColor(Color.WHITE);
            window.addShape(songTitle);
            TextShape artist = new TextShape(curLeft + songWidth / 2, curTop
                + textPad, nameParts[1]);
            artist.move(-artist.getWidth() / 2, 0);
            artist.setBackgroundColor(Color.WHITE);
            window.addShape(artist);

            percentSize = data[i][0][0];
            Shape heard1 = new Shape(curLeft + (barWidth - percentSize), curTop
                + songTextHeight, percentSize, textPad / 2, Color.red);
            window.addShape(heard1);
            percentSize = data[i][0][1];
            Shape heard2 = new Shape(curLeft + (barWidth - percentSize), curTop
                + songTextHeight + textPad / 2, percentSize, textPad / 2,
                Color.green);
            window.addShape(heard2);
            percentSize = data[i][0][2];
            Shape heard3 = new Shape(curLeft + (barWidth - percentSize), curTop
                + songTextHeight + textPad, percentSize, textPad / 2,
                Color.blue);
            window.addShape(heard3);
            percentSize = data[i][0][3];
            Shape heard4 = new Shape(curLeft + (barWidth - percentSize), curTop
                + songTextHeight + textPad + textPad / 2, percentSize, textPad
                    / 2, Color.orange);
            window.addShape(heard4);

            curLeft += padLeft;
            percentSize = data[i][1][0];
            Shape like1 = new Shape(curLeft, curTop + songTextHeight,
                percentSize, textPad / 2, Color.red);
            window.addShape(like1);
            percentSize = data[i][1][1];
            Shape like2 = new Shape(curLeft, curTop + songTextHeight + textPad
                / 2, percentSize, textPad / 2, Color.green);
            window.addShape(like2);
            percentSize = data[i][1][2];
            Shape like3 = new Shape(curLeft, curTop + songTextHeight + textPad,
                percentSize, textPad / 2, Color.blue);
            window.addShape(like3);
            percentSize = data[i][1][3];
            Shape like4 = new Shape(curLeft, curTop + songTextHeight + textPad
                + textPad / 2, percentSize, textPad / 2, Color.orange);
            window.addShape(like4);

            curLeft -= padLeft;
            Shape middle = new Shape(curLeft + songWidth / 2 - 2, curTop
                + songTextHeight, 4, textPad * 2, Color.BLACK);
            window.addShape(middle);
            window.moveToFront(middle);
        }
    }


    /**
     * returns the type of Enum for the integer notation indicated by
     * curRepresent
     * 
     * @return TypeEnum for current representation
     */
    private TypeEnum getRType() {
        switch (curRepresent) {
            case 0:
                return TypeEnum.HOBBY;
            case 1:
                return TypeEnum.MAJOR;
            case 2:
                return TypeEnum.REGION;
            default:
                return TypeEnum.OTHER;
        }
    }


    /**
     * gets the previous page
     * 
     * @param b
     *            the button clicked
     */
    public void prevPage(Button b) {
        backend.retrievePreviousPage();
        render();
    }


    /**
     * sorts by Artist
     * 
     * @param b
     *            the button clicked
     */
    public void sortArtist(Button b) {
        backend.sort(0);
        render();
    }


    /**
     * sorts by Title
     * 
     * @param b
     *            the button clicked
     */
    public void sortSong(Button b) {
        backend.sort(3);
        render();
    }


    /**
     * sorts by Year
     * 
     * @param b
     *            the button clicked
     */
    public void sortYear(Button b) {
        backend.sort(2);
        render();
    }


    /**
     * sorts by Genre
     * 
     * @param b
     *            the button clicked
     */
    public void sortGenre(Button b) {
        backend.sort(1);
        render();
    }


    /**
     * returns next page
     * 
     * @param b
     *            the button clicked
     */
    public void nextPage(Button b) {
        backend.retrieveNextPage();
        render();
    }


    /**
     * changes representation to by Hobby
     * 
     * @param b
     *            the button clicked
     */
    public void repHobby(Button b) {
        curRepresent = 0;
        render();
    }


    /**
     * changes representation to by Major
     * 
     * @param b
     *            the button clicked
     */
    public void repMajor(Button b) {
        curRepresent = 1;
        render();
    }


    /**
     * changes representation to by Region
     * 
     * @param b
     *            the button clicked
     */
    public void repRegion(Button b) {
        curRepresent = 2;
        render();
    }


    public void exit(Button b) {
        System.exit(0);
    }
}
