import java.util.Arrays;

public class StateDAT extends State {
    private boolean[] pegs;

    /**
     * Create a game state for a board, with a list of positions
     * where pegs are missing
     * 
     * @param board the board 
     * @param missingPegs the array of missing peg positions
     */
    StateDAT(Board board, int[] missingPegs) {
        super(board, missingPegs);
    }

    /**
     * Create a new state after a move is applied to current
     * state
     * 
     * @param previous the previous state
     * @param move the move to be applied to the previous state
     */
    StateDAT(State previous, Move move) {
        super(previous, move);
    }

    /**
     * Return true iff there is a peg at position i
     * 
     * @param i the position
     * @return whether there is a peg at this position
     */
    public boolean getPeg(int i) {
        if (pegs == null) pegs = new boolean[board.getHoleCount()];
        return pegs[i];
    }

    /**
     * Set a peg state at a position 
     * @param i the position
     * @param value true iff a peg is present at the position
     */
    public void setPeg(int i, boolean value) {
        if (pegs == null) pegs = new boolean[board.getHoleCount()];
        pegs[i] = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        StateDAT that = (StateDAT) obj;

        return Arrays.equals(pegs, that.pegs);
    }

    @Override 
    public int hashCode() {
        return Arrays.hashCode(pegs);
    }

    @Override 
    public int compareTo(State other) {
        return getScore() - other.getScore();
    }
}