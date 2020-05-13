
/**
 * CheckSolution is a utility class which can check if
 * a board position in an Aquarium puzzle is a solution.
 *
 * @author Aashish K.C.
 * @version 2020
 */
import java.util.Arrays; 

public class CheckSolution
{
    /**
     * Non-constructor for objects of class CheckSolution
     */
    private CheckSolution(){}
    
    /**
     * Returns the number of water squares in each row of Aquarium puzzle p, top down.
     */
    public static int[] rowCounts(Aquarium p)
    {
        Space[][] spaces = p.getSpaces();
        int waterCount = 0;
        int[] totalRowWaterCount = new int[spaces.length];

        for (int i = 0; i < spaces.length; i++){
            for (int j = 0; j < spaces[0].length; j++){
                if (spaces[i][j] == Space.WATER){
                    waterCount++;
                }
            }
            totalRowWaterCount[i] = waterCount;
            waterCount = 0;
        }
        // TODO 16
        return totalRowWaterCount;


    }
    
    /**
     * Returns the number of water squares in each column of Aquarium puzzle p, left to right.
     */
    public static int[] columnCounts(Aquarium p)
    {
        Space[][] spaces = p.getSpaces();
        int waterCount = 0;
        int[] totalColumnWaterCount = new int[spaces.length];

        for (int i = 0; i < spaces.length; i++){
            for (int j = 0; j < spaces[0].length; j++){
                if (spaces[j][i] == Space.WATER){
                    waterCount++;
                }
            }
            totalColumnWaterCount[i] = waterCount;
            waterCount = 0;
        }

        // TODO 17
        return totalColumnWaterCount;
    }
    
    /**
     * Returns a 2-int array denoting the collective status of the spaces 
     * in the aquarium numbered t on Row r of Aquarium puzzle p. 
     * The second element will be the column index c of any space r,c which is in t, or -1 if there is none. 
     * The first element will be: 
     * 0 if there are no spaces in t on Row r; 
     * 1 if they're all water; 
     * 2 if they're all not-water; or 
     * 3 if they're a mixture of water and not-water. 
     */
    public static int[] rowStatus(Aquarium p, int t, int r)
    {
        int status=0;
        int[] rowStatus = new int[]{0,-1};

        for (int i = 0; i < p.getSize(); i++){
            if(p.getAquariums()[r][i] == t) {
                rowStatus[1]=i;
                if (i > 0) {
                    if (p.getSpaces()[r][i] == Space.WATER && p.getSpaces()[r][i-1] == Space.WATER){
                        status = 1;
                    }
                    else {
                        if (p.getSpaces()[r][i] != Space.WATER && p.getSpaces()[r][i-1]!= Space.WATER){
                            status = 2;
                        }
                        else {
                            status = 3;
                        }
                    }
                }
                else {
                    if(p.getSpaces()[r][i] == Space.WATER){
                        status = 1;
                    }
                    else if (p.getSpaces()[r][i] != Space.WATER) {
                        status = 2;
                    }
                }
            }
            rowStatus[0] = status;
        }
        return rowStatus;
        // TODO 18
    }
    
    /**
     * Returns a statement on whether the aquarium numbered t in Aquarium puzzle p is OK. 
     * Every row must be either all water or all not-water, 
     * and all water must be below all not-water. 
     * Returns "" if the aquarium is ok; otherwise 
     * returns the indices of any square in the aquarium, in the format "r,c". 
     */
    public static String isAquariumOK(Aquarium p, int t)
    {
        // TODO 19
        return null;
    }
    
    /**
     * Returns a statement on whether we have a correct solution to Aquarium puzzle p. 
     * Every row and column must have the correct number of water squares, 
     * and all aquariums must be OK. 
     * Returns three ticks if the solution is correct; 
     * otherwise see the LMS page for the expected results. 
     */
    public static String isSolution(Aquarium p)
    {
        // TODO 20
        return null;
    }
}
