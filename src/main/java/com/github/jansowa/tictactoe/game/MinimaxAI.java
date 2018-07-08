package com.github.jansowa.tictactoe.game;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

import java.util.ArrayList;

public class MinimaxAI extends AI {
	static int aiPlayer = 0; //"O"
	static int huPlayer = 1; //"X"
	
	public MinimaxAI(GameBoard board) {
		super(board);
	}

	public ArrayList<Integer> emptyIndexes(TicTacToeBoard board){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<9; i++){
			if(board.getFields()[i]==(-1)){
				list.add(i);
			}
		}
		return list;
	}
	
	public boolean winning(TicTacToeBoard board, int player){
		TicTacToeMechanics mechanics = new TicTacToeMechanics(board);
		if(mechanics.isGameOver()==player){
			return true;
		}
		return false;
	}
	
	public int evaluate(TicTacToeBoard board, int player){
		if(winning(board, player)){
			return 10;
		}
		else if(winning (board, (player+1)%2)){
			return -10;
		}
		return 0;
	}
	
	@Override
	public String nextAIMove() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int minimax(TicTacToeBoard newBoard, int depth, int player){
		//player 0  (O) is "maximizer", player 1 (X) is "minimizer"
		
		int score = evaluate(newBoard, 0);
		
		//Maximizer won game
		if(score == 10){
			return score;
		}
		
		//Minimizer won game
		if(score == -10){
			return score;
		}
		
		ArrayList<Integer> emptyFields = emptyIndexes(newBoard);
		
		//No fields left and nobody won -> draw game
		if(emptyFields.size()==0){
			return 0;
		}
		
		//Maximizer's move
		if(player==aiPlayer){
			int best = -10000;
						
			//Try moves in every empty fields
			for(int i=0; i<emptyFields.size(); i++){
				//Single move
				newBoard.getFields()[emptyFields.get(i)]=player;
				
				//Call minimax and choose max value
				best = Math.max(
						best,
						minimax(newBoard, depth+1, huPlayer));
				
				//Undo move
				newBoard.getFields()[emptyFields.get(i)]=-1;
			}
			return best;
		}
		
		//Minimizer's move
		else{ // player==huPlayer
			int best = 10000;
			
			//Try moves in every empty fields
			for(int i=0; i<emptyFields.size(); i++){
				//Single move
				newBoard.getFields()[emptyFields.get(i)]=player;
				
				//Call minimax and choose max value
				best = Math.min(
						best,
						minimax(newBoard, depth+1, aiPlayer));
				
				//Undo move
				newBoard.getFields()[emptyFields.get(i)]=-1;
			}
			return best;
		}
	}

	//Returns best move for AI (maximizer)
	public int findBestMove(TicTacToeBoard board){

		return 0;
	}
}
