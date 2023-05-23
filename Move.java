/**
 * Class that represents a move on a Peg board
 * 
 */
public class Move {
    private int startPeg;  // the starting peg
    private int middlePeg; // the middle peg
    private int endPeg;    // the ending peg
    
    /**
     * In order to make a move, the endPeg location should be empty, 
     * and the startPeg and middlePeg should be occupied. 
     * The move consists of moving the startPeg to the endPeg, and
     * removing the middlePeg
     * 
     * @param startPeg 
     * @param middlePeg
     * @param endPeg
     */
    public Move(int startPeg, int middlePeg, int endPeg) {
        this.startPeg = startPeg;
        this.middlePeg = middlePeg;
        this.endPeg = endPeg;
    }

    public static Move reverse(Move move) {
        return new Move(move.endPeg,move.middlePeg,move.startPeg);
    }
    /**
     * @return the startPeg
     */
    public int getStartPeg() {
        return startPeg;
    }

    /**
     * @return the middlePeg
     */
    public int getMiddlePeg() {
        return middlePeg;
    }

    /**
     * @return the endPeg
     */
    public int getEndPeg() {
        return endPeg;
    }    
}