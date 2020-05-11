
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
    
    private Aquarium puzzle; // the internal representation of the puzzle
    private int        size; // the puzzle is size x size
    private SimpleCanvas sc; // the display window

    private final static Color BACK_COLOUR = Color.white;
    private final static Color GRID_COLOUR = Color.black;



    /**
     * Main constructor for objects of class AquariumViewer.
     * Sets all fields, and displays the initial puzzle.
     */
    public AquariumViewer(Aquarium puzzle)
    {
        this.puzzle = puzzle;
        size = puzzle.getSize();

        WINDOWSIZE = size * BOXSIZE + 2 * OFFSET;

        sc = new SimpleCanvas("Aquarium Game", WINDOWSIZE, WINDOWSIZE, BACK_COLOUR);
        sc.addMouseListener(this);
        fontSize(2);

        displayGrid();
        displayNumbers();
        displayAquariums();
        displayButtons();
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
        // TODO 10
    }
    
    /**
     * Displays the aquariums.
     */
    public void displayAquariums()
    {
        // TODO 11
    }
    
    /**
     * Displays the buttons below the grid.
     */
    public void displayButtons()
    {
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
