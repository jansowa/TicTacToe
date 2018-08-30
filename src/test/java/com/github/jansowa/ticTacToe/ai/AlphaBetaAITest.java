package com.github.jansowa.ticTacToe.ai;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.github.jansowa.boardGame.mechanics.Move;
import com.github.jansowa.tictactoe.ai.AlphaBetaPruningBot;
import com.github.jansowa.tictactoe.ai.EvaluateTicTacToe;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

public class AlphaBetaAITest {
	TicTacToeBoard board;
	AlphaBetaPruningBot ai;
	EvaluateTicTacToe evaluate;
	
	@Before
	public final void setup(){
		board = new TicTacToeBoard();
		evaluate = new EvaluateTicTacToe();
		ai = new AlphaBetaPruningBot(board, evaluate);
	}
	
	
	@Test
	public final void testNextAIMove() {
		ai.getBoard().setNumberOfPlayers(1);
		Move move = ai.nextBotMove();
		assertEquals(new Move(1,1), move);
		
		this.board.getFields()[1][1]=0;
		this.board.getFields()[0][1]=1;
		move = ai.nextBotMove();
		assertTrue(move.equals(new Move(0, 0))
				|| move.equals(new Move(0, 2)));
	}
	
	@Test
	public final void testEmptyIndexes() {
		int[][] fields1 = {
				{-1, 0, -1}, 
				{1, -1, 0}, 
				{-1, 1, -1}
			};
		this.board.setFields(fields1);
		ArrayList<Move> arList = ai.emptyIndexes(this.board);
		ArrayList<Move> expectedList = new ArrayList<Move>();
		Collections.addAll(expectedList, 
				new Move(0, 0),
				new Move(0, 2),
				new Move(1, 1),
				new Move(2, 0),
				new Move(2, 2));
		assertEquals(arList, expectedList);
		
		int[][] fields2 = {
				{-1, -1, -1}, 
				{0, 1, 0}, 
				{-1, -1, -1}
			};
		this.board.setFields(fields2);
		arList = ai.emptyIndexes(this.board);
		expectedList.clear();
		Collections.addAll(expectedList,
				new Move(0, 0),
				new Move(0, 1),
				new Move(0, 2),
				new Move(2, 0),
				new Move(2, 1),
				new Move(2, 2));
		assertEquals(arList, expectedList);
	}

	@Test
	public final void testMinimaxAlphaBeta() {
		//First tests checks 0 (O) win, 1 (X) win and a tie
		int[][] fields1 = {
			{1, 1, 1},
			{-1, 0, -1},
			{0, -1, 0}
		};
		int[][] fields2 = {
			{1, -1, 1},
			{-1, 1, -1},
			{0, 0, 0}
		};
		int[][] fields3 = {
			{1, 0, 1},
			{0, 1, 0},
			{0, 1, 0}
			};
		this.board.setFields(fields1);
		assertEquals(-10, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 1000));
		
		this.board.setFields(fields2);
		assertEquals(10, ai.minimaxAlphaBeta(this.board, 0, 0, -10000, 1000));
		
		this.board.setFields(fields3);
		assertEquals(0, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 1000));
		
		//Now calculate scores for all posible moves in this board:
		int[][] fields4 = {
			{0, 1, 0},
			{1, 1, 0},
			{-1, -1, -1}
		};
		this.board.setFields(fields4);
		this.board.getFields()[2][0]=0;
		assertEquals(-9, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
		
		this.board.setFields(fields4);
		this.board.getFields()[2][1]=0;
		assertEquals(0, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
		
		this.board.setFields(fields4);
		this.board.getFields()[2][2]=0;
		assertEquals(10, ai.minimaxAlphaBeta(this.board, 0, 1, -10000, 10000));
	}

	@Test
	public final void testFindBestMove(){
		int[][] fields1 ={
			{0, 1, 0},
			{1, 1, 0},
			{-1, -1, -1}
		};
		this.board.setFields(fields1);
		assertEquals(new Move(2, 2), ai.findBestMove(this.board));
		int[][] fields2 = {
			{-1, -1, -1},
			{-1, -1, -1},
			{-1, -1, -1}
		};
		this.board.setFields(fields2);
		assertEquals(new Move(1,1), ai.findBestMove(this.board));
		int[][] fields3 = {
			{-1, 1, -1},
			{-1, 0, -1},
			{-1, -1, -1}
		};
		this.board.setFields(fields3);
		assertTrue(ai.findBestMove(this.board).equals(new Move(0,0))
				|| ai.findBestMove(this.board).equals(new Move(0, 2)));
		int[][] fields4 ={
			{0, 1, -1},
			{-1, 0, -1},
			{-1, -1, 1}
		};
		this.board.setFields(fields4);
		assertEquals(new Move(1,0), ai.findBestMove(this.board));
		int[][] fields5 = {
			{-1, 1, -1},
			{-1, 0, -1},
			{-1, -1, -1}
		};
		this.board.setFields(fields5);
		assertTrue(ai.findBestMove(this.board).equals(new Move(0,0))
		|| ai.findBestMove(this.board).equals(new Move(0, 2)));
	}
}
