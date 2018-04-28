package game;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import interfaces.AI;
import interfaces.OldBoard;
import interfaces.GameDAO;
import interfaces.GameView;

@Component
public class GameApp {
	GameView view;
	OldBoard board;
	AI ai;
	GameDAO dao;
	
	@Autowired
	GameApp(GameView view, OldBoard board, AI ai, GameDAO dao){
		this.view = view;
		this.board = board;
		this.ai = ai;
		this.dao = dao;
	}
	
	private void playerMove() throws ClassNotFoundException, IOException{
		String playerInput = view.takeInput();
		if(playerInput.equals("save")){
			saveGame();
		}
		else if(playerInput.equals("menu")){
			playGame();
			return;
		}
		else{
			board.singleMove(playerInput, board.getCurrentPlayer());
		}
	}
	
	private void singlePlayer() throws ClassNotFoundException, IOException{
		board.setPlayers(1);
		int result=-1;
		do {
			view.printBoard();
			playerMove();
			result = board.isGameOver();
		} while(result==-1);
		view.printBoard();
		view.endGame(result);		
	}
	
	private void multiPlayer() throws ClassNotFoundException, IOException{
		board.setPlayers(2);
		String aiField;
		int result = -1;
		do{
			view.printBoard();
			if(board.getCurrentPlayer()==0){
				aiField=ai.nextAImove();
				board.singleMove(aiField, board.getCurrentPlayer());
			}
			else{
				playerMove();
			}
			result = board.isGameOver();
		} while(result==-1);
		view.printBoard();
		view.endGame(result);
	}
	
	private void loadGame() throws ClassNotFoundException, IOException{
		String name = view.loadGame();
		this.board = dao.loadGame(name);
		view.setBoard(board);
		dao.setBoard(board);
		ai.setBoard(board);
		if(board.getPlayersNumber()==1){
			singlePlayer();
		}
		else{
			multiPlayer();
		}
	}
	
	private void saveGame() throws IOException{
		String name = view.saveGame();
		dao.saveGame(name);
	}
	
	public void playGame() throws ClassNotFoundException, IOException{
		int mode = view.startMenu();
		if(mode==1){
			board.restartBoard();
			multiPlayer();
		}
		else if (mode==2){
			board.restartBoard();
			singlePlayer();
		}
		else{
			loadGame();
		}
	}
}
