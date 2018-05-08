package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.TicTacToeBoard;
import interfaces.Board;

public class HibernateGameDaoTest {
	private HibernateGameDAO dao;
	
	@Before
	public void setUp(){
		dao = new HibernateGameDAO();
	}
	
	@Test
	public final void saveAdnLoadGameTest(){
		TicTacToeBoard board = new TicTacToeBoard();
		board.setName("TestGame");
		dao.saveBoard(board);
		
		TicTacToeBoard loadGame;
		loadGame = (TicTacToeBoard) dao.loadBoard("TestGame");
		assertNotNull(loadGame);
		assertEquals(0, loadGame.getPlayer());
	}
	

}
