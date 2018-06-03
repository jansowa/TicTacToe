package ticTacToe2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.jansowa.tictactoe.game.OldSimpleBoard;
import com.github.jansowa.tictactoe.game.OldSimpleGameDAO;

@RunWith(MockitoJUnitRunner.class)
public class SimpleGameDAOTest {

	OldSimpleGameDAO simpleDAO;
	
	@Mock
	OldSimpleBoard board;

	private ArrayList<String> testList;
	@Before
	public void setUp(){
		simpleDAO = new OldSimpleGameDAO(board);
	}
	
	@Test
	public final void testDAO() throws IOException, ClassNotFoundException {
		simpleDAO.setBoard(board);
		simpleDAO.saveGame("testSave");
		File file = new File("testSave.bin");
		assertTrue(file.exists());
		OldSimpleBoard board2;
		board2 = (OldSimpleBoard) simpleDAO.loadGame("testSave");
		Assert.assertNotNull(board2);
	}

}
