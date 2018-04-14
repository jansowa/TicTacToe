package ticTacToe2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import game.SimpleBoard;
import game.SimpleGameDAO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGameDAOTest {

	SimpleGameDAO simpleDAO;
	
	@Mock
	SimpleBoard board;

	private ArrayList<String> testList;
	@Before
	public void setUp(){
		simpleDAO = new SimpleGameDAO(board);
	}
	
	@Test
	public final void testDAO() throws IOException, ClassNotFoundException {
		simpleDAO.setBoard(board);
		simpleDAO.saveGame("testSave");
		File file = new File("testSave.bin");
		assertTrue(file.exists());
		SimpleBoard board2;
		board2 = (SimpleBoard) simpleDAO.loadGame("testSave");
		Assert.assertNotNull(board2);
	}

}
