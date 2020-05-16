
/**
 * CheckSolution is a utility class which can check if
 * a board position in an Aquarium puzzle is a solution.
 *
 * @author Aashish K.C.
 * @version 2020
 */
import java.util.ArrayList;
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

        // matches with row numbers, water in each row from left to right gets counted
        for (int i = 0; i < spaces.length; i++){
            for (int j = 0; j < spaces.length; j++){
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

        // matches with column numbers, water in each column from top to bottom gets counted
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
        int status;
        int[] rowStatus = new int[]{0,-1};
        ArrayList<Space> spaces = new ArrayList<>();

        for(int i = 0; i < p.getSize(); i++) {
            for (int j = 0; j < p.getSize(); j++) {
                if (p.getAquariums()[r][j] == t) {

                    // Space that is present in that box gets added to array
                    spaces.add(p.getSpaces()[r][j]);

                    rowStatus[1] = j; //column number
                }
            }

            // setting status
            if (!spaces.isEmpty() && spaces.stream().allMatch(Space.WATER::equals)){
                status = 1;
            }
            else if (!spaces.isEmpty() && spaces.stream().noneMatch(Space.WATER::equals)){
                status = 2;
            }
            else if (spaces.isEmpty()){
                status = 0;
            }
            else {
                status = 3;
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
        String ok = "";

        for (int i = 0; i < p.getAquariums()[0].length; i++){
            if (rowStatus(p, t, i)[0] == 3){
                ok = i + "," + rowStatus(p, t, i)[1];
            }

            // 'index out of bound' check performed using (i + 1) < p.getAquariums()[0].length
            else if (((i + 1) < p.getAquariums()[0].length) && rowStatus(p, t, i+1)[0] == 2 && rowStatus(p, t, i)[0] == 1){
                ok = i + "," + rowStatus(p, t, i)[1];
            }
        }
        // TODO 19
        return ok;
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
        int noOfAquariums = p.getAquariums() [p.getSize() - 1] [p.getSize() - 1];
        boolean allAquariumsOK = true;
        String feedback = "";
        String wrongAquarium = "";
        String threeTicks = "\u2713\u2713\u2713";

        for(int i = 0; i <= noOfAquariums; i++) {
            if (!isAquariumOK(p, i).equals("")) {
                allAquariumsOK = false;
                wrongAquarium = isAquariumOK(p, i);
            }
        }

        // if solution is right
        if( Arrays.equals(p.getRowTotals(),rowCounts(p)) && Arrays.equals(p.getColumnTotals(),columnCounts(p)) && allAquariumsOK){
            feedback = threeTicks;
        }

        // if row numbers don't match
        else if (!Arrays.equals(p.getRowTotals(), rowCounts(p))){
            int wrongRow = 0;
            for (int i = p.getRowTotals().length -1; i >= 0; i--){
                if (rowCounts(p)[i] != p.getRowTotals()[i]){
                    wrongRow = i;
                }
            }
            feedback = "Row " + wrongRow + " is wrong";
        }

        // if column numbers don't match
        else if (!Arrays.equals(p.getColumnTotals(), columnCounts(p))){
            int wrongColumn = 0;
            for (int i = p.getColumnTotals().length - 1; i >= 0; i--){
                if (columnCounts(p)[i] != p.getColumnTotals()[i]){
                    wrongColumn = i;
                }
            }
            feedback = "Column " + wrongColumn + " is wrong";
        }

        // if aquarium is wrongly filled
        else if (!allAquariumsOK) {
            feedback = "The aquarium at " + wrongAquarium + " is wrong";
        }
        return feedback;
        // TODO 20
    }
}
