package com.github.jansowa.tictactoe.ai;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.ai.Evaluate;
import com.github.jansowa.boardgame.domain.GameBoard;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class EvaluateTicTacToe extends Evaluate {
	
	private boolean winning(GameBoard board, int player){
		TicTacToeMechanics mechanics = new TicTacToeMechanics((TicTacToeBoard) board);
		return mechanics.isGameOver()==player;
	}

	@Override
	public int calculateEvaluation(GameBoard board, int player) {
		if(winning(board, player)){
			return 10;
		}
		else if(winning (board, (player+1)%2)){
			return -10;
		}
		return 0; //a tie
	}
}
