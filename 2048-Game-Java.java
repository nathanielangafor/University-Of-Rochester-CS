import java.util.*;


public class App {
    public static int randomNumber(int minValue, int maxValue) {
        Random r = new Random();
        int randomValue = r.nextInt((maxValue - minValue) + 1) + minValue;
        return randomValue; 
    }

    // Returns what points within the board a number can be added
    public static List<int[]> validPoints(int[][] board) {
        List<int[]> vp = new ArrayList<>();
        // We know the board is 4x4
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
            	// Find where the 0's are on the board
                if (board[i][j] == 0) {
                    vp.add(new int[] {i, j});
                }
            }
        }
        return vp;
    }

    // Add a 2 or 4 to a valid empty spot on the board
    public static int[][] addNumber(int[][] board) {
    	// array of options, 80% 2's, 20% 4's
        int[] options = {2, 2, 2, 2, 2, 2, 2, 2, 4, 4};
        // List of all valid points on the board
        List<int[]> vp = validPoints(board);
        if (!vp.isEmpty()) {
            Random r = new Random();
            int[] randomPoint = vp.get(r.nextInt(vp.size()));
    
            board[randomPoint[0]][randomPoint[1]] = options[r.nextInt(options.length)];
            return board;
        } else {
            return new int[0][0];
        }
    }

    //make board by adding the two numbers
    public static int[][] makeBoard() {
        int [][] board = new int[4][4];
        addNumber(board);
        addNumber(board);
        return board;
    }
    
    // All the movement methods are responsible to it's respected movement function
    public static int[][] moveUp(int[][] board) {
        board = transpose(board, "up");
        for (int i = 0; i <= 3; i++) {
        	 // Loop through each row
            for (int j = 0; j <= 3; j++) {
                // This keeps track of if a spot has already been merged with (meaning it shoudn't be merged with again)
                int[][] tempBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                // Check all items ahead of it for merge possibilities 
                for (int k = i; k >= 0; k--) {
                    if (k != i || i == 0) {
                        if (board[k][j] != 0) {
                            if (tempBoard[i][j] != -1) {

                                if (board[i][j] == board[k][j] && (i - k == 1 || i - k == -1)) {

                                    board[i][j] = board[k][j] + board[i][j]; 
                                    board[k][j] = 0;

                                    tempBoard[i][j] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        board = transpose(board, "up");
        return board;
    }

    public static int[][] moveDown(int[][] board) {
        board = transpose(board, "down");
        for (int i = 3; i >= 0; i--) {
       	 // Loop through each row
            for (int j = 0; j <= 3; j++) {
                // This keeps track of if a spot has already been merged with (meaning it shoudn't be merged with again)
                int[][] tempBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                // Check all items ahead of it for merge possibilities 
                for (int k = i; k <= 3; k++) {
                    if (k != i || i == 3) {
                        if (board[k][j] != 0) {
                            if (tempBoard[i][j] != -1) {

                                if (board[i][j] == board[k][j] && (i - k == 1 || i - k == -1)) {

                                    board[i][j] = board[k][j] + board[i][j]; 
                                    board[k][j] = 0;

                                    tempBoard[i][j] = -1;
                                } 
                            }
                        }
                    }
                }
            }
        }
        board = transpose(board, "down");
        return board;
    }

    public static int[][] moveLeft(int[][] board) {
        board = transpose(board, "left");
        for (int i = 0; i <= 3; i++) {
       	 // Loop through each row
            for (int j = 0; j <= 3; j++) {
            	 // This keeps track of if a spot has already been merged with (meaning it shoudnt be merged with again)
                int[][] tempBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                // Check all items ahead of it for merge possibilities 
                for (int k = j; k <= 3; k++) {
                    if (k != j || j == 0) {
                        if (board[i][k] != 0) {
                            if (tempBoard[i][j] != -1) {

                                if (board[i][j] == board[i][k] && (j - k == 1 || j - k == -1)) {

                                    board[i][j] = board[i][k] + board[i][j]; 
                                    board[i][k] = 0;

                                    tempBoard[i][j] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        board = transpose(board, "left");
        return board;
    }
  
    public static int[][] moveRight(int[][] board) {
        board = transpose(board, "right");
        for (int i = 0; i <= 3; i++) {
            // Loop through each row
            for (int j = 3; j >= 0; j--) {
                // This keeps track of if a spot has already been merged with (meaning it shoudnt be merged with again)
                int[][] tempBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                // Check all items ahead of it for merge possibilities 
                for (int k = j; k >= 0; k--) {
                    if (k != j || j == 3) {
                        if (board[i][k] != 0) {
                            if (tempBoard[i][j] != -1) {
    
                                if (board[i][j] == board[i][k] && (j - k == 1 || j - k == -1)) {
    
                                    board[i][j] = board[i][k] + board[i][j]; 
                                    board[i][k] = 0;
    
                                    tempBoard[i][j] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }
        board = transpose(board, "right");
        return board;
    }
    
 
    // move all the elements in the array to one direction to remove any blank spaces.   
    public static int[][] transpose(int[][] board, String direction) {
        if (direction == "right") {
            int tempVar;
            
            for (int k = 0; k <= 3; k++) {
                for (int i = 0; i <= 3; i++) {
                    for (int j = 0; j <= 2; j++) {
                    	//check if the spot next to it is a  0
                        tempVar = board[i][j + 1];
                        //move the current spot value to the next spot value
                        if (tempVar == 0) {
                            board[i][j + 1] = board[i][j];
                            board[i][j] = 0;
                        } 
                    }
                }
            }
        }
        if (direction == "left") {
            int tempVar;
            for (int k = 0; k <= 3; k++) {
                for (int i = 0; i <= 3; i++) {
                    for (int j = 3; j >= 1; j--) {
                    	//check if the spot next to it is a  0
                        tempVar = board[i][j - 1];
                        //move the current spot value to the next spot value
                        if (tempVar == 0) {
                            board[i][j - 1] = board[i][j];
                            board[i][j] = 0;
                        } 
                    }
                }
            }
        }
        if (direction == "down") {
            int tempVar;
            for (int k = 0; k <= 3; k++) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 3; j++) {
                    	//check if the spot below to it is a  0
                        tempVar = board[i + 1][j];
                      //move the current spot value to the next spot value
                        if (tempVar == 0) {
                            board[i + 1][j] = board[i][j];
                            board[i][j] = 0;
                        } 
                    }
                }
            }
        }
        if (direction == "up") {
            int tempVar;
            for (int k = 0; k <= 3; k++) {
                for (int i = 3; i >= 1; i--) {
                    for (int j = 0; j <= 3; j++) {
                    	//check if the spot above to it is a  0
                        tempVar = board[i - 1][j];
                      //move the current spot value to the next spot value
                        if (tempVar == 0) {
                            board[i - 1][j] = board[i][j];
                            board[i][j] = 0;
                        } 
                    }
                }
            }
        }
        return board;
    }
        
    //print out the array and simultaneously returns the highest value in the board
    public static int printBoard(int[][] board) {
        int highest = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j] + "       ");
                if (board[i][j] > highest) {
                    highest = board[i][j];
                }
            }
            System.out.println("\n\n");
        }
        System.out.println();
        return highest;
    }
    //This function clones the input matrix. 
    //This is used to compare the board before making a move and after making a move in order to determine if said move was valid.
    public static int[][] clone(int[][] board) {
        int[][] board2 = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board2[i][j] = board[i][j];
            }
        }
        return board2;
    }

    // compare two arrays for equivalence.
    public static boolean compare(int[][] board1, int[][] board2) {
        boolean clone = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board1[i][j] != board2[i][j]) {
                    clone = false;
                }
            }
        }
        return clone;
    }

    // Clear the terminal
    public static void clearTerminal() {
        for (int o = 0; o < 11; o++) {
            System.out.println("\n");
        }
    }

    //Checks if the game is over by determining if the user has won or if the user is unable to make any more moves.
    public static int checkOver(int[][] board, int validMoves) {
        int highest;
        highest = printBoard(board);
        System.out.printf("--- Moves Made: %d ---\n--- Highest Value: %d ---\n", validMoves, highest);
        // Game ends of the highest value is 2048
        if (highest == 2048) {
            System.out.printf("You Won! It took you %s moves.\nIf you would like to quit simply input q otherwise keep playing!\n", validMoves);
            return 0;
          // game ends if unable to make another move
        } else {
            int[][] tempBoard = clone(board);
            tempBoard = moveDown(tempBoard);
            tempBoard = moveUp(tempBoard);
            tempBoard = moveLeft(tempBoard);
            tempBoard = moveRight(tempBoard);

            if (compare(board, tempBoard)) {
                System.out.println("Game over! There are no more free spaces on the board.");
                return 1;
            } else {
                return 0;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        char confirmation;
        int validMoves = 0;
        // Make Initial Board
        int[][] board = makeBoard();
        Scanner input = new Scanner(System.in);
        String previousMove = "";

        while (true) {
            clearTerminal();
        	// Check if game is over
            if (checkOver(board, validMoves) == 1) {
                break;
            }
            /*
             * This section is mainly for method calls and prompting the user
             * It prints to console how to play the game
             * How the movements works and how to restart and end the game before it is officially over. 
             */
            System.out.println(previousMove);
            System.out.print("Enter your next move (w/A/s/D or q/R): ");
            char direction = input.nextLine().charAt(0);
            // Direction up(w)
            if (direction == 'w' || direction == 'W') {
                int[][] boardClone = clone(board);
                board = moveUp(board);

                if (!compare(board, boardClone)){
                    board = addNumber(board);
                    previousMove = "Previous Move: UP (Valid)";
                    validMoves++;
                } else {
                    previousMove = "Previous Move: UP (Invalid)";
                }
            }
         // Direction left(a)
            if (direction == 'a' || direction == 'A') {
                int[][] boardClone = clone(board);
                board = moveLeft(board);

                if (!compare(board, boardClone)){
                    board = addNumber(board);
                    previousMove = "Previous Move: LEFT (Valid)";
                    validMoves++;
                } else {
                    previousMove = "Previous Move: LEFT (Invalid)";
                }                
            }
         // Direction down(s)
            if (direction == 's' || direction == 'S') {
                int[][] boardClone = clone(board);
                board = moveDown(board);

                if (!compare(board, boardClone)){
                    board = addNumber(board);
                    previousMove = "Previous Move: DOWN (Valid)";
                    validMoves++;
                } else {
                    previousMove = "Previous Move: DOWN (Invalid)";
                }                
            }
         // Direction right(d)
            if (direction == 'd' || direction == 'D') {
                int[][] boardClone = clone(board);
                board = moveRight(board);

                if (!compare(board, boardClone)){
                    board = addNumber(board);
                    previousMove = "Previous Move: RIGHT (Valid)";
                    validMoves++;
                } else {
                    previousMove = "Previous Move: RIGHT (Invalid)";
                }                
            }
         // Functionality quit(q)
            if (direction == 'q' || direction == 'Q') {
                System.out.println("Are you sure you want to quit? [y/N]");
                confirmation = input.nextLine().charAt(0);
                
                if (confirmation == 'y' || confirmation == 'Y') {
                    input.close();
                    break;
                }
            }
         // Functionality restart(r)
            if (direction == 'r' || direction == 'R') {
                System.out.println("Are you sure you want to restart? [y/N]");
                confirmation = input.nextLine().charAt(0);
                
                if (confirmation == 'y' || confirmation == 'Y') {
                    board = makeBoard();
                }
            }
        }
    }
}
