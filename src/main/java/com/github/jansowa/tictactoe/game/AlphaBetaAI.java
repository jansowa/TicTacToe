package com.github.jansowa.tictactoe.game;

import java.util.ArrayList;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

public class AlphaBetaAI extends AI {

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
		//TODO
		return null;
	}
	
	public int minimaxAlphaBeta(TicTacToeBoard newBoard, int depth, int player, int alpha, int beta){
		//TODO
		return 0;
	}
	
}
