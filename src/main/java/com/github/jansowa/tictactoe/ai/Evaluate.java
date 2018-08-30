package com.github.jansowa.tictactoe.ai;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

@Controller
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class Evaluate {
	
	private boolean winning(TicTacToeBoard board, int player){
		TicTacToeMechanics mechanics = new TicTacToeMechanics(board);
		if(mechanics.isGameOver()==player){
			return true;
		}
		return false;
	}
	
	public int calculateEvaluation(TicTacToeBoard board, int player){
		if(winning(board, player)){
			return 10;
		}
		else if(winning (board, (player+1)%2)){
			return -10;
		}
		return 0;
	}
}
