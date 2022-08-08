# 2048-Game-Java-Implimentation-


Playthrough Logic:
    Initialization: 
        - Generate a board
        - Insert two random points
        - Print the board and prompt the user for a move

    Prereqs per move:
        - Check if the user has won (highest < 2048)
        - Check if the board has at least one free space
        - If both are true, continue. Otherwise, print the appropriate messages and break

        1. If the user inserts a valid move
            1.1. Transpose the array to get rid of all blank spots
            1.2. Perform a merge to combine points
            1.3. Transpose the board again to get rid of any blank spots created in 1.1

        2. If the user inserts an invalid move
            2.1. Do nothing, prompt the user for another move


Functions:
    
    1. validPoints(int[][] board) / Return Type: List<int[]> 
        Explanation:
            Iterates through the array and returns a list of arrays. Each array holds a valid point within the matrix.

        Upper Level Psuedo Code:
            - Iterates through the board matrix
            - For each point
                - If point == 0:
                    - Append point to return array
            - Returns array of valid points
            

    2. addNumber(int[][] board, int[] options) / Return Type: int[][]
        Explanation:
            Generates a random number as per probability .8/.2 and places it within the matrix

        Upper Lever Psuedo Code:
            - Innitializes  the validPoints function
            - Picks a random element in the array of possibilities (array contains 8 occurances of 2 and 2 occurances of 4 to achieve .8/.2 probability)
            - Picks a random point from the list of valid points
            - Inserts the random element into the random spot
            - Returns edited board


    3. transpose(int[][] board, String direction) / Return Type: int[][]
        Explanation: 
            This function moves all the elements in the array to one direction to remove any blank spaces. It takes in the parameter direction and performs the given movement based on said input.

        Upper-Level Psuedo Code:
            - For x in range 4
                - For each item in the array
                    - check if the spot next to it is a  0. If it is, move the current spot value to the next spot value and repeat this 4 times in account of 4 rows.


    4. printBoard(int[][] board) / Return Type: int[][]
        Explanation:
            This function simply prints out the array and simultaneouslys returns the highest value in the board

        Upper Level Psuedo Code:
            - For each item in the array:
                - Print the item


    5. clone(int[][] board) // Return Type: int[][]
        Explanation:
            This function clones the input matrix. This is used to compare the board before making a move and after making a move in order to determine if the said move was valid. I did not simply use a temporary variable of type int[][] because I was running into an issue where it was setting the variable to the location of the board instead of its values. This prevented me from comparing and determining if there was a way.

        Upper Level Psuedo Code:
            - For each item in the array   
                - Map the item onto a new array
            - Return the array clone


    6. compare(int[][] board1, int[][] board2) //  Return Type: boolean
        Explanation:
            This function compares two arrays for equivalence.

        Upper-Level Psuedo Code:
            - For each item in the first array
                - Compare it with the same position of the second array
                    - If a discrepancy is detected, return false
            - Return true in the end (if the code reaches here it indicates a disprepancy was not detected throughout the matrix)


    7. checkOver(int[][] board, int[] max) // Return Type: int (0: Continue, 1: End)
        Explanation:
            Checks if the game is over by determining if the user has won or if the user is unable to make any more moves.

        Upper-Level Psuedo Code: 
            - For each item in the array
                - If the item is >= 2048
                    - Give the user the option to quit the game
            
            - Clone the array to a temp variable
            - Perform movements up, down, left, right on the temporary variable
            - If the temporary variable is different from the original board, there is a possible move to be made so the game is not over.


    7. clearTerminal() // Return Type: void
        Explanation: 
            Just clears the terminal with 10 empty lines

    9. 
        moveLeft(int[][] board) // Return Type: int[][]
        moveRight(int[][] board) // Return Type: int[][]
        moveUp(int[][] board) // Return Type: int[][]
        moveDown(int[][] board) // Return Type: int[][]

        Explanation:
            These functions are responsible for performing movements on the matrix.
        
        Upper-Level Psuedo Code:
            - Remove all unnecessary blank spaces depending on the direction you're going using the transpose method. If you're moving right you would transpose right (0, 2, 2, 0) -> (0, 0, 2, 2)
            - Loop through each row and column then check for merge possibilities. 
            - Repeat the previous step for all points in the matrix.
            - Transpose to your direction once more.

            Things to keep in mind:
                I used a second temporary board to make sure points don't merge twice. If a 2 were to merge with another 2 and become 4, when it loops over the 4, if there is another 4 nearby it would merge them to 8. Instead, the temp matrix allows the 4 to be considered "occupied" so it would not merge unnecessarily. 
