package game;

import java.io.IOException;

public class SimpleGameStarter {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		SimpleBoard board = new SimpleBoard();
		SimpleGameView view = new SimpleGameView(board);
		BrutalAI ai = new BrutalAI();
		SimpleGameDAO dao = new SimpleGameDAO(board);
		GameApp simpleGameApp = new GameApp(view, board, ai, dao);
		simpleGameApp.playGame();
	}
}
