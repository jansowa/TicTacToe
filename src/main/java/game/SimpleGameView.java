package game;

import interfaces.Board;
import interfaces.GameView;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleGameView implements GameView {
	Board board;
	Scanner scan;
	
	@Autowired
	public SimpleGameView(Board board){
		this.board = board;
		scan = new Scanner(System.in);
	}
	
	@Override
	public void setBoard(Board board){
		this.board = board;
	}
	
	@Override
	public int startMenu(){
		int result;
		do{
			System.out.println("Welcome in Tic Tac Toe game.");
			System.out.println("Choose \"1\" for player vs AI.");
			System.out.println("Choose \"2\" for player vs player.");
			System.out.println("Choose \"3\" to load game.");
			result = scan.nextInt();
			if(result==1 || result==2 || result==3){
				return result;
			}
			else{
				System.out.println("Bad input!");
			}
		} while(true);
	}
	
	@Override
	public void printBoard() {
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(board.getBoard()[3*i+j]==0){
					System.out.print("O");
				}
				else if(board.getBoard()[3*i+j]==1){
					System.out.print("X");
				}
				else{
					System.out.print(" ");
				}
				if(j!=2){
					System.out.print("|");
				}
			}
			System.out.println();
			if(i!=2){
				System.out.println("-----");
			}
		}
	}

	@Override
	public void endGame(int result) {
		if(result==0){
			System.out.println("Player O wins!");
		}
		else if(result==1){
			System.out.println("Player X wins!");
		}
		else{
			System.out.println("Draw game.");
		}
	}
	
	@Override
	public String takeInput() {
		if(board.getCurrentPlayer()==0){
			System.out.println("Player O move: ");
		}
		else{
			System.out.println("Player X move: ");
		}
		System.out.println("Write 'save' for save or load game or 'menu' for go back to menu.");
		return scan.next();
	}

	@Override
	public String saveGame(){
		System.out.println("Write name of game to save:");
		return scan.next();
	}
	
	@Override
	public String loadGame(){
		System.out.println("Write name of game to load:");
		return scan.next();
	}

	@Override
	public void aiMove() {
		System.out.println("AI move:");		
	}
}
