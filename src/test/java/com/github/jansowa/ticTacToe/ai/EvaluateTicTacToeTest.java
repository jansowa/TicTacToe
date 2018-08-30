package com.github.jansowa.ticTacToe.ai;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jansowa.tictactoe.ai.EvaluateTicTacToe;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

public class EvaluateTicTacToeTest {
	TicTacToeBoard board;
	EvaluateTicTacToe evaluate;
	
	@Before
	public final void setUp(){
		board = new TicTacToeBoard();
		evaluate = new EvaluateTicTacToe();
	}
	
	@Test
	public final void testEvaluate() {
		int[][] fields1 = {
				{1, 1, 1},
				{-1, 0, -1},
				{0, -1, 0}
			};
			this.board.setFields(fields1);
			assertEquals(-10, evaluate.calculateEvaluation(this.board, 0));
			assertEquals(10, evaluate.calculateEvaluation(this.board, 1));
			
			int[][] fields2={
				{-1, -1, -1},
				{-1, -1, -1},
				{-1, -1, -1}
			};
			this.board.setFields(fields2);
			assertEquals(0, evaluate.calculateEvaluation(this.board, 0));
			assertEquals(0, evaluate.calculateEvaluation(this.board, 1));
	}

}
