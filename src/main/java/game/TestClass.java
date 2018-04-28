package game;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		OldSimpleBoard board = null;
		SimpleGameDAO dao = new SimpleGameDAO(board);
		board = (OldSimpleBoard) dao.loadGame("gra");
		SimpleGameView view = new SimpleGameView(board);
		view.printBoard();
		
	}
}
