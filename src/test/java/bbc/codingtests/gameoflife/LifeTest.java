package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class LifeTest {

	@Test
	public void testEmptyGrid() {
		String emptyStateInput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState emptyState = new GameStateImpl(emptyStateInput);
                String result = testLife.evolve(emptyState).toString();
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolve(emptyState).toString());
	}

        @Test
	public void testUnderPopulation() {
		String StateInput = "...\n.*.\n...";
                String StateOutput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("When a live cell has fewer than two neighbours, then this cell dies (0 Neighbours)", StateOutput, testLife.evolve(state).toString());
                               
                StateInput = "...\n.*.\n..*";
                StateOutput = "...\n...\n...";

		testLife = new LifeImpl();
		state = new GameStateImpl(StateInput);
		assertEquals("When a live cell has fewer than two neighbours, then this cell dies (1 Neighbour)", StateOutput, testLife.evolve(state).toString());
	}
        
        @Test
	public void testOverCrowding() {
		String StateInput = "***\n.*.\n***";
                String StateOutput = "***\n...\n***";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("When a live cell has more than three neighbours then this cell dies", StateOutput, testLife.evolve(state).toString());              
	}
        
        @Test
	public void testSurvival() {
		String StateInput = ".*.\n*.*\n.**";
                String StateOutput = ".*.\n*.*\n.**";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("When a live cell has two or three neighbours, then this cell stays alive", StateOutput, testLife.evolve(state).toString());
	}
        
        @Test
	public void testLifeCreation() {
		String StateInput = ".**\n..*\n...";
                String StateOutput = ".**\n.**\n...";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("When an empty position has exactly three neighbouring cells, a new cell is created in this position", StateOutput, testLife.evolve(state).toString());
	}
        
        @Test
	public void testGridNoLiveCells() {
		String StateInput = "...\n...\n...";
                String StateOutput = "...\n...\n...";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("Given a game of life with the initial state containing no live cells, the next state also contains no live cells", StateOutput, testLife.evolve(state).toString());
	}
        
        @Test
	public void testSeededGrid() {
		String StateInput = "...\n***\n...";
                String StateOutput = ".*.\n.*.\n.*.";

		Life testLife = new LifeImpl();
		GameState state = new GameStateImpl(StateInput);
		assertEquals("Given a specific input, check a specific output is produced", StateOutput, testLife.evolve(state).toString());
                state = new GameStateImpl(StateOutput);
                assertEquals("Given a specific input, check a specific output is produced", StateInput, testLife.evolve(state).toString());
	}
}
