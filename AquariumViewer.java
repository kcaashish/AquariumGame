
/**
 * AquariumViewer represents an interface for playing a game of Aquarium.
 *
 * @author Aashish K.C.
 * @version 2020
 */
import java.awt.*;
import java.awt.event.*;
import java.time.OffsetDateTime;
import javax.swing.*;

public class AquariumViewer implements MouseListener
{
    private final int BOXSIZE = 50;          // the size of each square
    private final int OFFSET  = BOXSIZE * 2; // the gap around the board
    private       int WINDOWSIZE;            // set this in the constructor 
    private       int gridSize;

    private Aquarium puzzle; // the internal representation of the puzzle
    private int        size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window

    private final static Color BACK_COLOUR = Color.white;
    private final static Color GRID_COLOUR = Color.black;
    private final static Color BORDER_COLOUR = Color.red;



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
     * Increases the font size by a factor x.
     */
    private void fontSize(double x)
    {
        sc.setFont(sc.getFont()
                .deriveFont((float) (sc.getFont().getSize() * x)));
    }

    /**
     * Makes the font bold.
     */
    private void fontBold()
    {
        sc.setFont(sc.getFont().deriveFont(Font.BOLD));
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
        displayGrid();
        displayNumbers();
        displayAquariums();
        displayButtons();
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
        fontSize(2);
        fontBold();
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

        int marginSize = BOXSIZE * 5 / 100;

        // border top
        sc.drawRectangle(OFFSET - marginSize, OFFSET - marginSize, OFFSET + gridSize + marginSize, OFFSET + marginSize, BORDER_COLOUR);

        // border bottom
        sc.drawRectangle(OFFSET - marginSize, OFFSET + gridSize - marginSize, OFFSET + gridSize + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);

        // border left
        sc.drawRectangle(OFFSET - marginSize, OFFSET - marginSize, OFFSET + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);
        
        // border right
        sc.drawRectangle(OFFSET + gridSize - marginSize, OFFSET - marginSize, OFFSET + gridSize + marginSize, OFFSET + gridSize + marginSize, BORDER_COLOUR);

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                sc.drawString(aquariums[i][j], OFFSET + BOXSIZE * j + BOXSIZE / 2 - 10, OFFSET + BOXSIZE * i + BOXSIZE / 2 + 10, GRID_COLOUR);
//            }
//        }

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
        int solvedX2 = OFFSET + 105;
        int solvedY1 = WINDOWSIZE - OFFSET + 15;
        int solvedY2 = WINDOWSIZE - OFFSET + 50;

        int clearX1 = WINDOWSIZE - 2 * OFFSET + 20;
        int clearX2 = WINDOWSIZE - OFFSET + 10;
        int clearY1 = WINDOWSIZE - OFFSET + 15;
        int clearY2 = WINDOWSIZE - OFFSET + 50;

        fontSize(0.8);
        fontBold();
        sc.drawRectangle(solvedX1, solvedY1, solvedX2, solvedY2, Color.decode("#D5E7FF"));
        sc.drawString("SOLVED?", OFFSET, WINDOWSIZE - OFFSET + 40, GRID_COLOUR);

        sc.drawRectangle(clearX1, clearY1, clearX2, clearY2, Color.decode("#D5E7FF"));
        sc.drawString("CLEAR", WINDOWSIZE - 2 * OFFSET + 30, WINDOWSIZE - OFFSET + 40, GRID_COLOUR);
        // TODO 12
    }
    
    /**
     * Updates the display of Square r,c. 
     * Sets the display of this square to whatever is in the squares array. 
     */
    public void updateSquare(int r, int c)
    {
        // TODO 14
    }
    
    /**
     * Responds to a mouse click. 
     * If it's on the board, make the appropriate move and update the screen display. 
     * If it's on SOLVED?,   check the solution and display the result. 
     * If it's on CLEAR,     clear the puzzle and update the screen display. 
     */
    public void mousePressed(MouseEvent e) 
    {
        // TODO 15
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
