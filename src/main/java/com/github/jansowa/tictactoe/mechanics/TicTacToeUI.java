package com.github.jansowa.tictactoe.mechanics;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.ai.Bot;
import com.github.jansowa.boardgame.mechanics.Move;
import com.github.jansowa.tictactoe.domain.BoardState;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Service
@Data
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeUI {
	@Autowired
	private TicTacToeMechanics mechanics;
	@Autowired
	private TicTacToeBoard board;
	@Autowired
	private Bot ai;
	
	public BoardState singleMove(String field){
		Move move = TicTacToeMechanics.stringFieldToMove(field);
		int state = mechanics.singleMove(move);
		//If mode is single player, AI makes his move here:
		if(this.board.getNumberOfPlayers()==1 && state==-1 && this.board.getPlayer()==0){
			state = mechanics.singleMove(ai.nextBotMove());
		}
		return new BoardState(this.board, state);
	}
	
	public TicTacToeBoard singlePlayer(){
		mechanics.restartBoard();
		this.board.setNumberOfPlayers(1);
		mechanics.singleMove(ai.nextBotMove());
		return this.board;
	}
	
	public TicTacToeBoard multiPlayer(){
		mechanics.restartBoard();
		this.board.setNumberOfPlayers(2);
		return this.board;
	}
}
