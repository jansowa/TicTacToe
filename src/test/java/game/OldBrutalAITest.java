package game;

import static org.junit.Assert.*;
import interfaces.OldAI;
import interfaces.OldBoard;

import org.junit.Before;
import org.junit.Test;

public class OldBrutalAITest {
	OldBoard board;
	String field;
	OldAI ai;
	
	@Before
	public final void setUp(){
		board = new OldSimpleBoard();
		ai = new OldBrutalAI(board);
	}
	
	@Test
	public final void nextAIMoveTest1() {
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("B1", 1);
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("C3", 1);
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("C2", 1);
		board.singleMove(ai.nextAImove(), 0);
		assertEquals(0, board.isGameOver());
	}
	
	@Test
	public final void nextAIMoveTest2(){
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("C1", 1);
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("C3", 1);
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("A2", 1);
		board.singleMove(ai.nextAImove(), 0);
		board.singleMove("B3", 1);
		board.singleMove(ai.nextAImove(), 0);
		assertEquals(2, board.isGameOver());
	}

}
