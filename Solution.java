import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] missingPegs = new int[m];
        for (int i = 0; i < m; i++) {
            missingPegs[i] = sc.nextInt();
        }
        
        Board board = new Board(n);
               
        //State state = new StateBitString(board, missingPegs);
        State state = new StateDAT(board, missingPegs);
                
        long startTime = System.currentTimeMillis();
        State finalState = solve(state, board);
        printMoves(finalState);
        System.out.println("Elapsed time " + (System.currentTimeMillis() - startTime));        
    }

    // Return the final state if the game can be solved, or null otherwise
    public static State solve(State initState, Board board) {
        
    	if (initState.pegCount()==1) {  // base case
    		return initState;
    	}
    	for();   
    	
        return null;
    }
    
    static void printMoves(State finalState) {
        // TO DO: Implement this method           //prints out backward
    	State currentState = finalState;
    	while (currentState != null) {
    		System.out.print(currentState.toString());
    		currentState = currentState.getPrevious();
    		
    	}
    }
}