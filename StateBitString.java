public class StateBitString extends State {
    private long pegs;

    /**
     * Create a game state for a board, with a list of positions
     * where pegs are missing
     * 
     * @param board the board 
     * @param missingPegs the array of missing peg positions
     */
    StateBitString(Board board, int[] missingPegs) {
        super(board, missingPegs);
    }

    /**
     * Create a new state after a move is applied to current
     * state
     * 
     * @param previous the previous state
     * @param move the move to be applied to the previous state
     */
    StateBitString(State previous, Move move) {
        super(previous, move);
    }

    /**
     * Return true iff there is a peg at position i
     * 
     * @param i the position
     * @return whether there is a peg at this position
     */
    public boolean getPeg(int i) {
        return 0 != ((1L << i) & pegs);
    }

    /**
     * Set a peg state at a position 
     * @param i the position
     * @param value true iff a peg is present at the position
     */
    public void setPeg(int i, boolean value) {
        if (value) {
            pegs |= (1L << i);
        }
        else {
            pegs &= ~(1L << i);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        StateBitString that = (StateBitString) obj;

        return pegs == that.pegs;
    }

    @Override 
    public int hashCode() {
        return Long.hashCode(pegs);
    }

    @Override 
    public int compareTo(State other) {
        return getScore() - other.getScore();
    }
}