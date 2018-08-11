package com.github.jansowa.ticTacToe.mechanics;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.boardGame.mechanics.Move;
import com.github.jansowa.tictactoe.mechanics.AppConfiguration;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

public class TicTacToeMechanicsTest {
	private TicTacToeMechanics mechanics;
	int[][][] exampleFields;
	@Before
	public final void setUp(){
		//board with empty fields for 2 players, actual player 0
		TicTacToeBoard board = new TicTacToeBoard();
		mechanics = new TicTacToeMechanics(board);
		int[][][] exampleFieldsCreate = {
				{
				{-1, -1, 1}, 
				{1, -1, 0}, 
				{0, -1, 0}
				}, //game in progress, now player 1
				{
				{1, 1, -1}, 
				{-1, 0, 0}, 
				{0, -1, 1}
				}, //game in progress, now player 0
				{
				{0, 0, 1}, 
				{1, 1, 0}, 
				{0, 1, 0}
				}, //draw game
				{
				{0, 1, -1}, 
				{-1, 0, 1}, 
				{-1, -1, 0}
				}, //player 0 wins
				{
				{0, -1, 1}, 
				{-1, 0, 1}, 
				{-1, 0, 1}
				} //player 1 wins
			};
		this.exampleFields = exampleFieldsCreate;
	}
	
	private void setFieldsSetup(int number){
		mechanics.getBoard().setFields(exampleFields[number]);
	}
	
	@Test
	public final void testEmptyFields() {
		setFieldsSetup(0);
		assertEquals(4, mechanics.emptyFields());
		setFieldsSetup(1);
		assertEquals(3, mechanics.emptyFields());
		setFieldsSetup(2);
		assertEquals(0, mechanics.emptyFields());
		setFieldsSetup(3);
		assertEquals(4, mechanics.emptyFields());
		setFieldsSetup(4);
		assertEquals(3, mechanics.emptyFields());
	}

	@Test
	public final void testRestartBoard() {
		mechanics.getBoard().setFields(exampleFields[0]);
		mechanics.restartBoard();
		int[][] empty = {
				{-1, -1, -1}, 
				{-1, -1, -1}, 
				{-1, -1, -1}
				};
		assertArrayEquals(empty, mechanics.getBoard().getFields());
	}

	@Test
	public final void testIsGameOver() {
		setFieldsSetup(0);
		assertEquals(-1, mechanics.isGameOver());
		setFieldsSetup(1);
		assertEquals(-1, mechanics.isGameOver());
		setFieldsSetup(2);
		assertEquals(2, mechanics.isGameOver());
		setFieldsSetup(3);
		assertEquals(0, mechanics.isGameOver());
		setFieldsSetup(4);
		assertEquals(1, mechanics.isGameOver());
	}

	@Test
	public final void testSingleMoveGameBoard() {
		setFieldsSetup(1);
		mechanics.getBoard().setPlayer(0);
		//C2, B1, A3
		assertEquals(-1, mechanics.singleMove(new Move(2,1)));
		assertEquals(-1, mechanics.singleMove(new Move(1,0)));
		assertEquals(0, mechanics.singleMove(new Move(0,2)));
	}

	@Test
	public final void testChangeBoard() {
		mechanics.changeBoard(new Move(0,0));
		mechanics.changeBoard(new Move(1,1));
		mechanics.changeBoard(new Move(0,1));
		mechanics.changeBoard(new Move(1,2));
		int[][] expected1 = {
				{0, 0, -1}, 
				{-1, 1, 1}, 
				{-1, -1, -1}
		};
		assertArrayEquals(expected1, mechanics.getBoard().getFields());
		
		setFieldsSetup(0);
		mechanics.getBoard().setPlayer(1);
		mechanics.changeBoard(new Move(1,1));
		mechanics.changeBoard(new Move(0,0));
		mechanics.changeBoard(new Move(2,1));
		mechanics.changeBoard(new Move(0,1));
		int[][] expected2 = {
				{0, 0, 1}, 
				{1, 1, 0}, 
				{0, 1, 0}
		};
		assertArrayEquals(expected2, mechanics.getBoard().getFields());
	}

	@Test
	public final void testIsMovePossible() {
		assertTrue(mechanics.isMovePossible(new Move(0,0)));
		assertTrue(mechanics.isMovePossible(new Move(2,1)));
		setFieldsSetup(0);
		assertTrue(mechanics.isMovePossible(new Move(0,1)));
		assertTrue(mechanics.isMovePossible(new Move(2,1)));
		assertFalse(mechanics.isMovePossible(new Move(0,2)));
		assertFalse(mechanics.isMovePossible(new Move(2,0)));
		setFieldsSetup(2);
		assertFalse(mechanics.isMovePossible(new Move(1,1)));
		assertFalse(mechanics.isMovePossible(new Move(2,2)));
	}

	@Test
	public final void teststringFieldToMove() {
		assertEquals(new Move(0, 0), TicTacToeMechanics.stringFieldToMove("A1"));
		assertEquals(new Move(0, 1), TicTacToeMechanics.stringFieldToMove("A2"));
		assertEquals(new Move(0, 2), TicTacToeMechanics.stringFieldToMove("A3"));
		assertEquals(new Move(1, 0), TicTacToeMechanics.stringFieldToMove("B1"));
		assertEquals(new Move(1, 1), TicTacToeMechanics.stringFieldToMove("B2"));
		assertEquals(new Move(1, 2), TicTacToeMechanics.stringFieldToMove("B3"));
		assertEquals(new Move(2, 0), TicTacToeMechanics.stringFieldToMove("C1"));
		assertEquals(new Move(2, 1), TicTacToeMechanics.stringFieldToMove("C2"));
		assertEquals(new Move(2, 2), TicTacToeMechanics.stringFieldToMove("C3"));
	}
	
	@Test
	public final void testMoveToStringField() {
		assertEquals("A1", TicTacToeMechanics.moveToStringField(new Move(0, 0)));
		assertEquals("A2", TicTacToeMechanics.moveToStringField(new Move(0, 1)));
		assertEquals("A3", TicTacToeMechanics.moveToStringField(new Move(0, 2)));
		assertEquals("B1", TicTacToeMechanics.moveToStringField(new Move(1, 0)));
		assertEquals("B2", TicTacToeMechanics.moveToStringField(new Move(1, 1)));
		assertEquals("B3", TicTacToeMechanics.moveToStringField(new Move(1, 2)));
		assertEquals("C1", TicTacToeMechanics.moveToStringField(new Move(2, 0)));
		assertEquals("C2", TicTacToeMechanics.moveToStringField(new Move(2, 1)));
		assertEquals("C3", TicTacToeMechanics.moveToStringField(new Move(2, 2)));
	}

}
