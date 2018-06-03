package com.github.jansowa.tictactoe.game;

import java.io.IOException;

public class TestClass {
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		OldSimpleBoard board = null;
		OldSimpleGameDAO dao = new OldSimpleGameDAO(board);
		board = (OldSimpleBoard) dao.loadGame("gra");
		SimpleGameView view = new SimpleGameView(board);
		view.printBoard();
		
	}
}
