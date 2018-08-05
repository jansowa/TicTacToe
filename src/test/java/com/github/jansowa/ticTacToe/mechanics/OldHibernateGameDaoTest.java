package com.github.jansowa.ticTacToe.mechanics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.HibernateGameDAO;

public class OldHibernateGameDaoTest {
	//Works while database runs
	//It's integration test, should be implemented with different tools
	/*private HibernateGameDAO dao;
	
	@Before
	public void setUp(){
		dao = new HibernateGameDAO();
	}
	
	@Test
	public final void saveAdnLoadGameTest(){
		TicTacToeBoard board = new TicTacToeBoard();
		board.setName("TestGame10");
		int[] fields = {-1, -1, 0, -1, -1, 1, -1, -1, 0};
		board.setFields(fields);
		dao.saveGame(board);
		
		TicTacToeBoard loadBoard;
		loadBoard = (TicTacToeBoard) dao.loadGame("TestGame10");
		assertNotNull(loadBoard);
		//assertEquals(0, loadGame.getPlayer());
		assertEquals(board.getFields(), loadBoard.getFields());
	}*/
	

}
