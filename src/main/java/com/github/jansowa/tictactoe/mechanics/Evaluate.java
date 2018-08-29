package com.github.jansowa.tictactoe.mechanics;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Controller
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class Evaluate {
	
	private boolean winning(TicTacToeBoard board, int player){
		//TODO
		return false;
	}
	
	public int calculateEvaluation(TicTacToeBoard board, int player){
		//TODO
		return 0;
	}
}
