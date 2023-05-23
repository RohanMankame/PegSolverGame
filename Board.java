import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private int sideLength;
    private HashMap<Integer, ArrayList<Move>> moves = new HashMap<>();
    private int[] score;
    
    
    /**
     * Initialize board for a triangle with sideLength pegs on each
     * side
     * 
     * @param sideLength
     */
    public Board(int sideLength) {
        if (sideLength > 10 || sideLength < 5) throw new IllegalArgumentException("Side length must be between 3 and 10, inclusive");
        this.sideLength = sideLength;
        for (int row = 0; row < sideLength; row++) {
            for (int col = 0; col <= row; col++) {
                if (row < sideLength-2) {
                    // Down and to the left
                    Move move = new Move(getPosition(row,col),
                            getPosition(row+1,col),
                            getPosition(row+2,col));
                    for (int i = 0; i < 2; i++) {
                        if (!moves.containsKey(move.getStartPeg()))
                            moves.put(move.getStartPeg(), new ArrayList<>());
                        moves.get(move.getStartPeg()).add(move);
                        move = Move.reverse(move);
                    }
                    // Down and to the right
                    move = new Move(getPosition(row,col),
                            getPosition(row+1,col+1),
                            getPosition(row+2,col+2));
                    for (int i = 0; i < 2; i++) {
                        if (!moves.containsKey(move.getStartPeg()))
                            moves.put(move.getStartPeg(), new ArrayList<>());
                        moves.get(move.getStartPeg()).add(move);
                        move = Move.reverse(move);
                    }
                }
                // Same level to the right
                if (col <= row-2) {
                    Move move = new Move(getPosition(row,col),
                            getPosition(row,col+1),
                            getPosition(row,col+2));
                    for (int i = 0; i < 2; i++) {
                        if (!moves.containsKey(move.getStartPeg()))
                            moves.put(move.getStartPeg(), new ArrayList<>());
                        moves.get(move.getStartPeg()).add(move);
                        move = Move.reverse(move);
                    }
                }
            }
        }
        score = new int[getHoleCount()];
        for (int r = 0; r < sideLength; r++) {
            for (int c = 0; c <= r; c++) {
                score[getPosition(r,c)] = Math.abs(r-getSideLength()/2) + Math.abs(c - getSideLength()/2);
            }
        }

    }
    
    
    /**
     * Return all possible states reachable from current state
     * 
     * @param current the current state
     * 
     * @return a list of states reachable from current state
     */
    public ArrayList<State> getNextStates(State current) {
        ArrayList<State> list = new ArrayList<>();
        for (int i = 0; i < getHoleCount(); i++) {
            if (current.getPeg(i)) {
                for (Move move: getMoves(i)) {
                    if (current.isLegal(move)) {
                        State state = null;
                        if (current instanceof StateBitString) {
                            state = new StateBitString(current, move);
                        }
                        else if (current instanceof StateDAT) {
                            state = new StateDAT(current, move);
                        }
                        list.add(state);
                    }
                }
            }
        }
        return list;
    }
    
    /**
     * 
     * @param startPeg
     * @return A list of moves that are possible from a startPeg
     */
    private ArrayList<Move> getMoves(int startPeg) {
        return moves.get(startPeg);
    }
    
    /** 
     * 
     * @return the maximum pegs that can fit in a board
     */
    public int getHoleCount() {
        return sideLength * (sideLength + 1) / 2;
    }
    
    /** 
     * 
     * @return the number of pegs along a side of the board
     */
    public int getSideLength() {
        return sideLength;
    }
    
    /**
     * Translate from a row/col to a position
     * @param row a 0-based row
     * @param col a 0-based column
     * @return
     */
    public int getPosition(int row, int col) {
        if (row >= sideLength || row < 0) throw new IllegalArgumentException("Invalid value for row");
        if (col > row || col < 0) throw new IllegalArgumentException("Invalid value for col");
        return (row+1) * (row + 2) / 2 - (row - col) - 1;
    }

    /**
     * @param position
     * @return      the distance of position from the center of the puzzle
     */
    public int getScore(int position) {
        return score[position];
    }
}