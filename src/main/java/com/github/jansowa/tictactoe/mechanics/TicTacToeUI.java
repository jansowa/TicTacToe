package com.github.jansowa.tictactoe.mechanics;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.ai.Bot;
import com.github.jansowa.boardgame.domain.BoardState;
import com.github.jansowa.boardgame.mechanics.GameUI;
import com.github.jansowa.boardgame.mechanics.Move;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Service
@Data
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeUI extends GameUI {
	/*@Autowired
	private TicTacToeMechanics mechanics;
	@Autowired
	private TicTacToeBoard board;
	@Autowired
	private Bot ai;*/
	@Autowired
	public TicTacToeUI(TicTacToeMechanics mechanics, TicTacToeBoard board, Bot ai){
		super(mechanics, board, ai);
	}
	
	@Override
	public BoardState singleMove(String field){
		Move move = TicTacToeMechanics.stringFieldToMove(field);
		int state = this.getMechanics()
						.singleMove(move);
		//If mode is single player, AI makes his move here:
		if(this.getBoard().getNumberOfPlayers()==1 &&
				state==-1 &&
				this.getBoard().getPlayer()==0){
			state = this.getMechanics().singleMove(this.getAi().nextBotMove());
		}
		return new BoardState(this.getBoard(), state);
	}
	
	public TicTacToeBoard singlePlayer(){
		return (TicTacToeBoard) super.singlePlayer();
	}
	
	public TicTacToeBoard multiPlayer(){
		return (TicTacToeBoard) super.multiPlayer();
	}
}
