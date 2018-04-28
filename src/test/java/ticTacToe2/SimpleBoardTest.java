package ticTacToe2;

import static org.junit.Assert.*;
import game.OldSimpleBoard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleBoardTest {
	OldSimpleBoard board;
	
	@Before
	public void setUp(){
		board = new OldSimpleBoard();
	}
	
	@Test
	public void isGameOverTest(){
		board.singleMove("A1", 1);
		board.singleMove("A3", 1);
		board.singleMove("B1", 0);
		board.singleMove("B2", 1);
		board.singleMove("B3", 1);
		board.singleMove("C1", 0);
		board.singleMove("C2", 0);
		board.singleMove("C3", 0);
		Assert.assertEquals(0, board.isGameOver());
		board = new OldSimpleBoard();
		board.singleMove("A1", 1);
		board.singleMove("A2", 0);
		board.singleMove("A3", 1);
		board.singleMove("B1", 0);
		board.singleMove("B2", 1);
		board.singleMove("B3", 0);
		board.singleMove("C1", 1);
		board.singleMove("C2", 1);
		board.singleMove("C3", 0);
		Assert.assertEquals(1, board.isGameOver());
		board = new OldSimpleBoard();
		board.singleMove("A1", 1);
		board.singleMove("A2", 0);
		board.singleMove("A3", 0);
		board.singleMove("B1", 0);
		board.singleMove("B2", 1);
		board.singleMove("B3", 1);
		board.singleMove("C1", 1);
		board.singleMove("C2", 1);
		board.singleMove("C3", 0);
		Assert.assertEquals(2, board.isGameOver());
	}

	@Test
	public void isMovePossibleTest(){
		Assert.assertEquals(true, board.isMovePossible("A1"));
		board.singleMove("A1", 1);
		board.singleMove("A2", 0);
		board.singleMove("A3", 1);
		Assert.assertEquals(true, board.isMovePossible("B1"));
		Assert.assertEquals(false, board.isMovePossible("A2"));
		board.singleMove("B2", 0);
		board.singleMove("C2", 1);
		board.singleMove("B1", 0);
		board.singleMove("B3", 1);
		board.singleMove("C1", 0);
		Assert.assertEquals(true, board.isMovePossible("C3"));
		Assert.assertEquals(false, board.isMovePossible("B3"));
		Assert.assertEquals(false, board.isMovePossible("C2"));
		board.singleMove("C3", 1);
		Assert.assertEquals(false, board.isMovePossible("C3"));
		Assert.assertEquals(false, board.isMovePossible("B2"));
		Assert.assertEquals(false, board.isMovePossible("A3"));
		Assert.assertEquals(false, board.isMovePossible("C1"));
	}

}
