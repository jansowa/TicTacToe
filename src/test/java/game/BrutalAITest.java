package game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.AppConfiguration;
import com.github.jansowa.tictactoe.game.BrutalAI;
import com.github.jansowa.tictactoe.game.TicTacToeMechanics;

public class BrutalAITest {
	BrutalAI ai;
	TicTacToeMechanics mechanics;
	
	@Before
	public final void setUp(){
		/*TicTacToeBoard board = new TicTacToeBoard("", 1, 0);
		ai = new BrutalAI(board);
		mechanics = new TicTacToeMechanics(board);*/
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		ai = context.getBean("brutalAI", com.github.jansowa.tictactoe.game.BrutalAI.class);
		mechanics = context.getBean("ticTacToeMechanics", com.github.jansowa.tictactoe.game.TicTacToeMechanics.class);
		mechanics.getBoard().setNumberOfPlayers(1);
		mechanics.getBoard().setPlayer(0);
	}
	
	@Test
	public final void testNextAIMove() {
		mechanics.singleMove(ai.nextAIMove());
		mechanics.singleMove("A1");
		mechanics.singleMove(ai.nextAIMove()); //A3
		mechanics.singleMove("C1");
		mechanics.singleMove(ai.nextAIMove()); //B1
		mechanics.singleMove("B3");
		mechanics.singleMove(ai.nextAIMove()); //A2
		mechanics.singleMove("C2");
		assertEquals(2, mechanics.singleMove(ai.nextAIMove()));
		assertEquals(0, mechanics.emptyFields());
		
		mechanics.restartBoard();
		mechanics.getBoard().setPlayer(0);
		mechanics.singleMove(ai.nextAIMove());
		mechanics.singleMove("A2");
		mechanics.singleMove(ai.nextAIMove()); //A1
		mechanics.singleMove("C3");
		mechanics.singleMove(ai.nextAIMove()); //B1
		mechanics.singleMove("B3");
		assertEquals(0, mechanics.singleMove(ai.nextAIMove()));
	}

}
