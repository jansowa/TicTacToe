package com.github.jansowa.tictactoe.mechanics;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.boardGame.mechanics.Move;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Controller
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class AlphaBetaAI extends AI {
	public static final int aiPlayer = 0; //"O", maximizer
	public static final int huPlayer = 1; //"X", minimizer
	Evaluate evaluate;
	
	@Autowired
	public AlphaBetaAI(GameBoard board, Evaluate evaluate) {
		super(board);
		this.evaluate=evaluate;
	}

	public ArrayList<Move> emptyIndexes(TicTacToeBoard board){
		ArrayList<Move> list = new ArrayList<Move>();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(board.getFields()[i][j]==-1){
					list.add(new Move(i, j));
				}
			}
		}
		return list;
	}
	
	@Override
	public Move nextAIMove() {
		return findBestMove((TicTacToeBoard) this.getBoard());
	}
	
	public int minimaxAlphaBeta(TicTacToeBoard newBoard, int depth, int player, int alpha, int beta){
		//player 0  (O) is "maximizer", player 1 (X) is "minimizer"
		int score = evaluate.calculateEvaluation(newBoard, 0);

				//Terminal state - Maximizer won the game
				if(score == 10){
					return score-depth;
				}
				
				//Terminal state - Minimizer won the game
				if(score == -10){
					return score+depth;
				}
				
				ArrayList<Move> emptyFields = emptyIndexes(newBoard);

				//Terminal state - no fields left and nobody won -> draw game
				if(emptyFields.size()==0){
					return 0;
				}
				
				int best=0;
				//maximizer move
				if (player == aiPlayer){
					best = -10000;
				}
				else if(player == huPlayer){
					best = 10000;
				}
				
				//Try every possible moves
				for(int i=0; i<emptyFields.size(); i++){
					//Single Move
					newBoard.getFields()[emptyFields.get(i).getCoordinates().getY()]
							[emptyFields.get(i).getCoordinates().getX()]=player;
					//Call minimax for proper player
					if(player==aiPlayer){
						best = Math.max(best,
								minimaxAlphaBeta(newBoard, depth+1, huPlayer, alpha, beta));
					}
					else{//player==huPlayer
						best = Math.min(best, 
								minimaxAlphaBeta(newBoard, depth+1, aiPlayer, alpha, beta));
					}
					//Undo move
					newBoard.getFields()[emptyFields.get(i).getCoordinates().getY()]
							[emptyFields.get(i).getCoordinates().getX()]=-1;		
					if(player==aiPlayer){
						alpha = Math.max(alpha, best);
						if(beta<=alpha){
							break;
						}
					}
					else{//player==huPlayer
						beta = Math.min(beta, best);
						if(beta<=alpha){
							break;
						}
					}
				}
				return best;
	}
	
	//Returns best move for AI (maximizer)
	public Move findBestMove(TicTacToeBoard board){
		int bestScores = -1000;
		Move bestMove = null;
		ArrayList<Move> emptyFields = emptyIndexes(board);
		int size = emptyFields.size();
		//IMPROVE OF MINIMAX
		//returns "B2" - if opponent makes mistake, AI can win		
		if(size==9){
			return new Move(1, 1);
		}
		//Try all possible moves
		for(int i=0; i<size; i++){
			//Single move
			board.getFields()[emptyFields.get(i).getCoordinates().getY()]
					[emptyFields.get(i).getCoordinates().getX()]=0;
			//Calculate scores for this move:
			int moveScores = minimaxAlphaBeta(board, 0, 1, -10000, 10000);
			//Undo move
			board.getFields()[emptyFields.get(i).getCoordinates().getY()]
					[emptyFields.get(i).getCoordinates().getX()]=-1;
			//Check if move was better then others
			if(moveScores>bestScores){
				bestScores=moveScores;
				bestMove=emptyFields.get(i);
			}
		}
		return bestMove;
	}
	
}
