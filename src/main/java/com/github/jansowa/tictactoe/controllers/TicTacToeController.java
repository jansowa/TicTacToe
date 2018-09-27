package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.ai.Bot;
import com.github.jansowa.boardgame.mechanics.Move;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.HibernateGameDAO;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeController {
	private class BoardState{
		private int state;
		private TicTacToeBoard board;

		BoardState(int state, TicTacToeBoard board){
			this.state = state;
			this.board = board;
		}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public TicTacToeBoard getBoard() {
			return board;
		}

		public void setBoard(TicTacToeBoard board) {
			this.board = board;
		}
	}

	@Autowired
	TicTacToeBoard board;
	@Autowired
	TicTacToeMechanics mechanics;
	@Autowired
	Bot ai;
	@Autowired
	HibernateGameDAO dao;

	public void setBoard(TicTacToeBoard board) {
		this.board = board;
	}

	@PostMapping("/singleMove")
	BoardState singleMove(
		@RequestParam String field
		){
		Move move = TicTacToeMechanics.stringFieldToMove(field);
		int state = mechanics.singleMove(move);
		//If mode is single player, AI makes his move there:
		if(this.board.getNumberOfPlayers()==1 && state==-1){
			state = mechanics.singleMove(ai.nextBotMove());
		}
		return new BoardState(state, this.board);
	}

	@GetMapping("/singlePlayer")
	TicTacToeBoard singlePlayer(){
		mechanics.restartBoard();
		this.board.setNumberOfPlayers(1);
		mechanics.singleMove(ai.nextBotMove());
		return this.board;
	}

	@GetMapping("/multiPlayer")
	TicTacToeBoard multiPlayer(){
		mechanics.restartBoard();
		this.board.setNumberOfPlayers(2);
		return this.board;
	}
}
