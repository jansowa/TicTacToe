package com.github.jansowa.tictactoe.game;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

import java.util.ArrayList;

public class MinimaxAI extends AI {

	public MinimaxAI(GameBoard board) {
		super(board);
	}

	public ArrayList<Integer> emptyIndexes(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<9; i++){
			if(this.getBoard().getFields()[i]==(-1)){
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
	
	@Override
	public String nextAIMove() {
		// TODO Auto-generated method stub
		int aiPlayer = 0; //"O"
		int huPlayer = 1; //"X"
		return null;
	}
	
	public int minimax(TicTacToeBoard board, int player){
		//TODO
		return 0;
	}

}
