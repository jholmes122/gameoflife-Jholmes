package bbc.codingtests.gameoflife.gamestate;

public class GameStateImpl implements GameState {
     /**
     * @The current state of the board
     */
    String[][] GameBoard = new String[3][3];
    
    //TODO implement this method such that live cells are represented as a '*' and dead cells are represented by a '.'
    //TODO use newline ('\n') to separate rows
    @Override
    public String toString() {   
        //Convert our game state to a string
        String result = "";
        
        //Loop through rows and cols 
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++ )
            {
                //Build up the string
                result += GameBoard[i][j];
            }
            
            //Formatting - Don't add a new line after the final row
            if( i != 2 )
            {
                result += "\n";
            }
        }
        return result;
    }

    //TODO implement this constructor to parse an input string and return a new GameStateImpl object representing what you got in the string
    //TODO as above, live cells are '*' and dead cells are '.' Rows are separated by newline ('\n')
    public GameStateImpl(String input) {
        
        //Delimit the input string by \n into 3 rows
        String[] rows = input.split("\n");
        
        //Loop through the delimited string array and populate our game state array
        for(int i = 0; i < rows.length; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                //Get the string element at the specified index and if it is equal to '.'
                //set it to true. Else, it will remain false (dead).
                if(rows[i].substring(j, j+1).equals("*"))
                {
                    GameBoard[i][j] = "*";
                }
                else
                    GameBoard[i][j] = ".";
            }
        }
    }

    //TODO implement this method according to explanation in GameState.java
    public boolean isCellAliveAt(int row, int col) {
        
        //Get the element in our array specified by row and col
        //if it is alive ('*') return true, else, return false
        
        if( GameBoard[row][col] == "*")
        {
            return true;
        }
        else
            return false;
        
    }

    @Override
    public int getRows() {
        
        //Return num rows
        return GameBoard.length;
    }

    @Override
    public int getCols() {
        
        //Return num cols
        return GameBoard[0].length;
    }
}
