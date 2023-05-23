public abstract class State implements Comparable<State>{
    private State previousState;
    protected Board board;
    protected Integer pegCount;
    protected Integer score;

    /**
     * Create a game state for a board, with a list of positions
     * where pegs are missing
     * 
     * @param board the board 
     * @param missingPegs the array of missing peg positions
     */
    public State(Board board, int[] missingPegs) {
        this.board = board;
        for (int i = 0; i < board.getHoleCount(); i++) {
            setPeg(i,true);
        }
        for (int peg: missingPegs) {
            setPeg(peg,false);
        }
    }

    /**
     * Create a new state after a move is applied to current
     * state
     * 
     * @param previous the previous state
     * @param move the move to be applied to the previous state
     */
    public State(State previous, Move move) {
        this.previousState = previous;
        this.board = previousState.board;
        for (int i = 0; i < board.getHoleCount(); i++) {
            setPeg(i, previousState.getPeg(i));
        }

        if (!isLegal(move)) throw new IllegalArgumentException("Not a valid move");
        setPeg(move.getStartPeg(), false);
        setPeg(move.getMiddlePeg(), false);
        setPeg(move.getEndPeg(), true);
    }

    /**
     * Return true iff there is a peg at position i
     * 
     * @param i the position
     * @return whether there is a peg at this position
     */
    public abstract boolean getPeg(int i);
    
    /**
     * Set a peg state at a position 
     * @param i the position
     * @param value true iff a peg is present at the position
     */
    public abstract void setPeg(int i, boolean value);

    /**
     * 
     * @return the number of pegs currently on the board
     */
    public int pegCount() {
        if (pegCount == null) {
            pegCount = 0;
            for (int i = 0; i < board.getHoleCount(); i++)
                if (getPeg(i)) pegCount++;
        }
        return pegCount;        
    }
    
    /**
     * A score for the board that is the sum of the distances of the 
     * pegs from the center of the board
     * @return the score
     */
    public int getScore() {
        if (score == null) {
            score = 0;
            for (int i = 0; i < board.getHoleCount(); i++)
                if (getPeg(i)) score += board.getScore(i);
        }
        return score;        
    }

    /** 
     * 
     * @return The state prior to the move that created this state
     */
    public State getPrevious() {
        return previousState;
    }
    
    /**
     * Return true iff this move is valid on this board
     * @param move
     */
    public boolean isLegal(Move move) {
        return getPeg(move.getStartPeg()) && getPeg(move.getMiddlePeg()) && !getPeg(move.getEndPeg());
    }

    @Override 
    public abstract int compareTo(State other);
    
    /**
     * Return a string representation of the board
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int row = 0; row < board.getSideLength(); row++) {
            for (int i = 0; i < board.getSideLength() - row; i++) {
                sb.append(" ");
            }
            for (int col = 0; col <= row; col++) {
                if (getPeg(board.getPosition(row, col))) 
                    sb.append("* ");
                else 
                    sb.append("O ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }   
}