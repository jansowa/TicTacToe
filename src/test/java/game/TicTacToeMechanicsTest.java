package game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.AppConfiguration;
import com.github.jansowa.tictactoe.game.TicTacToeMechanics;

public class TicTacToeMechanicsTest {
	private TicTacToeMechanics mechanics;
	int[][] exampleFields;
	@Before
	public final void setUp(){
		//board with empty fields for 2 players, actual player 0
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		mechanics = context.getBean("ticTacToeMechanics", com.github.jansowa.tictactoe.game.TicTacToeMechanics.class);
		int[][] exampleFieldsCreate = {
				{-1, -1, 1, 1, -1, 0, 0, -1, 0}, //game in progress, now player 1
				{1, 1, -1, -1, 0, 0, 0, -1, 1}, //game in progress, now player 0
				{0, 0, 1, 1, 1, 0, 0, 1, 0}, //draw game
				{0, 1, -1, -1, 0, 1, -1, -1, 0}, //player 0 wins
				{0, -1, 1, -1, 0, 1, -1, 0, 1} //player 1 wins
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
		int[] empty = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
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
		assertEquals(-1, mechanics.singleMove("C2"));
		assertEquals(-1, mechanics.singleMove("B1"));
		assertEquals(0, mechanics.singleMove("A3"));
	}

	@Test
	public final void testChangeBoardString() {
		mechanics.changeBoard("A1");
		mechanics.changeBoard("B2");
		mechanics.changeBoard("A2");
		mechanics.changeBoard("B3");
		int[] expected1 = {0, 0, -1, -1, 1, 1, -1, -1, -1};
		assertArrayEquals(expected1, mechanics.getBoard().getFields());
		
		setFieldsSetup(0);
		mechanics.getBoard().setPlayer(1);
		mechanics.changeBoard("B2");
		mechanics.changeBoard("A1");
		mechanics.changeBoard("C2");
		mechanics.changeBoard("A2");
		int[] expected2 = {0, 0, 1, 1, 1, 0, 0, 1, 0};
		assertArrayEquals(expected2, mechanics.getBoard().getFields());
	}

	@Test
	public final void testIsMovePossible() {
		assertTrue(mechanics.isMovePossible("A1"));
		assertTrue(mechanics.isMovePossible("C2"));
		setFieldsSetup(0);
		assertTrue(mechanics.isMovePossible("A2"));
		assertTrue(mechanics.isMovePossible("C2"));
		assertFalse(mechanics.isMovePossible("A3"));
		assertFalse(mechanics.isMovePossible("C1"));
		setFieldsSetup(2);
		assertFalse(mechanics.isMovePossible("B2"));
		assertFalse(mechanics.isMovePossible("C3"));
	}

	@Test
	public final void testStrFieldToIntField() {
		assertEquals(0, TicTacToeMechanics.strFieldToIntField("A1"));
		assertEquals(1, TicTacToeMechanics.strFieldToIntField("A2"));
		assertEquals(2, TicTacToeMechanics.strFieldToIntField("A3"));
		assertEquals(3, TicTacToeMechanics.strFieldToIntField("B1"));
		assertEquals(4, TicTacToeMechanics.strFieldToIntField("B2"));
		assertEquals(5, TicTacToeMechanics.strFieldToIntField("B3"));
		assertEquals(6, TicTacToeMechanics.strFieldToIntField("C1"));
		assertEquals(7, TicTacToeMechanics.strFieldToIntField("C2"));
		assertEquals(8, TicTacToeMechanics.strFieldToIntField("C3"));
	}
	
	@Test
	public final void testIntFieldToStringField() {
		assertEquals("A1", TicTacToeMechanics.intFieldToStringField(0));
		assertEquals("A2", TicTacToeMechanics.intFieldToStringField(1));
		assertEquals("A3", TicTacToeMechanics.intFieldToStringField(2));
		assertEquals("B1", TicTacToeMechanics.intFieldToStringField(3));
		assertEquals("B2", TicTacToeMechanics.intFieldToStringField(4));
		assertEquals("B3", TicTacToeMechanics.intFieldToStringField(5));
		assertEquals("C1", TicTacToeMechanics.intFieldToStringField(6));
		assertEquals("C2", TicTacToeMechanics.intFieldToStringField(7));
		assertEquals("C3", TicTacToeMechanics.intFieldToStringField(8));
	}

}
