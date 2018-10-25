package com.github.jansowa.tictactoe.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

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

@Data
@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeController {
	@Data
	@AllArgsConstructor
	private class BoardState{
		private int state;
		private TicTacToeBoard board;
	}

	@Autowired
	private TicTacToeBoard board;
	@Autowired
	private TicTacToeMechanics mechanics;
	@Autowired
	private Bot ai;
	@Autowired
	private HibernateGameDAO dao;

	@PostMapping("/singleMove")
	BoardState singleMove(
		@RequestParam String field
		){
		Move move = TicTacToeMechanics.stringFieldToMove(field);
		int state = mechanics.singleMove(move);
		//If mode is single player, AI makes his move there:
		if(this.board.getNumberOfPlayers()==1 && state==-1 && this.board.getPlayer()==0){
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