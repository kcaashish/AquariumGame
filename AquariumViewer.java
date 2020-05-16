
/**
 * AquariumViewer represents an interface for playing a game of Aquarium.
 *
 * @author Aashish K.C.
 * @version 2020
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class AquariumViewer implements MouseListener
{
    private final int BOXSIZE = 50;          // the size of each square
    private final int OFFSET  = BOXSIZE * 2; // the gap around the board
    private       int WINDOWSIZE;            // set this in the constructor 
    private       int gridSize;
    private       int marginSize;

    private Aquarium puzzle; // the internal representation of the puzzle
    private int        size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window

    private final static Color BACK_COLOUR = Color.decode("#FFFFF0");
    private final static Color GRID_COLOUR = Color.black;
    private final static Color BORDER_COLOUR = Color.black;
    private final static Color NOTIFICATION_COLOUR = Color.decode("#FF000D");
    private final static Color WATER_COLOUR = Color.cyan;
    private final static Color AIR_COLOUR = Color.decode("#0ADD08");
    private final static Color BUTTON_COLOUR = Color.decode("#6CB5BD");


    public static void main(String[] args) {
        new AquariumViewer(new Aquarium());
    }
    /**
     * Main constructor for objects of class AquariumViewer.
     * Sets all fields, and displays the initial puzzle.
     */
    public AquariumViewer(Aquarium puzzle)
    {
        this.puzzle = puzzle;
        size = puzzle.getSize();
        gridSize = size * BOXSIZE;
        WINDOWSIZE = size * BOXSIZE + 2 * OFFSET;
        marginSize = BOXSIZE * 5 / 100;

        sc = new SimpleCanvas("Aquarium Game", WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
        sc.addMouseListener(this);

        displayPuzzle();
        // TODO 8
    }
    
    /**
     * Selects from among the provided files in folder Examples. 
     * xyz selects axy_z.txt. 
     */
    public AquariumViewer(int n)
    {
        this(new Aquarium("Examples/a" + n / 10 + "_" + n % 10 + ".txt"));
    }
    
    /**
     * Uses the provided example file on the LMS page.
     */
    public AquariumViewer()
    {
        this(61);
    }

    /**
     * Sets the font to the size given my the integer passed to it.
     */
    private void fontSize(int x)
    {
        sc.setFont(new Font(sc.getFont().getFontName(), Font.BOLD, x));
    }

    /**
     * Returns the current state of the puzzle.
     */
    public Aquarium getPuzzle()
    {
        // TODO 7a
        return puzzle;
    }
    
    /**
     * Returns the size of the puzzle.
     */
    public int getSize()
    {
        // TODO 7b
        return size;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        // TODO 7c
        return sc;
    }
    
    /**
     * Displays the initial puzzle; see the LMS page for the format.
     */
    private void displayPuzzle()
    {
        // update square each time displayPuzzle is called
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                updateSquare(i, j);
            }
        }

        displayGrid();
        displayAquariums();

        fontSize(25);    // font size for the displayNumbers in row and column
        displayNumbers();

        fontSize(20);   // font size for the text in displayButtons
        displayButtons();

        fontSize(25);  // again, font size for the displayNumbers, after changing colour
        changeNumberColour();
        // TODO 13
    }
    
    /**
     * Displays the grid in the middle of the window.
     */
    public void displayGrid()
    {
        for (int i = 0; i <= size; i++) {
            int changingLinePosition = OFFSET + i * BOXSIZE;
            int lineEnd = OFFSET + size * BOXSIZE;

            //draw vertical lines
            sc.drawLine(changingLinePosition, OFFSET, changingLinePosition, lineEnd, GRID_COLOUR);

            //draw horizontal lines
            sc.drawLine(OFFSET, changingLinePosition, lineEnd, changingLinePosition, GRID_COLOUR);
        }

        // TODO 9
    }
    
    /**
     * Displays the numbers around the grid.
     */
    public void displayNumbers()
    {
        // display column numbers
        for (int i = 0; i < size; i++) {
            int changingNumberPosition = OFFSET + BOXSIZE / 2 + i * BOXSIZE - 10;
            sc.drawString(puzzle.getColumnTotals()[i], changingNumberPosition, OFFSET - 15, GRID_COLOUR);
        }


        // display row numbers
        for (int i = 0; i < size; i++) {
            int changingNumberPosition = OFFSET + BOXSIZE / 2 + i * BOXSIZE + 10;
            sc.drawString(puzzle.getRowTotals()[i], OFFSET - 30, changingNumberPosition, GRID_COLOUR);
        }
        // TODO 10
    }
    
    /**
     * Displays the aquariums.
     */
    public void displayAquariums()
    {
        int[][] aquariums = puzzle.getAquariums();

        // border top
        sc.drawRectangle(OFFSET - marginSize, OFFSET - marginSize, OFFSET + gridSize + marginSize, OFFSET + marginSize, BORDER_COLOUR);

        // border bottom
        sc.drawRectangle(OFFSET - marginSize, OFFSET + gridSize - marginSize, OFFSET + gridSize + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);

        // border left
        sc.drawRectangle(OFFSET - marginSize, OFFSET - marginSize, OFFSET + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);
        
        // border right
        sc.drawRectangle(OFFSET + gridSize - marginSize, OFFSET - marginSize, OFFSET + gridSize + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);

        // compares each adjacent element in row and column and draws border lines
        for (int i = 0; i < aquariums.length; i++){
            for (int j = 0; j < aquariums[i].length; j++) {

                // makes vertical border lines of aquarium
                if ((j + 1) < aquariums[i].length && !(aquariums[i][j] == aquariums[i][j+1])){
                    sc.drawRectangle(OFFSET + (j + 1) * BOXSIZE - marginSize, OFFSET + i * BOXSIZE - marginSize, OFFSET + (j + 1) * BOXSIZE + marginSize, OFFSET + (i + 1) * BOXSIZE + marginSize, BORDER_COLOUR);
                }

                // makes horizontal border lines of aquarium
                if ((j + 1) < aquariums[i].length && !(aquariums[j][i] == aquariums[j+1][i])){
                    sc.drawRectangle(OFFSET + i * BOXSIZE - marginSize, OFFSET + (j + 1) * BOXSIZE - marginSize, OFFSET + (i + 1) * BOXSIZE + marginSize, OFFSET + (j + 1) * BOXSIZE + marginSize, BORDER_COLOUR);
                }
            }
        }
        // TODO 11
    }
    
    /**
     * Displays the buttons below the grid.
     */
    public void displayButtons()
    {
        int solvedX1 = OFFSET - 10;
        int solvedX2 = OFFSET + 108;
        int solvedY1 = WINDOWSIZE - OFFSET + 15;
        int solvedY2 = WINDOWSIZE - OFFSET + 50;

        int clearX1 = WINDOWSIZE - 2 * OFFSET + 20;
        int clearX2 = WINDOWSIZE - OFFSET + 10;
        int clearY1 = WINDOWSIZE - OFFSET + 15;
        int clearY2 = WINDOWSIZE - OFFSET + 50;

        sc.drawRectangle(solvedX1, solvedY1, solvedX2, solvedY2, BUTTON_COLOUR);
        sc.drawString("SOLVED?", OFFSET, WINDOWSIZE - OFFSET + 40, GRID_COLOUR);

        sc.drawRectangle(clearX1, clearY1, clearX2, clearY2, BUTTON_COLOUR);
        sc.drawString("CLEAR", WINDOWSIZE - 2 * OFFSET + 30, WINDOWSIZE - OFFSET + 40, GRID_COLOUR);
        // TODO 12
    }
    
    /**
     * Updates the display of Square r,c. 
     * Sets the display of this square to whatever is in the squares array. 
     */
    public void updateSquare(int r, int c)
    {
        Space space = puzzle.getSpaces()[r][c];

        int top = OFFSET + r * BOXSIZE;
        int bot = OFFSET + (r + 1) * BOXSIZE;
        int left = OFFSET + c * BOXSIZE;
        int right = OFFSET + (c + 1) * BOXSIZE;

        if (space == Space.WATER) {
            sc.drawRectangle(left, top, right, bot, WATER_COLOUR);
        }
        else if (space == Space.AIR) {
            sc.drawRectangle(left, top, right, bot, Color.decode("#ECFFB8"));
            sc.drawCircle((left + right) / 2, (top + bot) / 2, 10, AIR_COLOUR);
        }
        else if (space == Space.EMPTY) {
            sc.drawRectangle(left, top, right, bot, BACK_COLOUR);
        }
        // TODO 14
    }

    // check if one of the boxes in the square puzzle is clicked
    private boolean boxClicked (int r, int c)
    {
        return (r > OFFSET) && r < WINDOWSIZE - OFFSET && (c > OFFSET) && c < WINDOWSIZE - OFFSET;
    }

    // check is the solved button is clicked
    private boolean solvedClicked(int r, int c)
    {
        int solvedX1 = OFFSET - 10;
        int solvedX2 = OFFSET + 105;
        int solvedY1 = WINDOWSIZE - OFFSET + 15;
        int solvedY2 = WINDOWSIZE - OFFSET + 50;

        return (solvedX1 < r && r < solvedX2 && solvedY1 < c && c < solvedY2);
    }

    // check if the clear button is clicked
    private boolean clearClicked (int r, int c)
    {
        int clearX1 = WINDOWSIZE - 2 * OFFSET + 20;
        int clearX2 = WINDOWSIZE - OFFSET + 10;
        int clearY1 = WINDOWSIZE - OFFSET + 15;
        int clearY2 = WINDOWSIZE - OFFSET + 50;

        return (clearX1 < r && r < clearX2 && clearY1 < c && c < clearY2);
    }

    /**
     * Changes the colour of the number in row or column if the number of water
     * in each row or column exceeds the required value.
     */
    public void changeNumberColour()
    {
        // rowCounts and columnCounts are objects of class CheckSolution
        int[] rowCounts = CheckSolution.rowCounts(puzzle);
        int[] rowTotal = puzzle.getRowTotals();
        int[] columnCounts = CheckSolution.columnCounts(puzzle);
        int[] columnTotal = puzzle.getColumnTotals();

        // check for row
        if (!Arrays.equals(rowCounts, rowTotal)) {
            for (int i = 0; i < rowTotal.length; i++) {
                if (rowCounts[i] > rowTotal[i]) {
                    changeRowNumberColour(i);
                }
            }
        }

        // check for column
        if (!Arrays.equals(columnCounts, columnTotal)){
            for (int i = 0; i < columnTotal.length; i++){
                if (columnCounts[i] > columnTotal[i]) {
                    changeColumnNumberColour(i);
                }
            }
        }
    }

    // changes the colour of row numbers
    public void changeRowNumberColour(int r)
    {
        int changingNumberPosition = OFFSET + BOXSIZE / 2 + r * BOXSIZE + 10;
        sc.drawString(puzzle.getRowTotals()[r], OFFSET - 30, changingNumberPosition, NOTIFICATION_COLOUR);
    }

    // changes the colour of column numbers
    public void changeColumnNumberColour(int c)
    {
        int changingNumberPosition = OFFSET + BOXSIZE / 2 + c * BOXSIZE - 10;
        sc.drawString(puzzle.getColumnTotals()[c], changingNumberPosition, OFFSET - 15, NOTIFICATION_COLOUR);
    }

    /**
     * Responds to a mouse click. 
     * If it's on the board, make the appropriate move and update the screen display. 
     * If it's on SOLVED?,   check the solution and display the result. 
     * If it's on CLEAR,     clear the puzzle and update the screen display. 
     */
    public void mousePressed(MouseEvent e) 
    {
        // if left clicked
        if (SwingUtilities.isLeftMouseButton(e)){

            // if one of the boxes is clicked do this
            // redraw the window & update puzzle
            if (boxClicked(e.getX(), e.getY())){
                int xBox = (e.getX() - OFFSET) / BOXSIZE;
                int yBox = (e.getY() - OFFSET) / BOXSIZE;
                puzzle.leftClick(yBox, xBox);
                sc.drawRectangle(0, 0, WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
                displayPuzzle();
            }

            // if Clear button is clicked do this
            // redraw the window & update puzzle
            else if (clearClicked(e.getX(), e.getY())){
                puzzle.clear();
                sc.drawRectangle(0, 0, WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
                displayPuzzle();
            }

            // if Solved? is clicked do this
            // redraw the window & update puzzle
            else if (solvedClicked(e.getX(), e.getY())){
                fontSize(15);
                String solution = CheckSolution.isSolution(puzzle);
                sc.drawRectangle(0, 0, WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
                sc.drawString(solution, OFFSET, WINDOWSIZE - 20, GRID_COLOUR);
                displayPuzzle();
            }
        }

        // if right clicked on the box, do this
        // redraw the window & update the puzzle
        else if (SwingUtilities.isRightMouseButton(e)) {
            if (boxClicked(e.getX(), e.getY())) {
                int xBox = (e.getX() - OFFSET) / BOXSIZE;
                int yBox = (e.getY() - OFFSET) / BOXSIZE;
                puzzle.rightClick(yBox, xBox);
                sc.drawRectangle(0, 0, WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
                displayPuzzle();
            }
        }
        // TODO 15
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
