package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState; 
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

import java.util.Arrays;

public class LifeImpl implements Life
{
        //Take a current state, apply the rules for each step, and return a resulting state
	public GameState evolve(GameState currentState) {
            
            //Initialise string to build 
            StringBuilder stringBuilder = new StringBuilder();
            
            //Loop through each cell, and calculate whether a cell
            //should stay alive or die
            for( int i = 0; i < 3; i++ )
            {
                for( int j = 0; j <= 3; j++ )
                {
                    //At the end of each row (when j == 3) add a "\n"
                    //Do not add one on the last row (when i != 2)
                    if( j == 3 && i != 2 )
                    {
                        stringBuilder.append("\n");
                    }
                    else if( j != 3 )
                    {
                        int[] indexes = {i, j};
                        
                        //Get the number of alive neighbours of this cell
                        int numNeighbours = getNumNeighbours(currentState, indexes);
                        
                        //Is the cell alive or dead?
                        boolean isAlive = currentState.isCellAliveAt(i, j);
                        
                        //Apply the rules
                        if( isAlive && numNeighbours < 2 )                                  //Underpopulation   (Scenario 1)
                        {
                            stringBuilder.append(".");
                        }
                        else if( isAlive && numNeighbours > 3)                              //Overpopulation    (Scenario 2)
                        {
                            stringBuilder.append(".");
                        }
                        else if( isAlive && numNeighbours >= 2 && numNeighbours <= 3)       //Survival          (Scenario 3)
                        {
                            stringBuilder.append("*");
                        }
                        else if( !isAlive && numNeighbours == 3 )                           //Creation of life  (Scenario 4)
                        {
                            stringBuilder.append("*");
                        }
                        else
                            stringBuilder.append(".");                                      //If no rules apply, stay dead
                    }
                }
            }
            
            //Create a new resulting GameState using our built up string
            GameState resultState = new GameStateImpl(stringBuilder.toString());
            return resultState;          
	}
        
      
        //Calculate the number of live neighbours for the current cell
        private int getNumNeighbours(GameState currentState, int[] cellIndexes)
        {
            //Initialise neighbours
            int neighbours = 0;
            
            //Loop through all neighbouring rows
            for( int i = cellIndexes[0] - 1; i <= cellIndexes[0] + 1; i++ )
            {
                //Check the index is in range
                if( IsValidIndex(i) )                                              
                {
                    //Loop through all neighbouring cols
                    for( int j = cellIndexes[1] - 1; j <= cellIndexes[1] + 1; j++)      
                    {
                        //Check the index is in range
                        if( IsValidIndex(j) )                                      
                        {  
                            //The current cell does not count as a neighbour
                            if( i != cellIndexes[0] || j != cellIndexes[1] )        
                            {
                                //If the cell is alive, increment neighbour count
                                if( currentState.isCellAliveAt(i, j))
                                {
                                    neighbours++;
                                }
                            }
                        }
                    }
                }
            }
            
            //Return neighbour count
            return neighbours;
        }
        
       
        private boolean IsValidIndex(int index)
        {
            //Check the index is in range
            if(index >= 0 && index <= 2)
            {
                return true;
            }
            else
                return false;
        }
}
