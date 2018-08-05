package com.github.jansowa.ticTacToe.mechanics;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.AlphaBetaAI;

public class AlphaBetaAITest {
	TicTacToeBoard board;
	AlphaBetaAI ai;
	
	@Before
	public final void setup(){
		board = new TicTacToeBoard();
		ai = new AlphaBetaAI(board);
	}
	
	
	@Test
	public final void testNextAIMove() {
		ai.getBoard().setNumberOfPlayers(1);
		String move = ai.nextAIMove();
		assertEquals("B2", move);
		
		this.board.getFields()[4]=0;
		this.board.getFields()[1]=1;
		move = ai.nextAIMove();
		assertTrue(move.equals("A1") || move.equals("A3"));
	}

	@Test
	public final void testEmptyIndexes() {
		int[] fields1 = {-1, 0, -1, 1, -1, 0, -1, 1, -1};
		this.board.setFields(fields1);
		ArrayList<Integer> arList = ai.emptyIndexes(this.board);
		ArrayList<Integer> expectedList = new ArrayList<Integer>();
		Collections.addAll(expectedList, 0, 2, 4, 6, 8);
		assertEquals(arList, expectedList);
		
		int[] fields2 = {-1, -1, -1, 0, 1, 0, -1, -1, -1};
		this.board.setFields(fields2);
		arList = ai.emptyIndexes(this.board);
		expectedList.clear();
		Collections.addAll(expectedList, 0, 1, 2, 6, 7, 8);
		assertEquals(arList, expectedList);
	}

	@Test
	public final void testWinning() {
		int[] fields1 = {1, -1, 0, -1, 1, 0, 0, -1, 1};
		this.board.setFields(fields1);
		assertTrue(ai.winning(this.board, 1));
		
		int[] fields2 = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
		this.board.setFields(fields2);
		assertFalse(ai.winning(this.board, 0));
		assertFalse(ai.winning(this.board, 1));
		
		int[] fields3 = {0, 1, -1, -1, 1, 0, 0, 1, -1};
		this.board.setFields(fields3);
		assertTrue(ai.winning(this.board, 1));
	}

	@Test
	public final void testEvaluate() {
		int[] fields1 = 
			{1, 1, 1,
			-1, 0, -1,
			0, -1, 0};
		this.board.setFields(fields1);
		assertEquals(-10, ai.evaluate(this.board, 0));
		assertEquals(10, ai.evaluate(this.board, 1));
		
		int[] fields2=
			{-1, -1, -1,
			-1, -1, -1,
			-1, -1, -1};
		this.board.setFields(fields2);
		assertEquals(0, ai.evaluate(this.board, 0));
		assertEquals(0, ai.evaluate(this.board, 1));
	}

	@Test
	public final void testMinimaxAlphaBeta() {
		//First tests checks 0 (O) win, 1 (X) win and a tie
		int[] fields1 = 
			{1, 1, 1,
			-1, 0, -1,
			0, -1, 0};
		int[] fields2 = 
			{1, -1, 1,
			-1, 1, -1,
			0, 0, 0};
		int[] fields3 =
			{1, 0, 1,
			 0, 1, 0,
			 0, 1, 0
			};
		this.board.setFields(fields1);
		assertEquals(-10, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 1000));
		
		this.board.setFields(fields2);
		assertEquals(10, ai.minimaxAlphaBeta(this.board, 0, 0, -10000, 1000));
		
		this.board.setFields(fields3);
		assertEquals(0, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 1000));
		
		//Now calculate scores for all posible moves in this board:
		int[] fields4 =
			{0, 1, 0,
			1, 1, 0,
			-1, -1, -1};
		this.board.setFields(fields4);
		this.board.getFields()[6]=0;
		assertEquals(-9, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
		
		this.board.setFields(fields4);
		this.board.getFields()[7]=0;
		assertEquals(0, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
		
		this.board.setFields(fields4);
		this.board.getFields()[8]=0;
		assertEquals(10, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
	}

	@Test
	public final void testFindBestMove(){
		int[] fields1 =
			{0, 1, 0,
			1, 1, 0,
			-1, -1, -1};
		this.board.setFields(fields1);
		assertEquals(8, ai.findBestMove(this.board)); //works
		int[] fields2 =
			{-1, -1, -1,
			-1, -1, -1,
			-1, -1, -1};
		this.board.setFields(fields2);
		assertEquals(4, ai.findBestMove(this.board));
		int[] fields3 =
			{-1, 1, -1,
			-1, 0, -1,
			-1, -1, -1};
		this.board.setFields(fields3);
		assertTrue(ai.findBestMove(this.board)==0 || ai.findBestMove(this.board)==2); //works
		int[] fields4 =
			{0, 1, -1,
			-1, 0, -1,
			-1, -1, 1};
		this.board.setFields(fields4);
		assertEquals(3, ai.findBestMove(this.board)); //WORKS
		int[] fields5 =
			{-1, 1, -1,
			-1, 0, -1,
			-1, -1, -1};
		this.board.setFields(fields5);
		assertTrue(ai.findBestMove(this.board)==0 || ai.findBestMove(this.board)==2); //WORKS
	}
}
