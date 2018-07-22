package com.github.jansowa.tictactoe.game;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Controller
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class AlphaBetaAI extends AI {
	static int aiPlayer = 0; //"O", maximizer
	static int huPlayer = 1; //"X", minimizer
	
	@Autowired
	public AlphaBetaAI(GameBoard board) {
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
		int bestMove = findBestMove((TicTacToeBoard) this.getBoard());
		return TicTacToeMechanics.intFieldToStringField(bestMove);
	}
	
	public int minimaxAlphaBeta(TicTacToeBoard newBoard, int depth, int player, int alpha, int beta){
		//player 0  (O) is "maximizer", player 1 (X) is "minimizer"
		
		
		int score = evaluate(newBoard, 0);
		
		//Maximizer won game
		if(score == 10){
			return score-depth;
		}
		
		//Minimizer won game
		if(score == -10){
			return score+depth;
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
				//Single Move
				newBoard.getFields()[emptyFields.get(i)]=player;
				
				//Call alphaBetaMinimax and choose max value
				best = Math.max(
						best,
						minimaxAlphaBeta(newBoard, depth+1, huPlayer, alpha, beta));
				
				//Undo move
				newBoard.getFields()[emptyFields.get(i)]=-1;
				
				alpha = Math.max(alpha, best);
				if(beta<=alpha){
					break;
				}
			}
			return best;
		} //ready block
		
		//Minimizer's move
		else{ //player==huPlayer
			int best = 10000;
			
			//Try moves in every empty fields
			for(int i=0; i<emptyFields.size(); i++){
				//Single Move
				newBoard.getFields()[emptyFields.get(i)]=player;
				
				best = Math.min(
						best,
						minimaxAlphaBeta(newBoard, depth+1, aiPlayer, alpha, beta));
				//Undo move
				newBoard.getFields()[emptyFields.get(i)]=-1;
				
				beta = Math.min(beta, best);
				if(beta<=alpha){
					break;
				}
			}
			return best;
		}//ready block
	}
	
	//Returns best move for AI (maximizer)
	public int findBestMove(TicTacToeBoard board){
		int bestScores = -1000;
		int bestMove=-1;
		ArrayList<Integer> emptyFields = emptyIndexes(board);
		int size = emptyFields.size();
		//IMPROVE OF MINIMAX
		//returns "B2" - if opponent makes mistake, AI can win		
		if(size==9){
			return 4;
		}
		//Try all possible moves
		for(int i=0; i<size; i++){
			//Single move
			board.getFields()[emptyFields.get(i)]=0;
			//Calculate scores for this move:
			int moveScores = minimaxAlphaBeta(board, 0, 1, -10000, 10000);
			//Undo move
			board.getFields()[emptyFields.get(i)]=-1;
			//Check if move was better then others
			if(moveScores>bestScores){
				bestScores=moveScores;
				bestMove=emptyFields.get(i);
			}
		}
		return bestMove;
	}
	
}
