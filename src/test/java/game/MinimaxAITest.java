package game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.MinimaxAI;

public class MinimaxAITest {
	private TicTacToeBoard board;
	private MinimaxAI ai;
	
	@Before
	public final void setup(){
		board = new TicTacToeBoard();
		ai = new MinimaxAI(board);
	}
	
	@Test
	public final void testEmptyIndexes() {
		int[] fields1 = {-1, 0, -1, 1, -1, 0, -1, 1, -1};
		this.board.setFields(fields1);
		ArrayList<Integer> arList = ai.emptyIndexes();
		ArrayList<Integer> expectedList = new ArrayList<Integer>();
		Collections.addAll(expectedList, 0, 2, 4, 6, 8);
		assertEquals(arList, expectedList);
		
		int[] fields2 = {-1, -1, -1, 0, 1, 0, -1, -1, -1};
		this.board.setFields(fields2);
		arList = ai.emptyIndexes();
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
	public final void testMinimax() {
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
		assertEquals(-10, ai.minimax(this.board, 0, 1)); //Player 1 (X) wins
		this.board.setFields(fields2);
		assertEquals(10, ai.minimax(this.board, 0, 0)); //Player 0 (O) wins
		this.board.setFields(fields3);
		assertEquals(0, ai.minimax(this.board, 0, 1)); //A tie
		//Now calculate scores for all posible moves in this board:
		int[] fields4 =
			{0, 1, 0,
			1, 1, 0,
			-1, -1, -1};
		this.board.setFields(fields4);
		this.board.getFields()[6]=0;
		assertEquals(-10, ai.minimax(this.board, 0, 1));
		
		this.board.setFields(fields4);
		this.board.getFields()[7]=0;
		assertEquals(0, ai.minimax(this.board, 0, 1));
		
		this.board.setFields(fields4);
		this.board.getFields()[8]=0;
		assertEquals(10, ai.minimax(this.board, 0, 1));
	}

}
