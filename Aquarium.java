/**
 * Aquarium represents a single problem in the game Aquarium.
 *
 * @author Aashish K.C.
 * @version 2020
 */

import java.util.ArrayList;
import java.util.stream.Stream;

public class Aquarium
{
    private int   size;         // the board is size x size
    private int[] columnTotals; // the totals at the top of the columns, left to right
    private int[] rowTotals;    // the totals at the left of the rows, top to bottom 
    
    // the board divided into aquariums, numbered from 1,2,3,...
    // spaces with the same number are part of the same aquarium
    private int[][] aquariums;
    // the board divided into spaces, each empty, water, or air
    private Space[][] spaces;

    /**
     * Constructor for objects of class Aquarium. 
     * Creates, initialises, and populates all of the fields.
     */
    public Aquarium(String filename)
    {
        FileIO file = new FileIO(filename);
        ArrayList<String> lines = new ArrayList<>(file.getLines());

        size = lines.size() - 3;
        columnTotals = parseLine(lines.get(0));
        rowTotals = parseLine(lines.get(1));

        ArrayList<int[]> aquariumList = new ArrayList<>();
        for (int i = 3; i < lines.size(); i++){
            aquariumList.add(parseLine(lines.get(i)));
        }

        aquariums = new int[size][size];

        for (int i = 0; i < size; i++){
            int[] temp = aquariumList.get(i);
            System.arraycopy(temp, 0, aquariums[i], 0, size);
        }

        spaces = new Space[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                spaces[j][i] = Space.EMPTY;
            }
        }
        // TODO 3
    }
    
    /**
     * Uses the provided example file on the LMS page.
     */
    public Aquarium()
    {
        this("Examples/a6_1.txt");
    }

    /**
     * Returns an array containing the ints in s, 
     * each of which is separated by one space. 
     * e.g. if s = "1 299 34 5", it will return {1,299,34,5} 
     */
    public static int[] parseLine(String s)
    {
        String[] nums = s.split(" ");
        int[] lineArr = new int[nums.length];
        int i = 0;
        for (String x : nums){
            lineArr[i++] = Integer.parseInt(x);
        }
        // TODO 2
//        int[] lineArr = Stream.of(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        return lineArr;
    }
    
    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 1a
        return size;
    }
    
    /**
     * Returns the column totals.
     */
    public int[] getColumnTotals()
    {
        // TODO 1b
        return columnTotals;
    }
    
    /**
     * Returns the row totals.
     */
    public int[] getRowTotals()
    {
        // TODO 1c
        return rowTotals;
    }
    
    /**
     * Returns the board in aquariums.
     */
    public int[][] getAquariums()
    {
        // TODO 1d
        return aquariums;
    }
    
    /**
     * Returns the board in spaces.
     */
    public Space[][] getSpaces()
    {
        // TODO 1e
        return spaces;
    }
    
    /**
     * Performs a left click on Square r,c if the indices are legal, o/w does nothing. 
     * A water space becomes empty; other spaces become water. 
     */
    public void leftClick(int r, int c)
    {
        if ( (0 <= r && r < size) && (0 <= c && c < size)) {
            if (spaces[r][c] == Space.WATER) {
                spaces[r][c] = Space.EMPTY;
            } else if (spaces[r][c] == Space.AIR || spaces[r][c] == Space.EMPTY) {
                spaces[r][c] = Space.WATER;
            }
        }
        // TODO 4
    }
    
    /**
     * Performs a right click on Square r,c if the indices are legal, o/w does nothing. 
     * An air space becomes empty; other spaces become air. 
     */
    public void rightClick(int r, int c)
    {
        if ( (0 <= r && r < size) && (0 <= c && c < size)) {
            if (spaces[r][c] == Space.AIR) {
                spaces[r][c] = Space.EMPTY;
            } else if (spaces[r][c] == Space.WATER || spaces[r][c] == Space.EMPTY) {
                spaces[r][c] = Space.AIR;
            }
        }
        // TODO 5
    }

    /**
     * Empties all of the spaces.
     */
    public void clear()
    {
        spaces = new Space[size][size];

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                spaces[j][i] = Space.EMPTY;
            }
        }
        // TODO 6
    }
}
