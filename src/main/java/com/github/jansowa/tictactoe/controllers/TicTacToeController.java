package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.TicTacToeMechanics;

@RestController
@Component
public class TicTacToeController {
	private class BoardState{
		public int state;
		public TicTacToeBoard board;
		
		BoardState(int state, TicTacToeBoard board){
			this.state = state;
			this.board = board;
		}
	}
	
	@Autowired
	TicTacToeBoard board;
	@Autowired
	TicTacToeMechanics mechanics;
	
	@GetMapping("/getBoard")
	TicTacToeBoard getBoard(){
		return this.board;
	}
	
	@GetMapping("/restartBoard")
	TicTacToeBoard restartBoard(){
		mechanics.restartBoard();
		return this.board;
	}
	
	@PostMapping("/singleMove")
	BoardState singleMove(
		@RequestParam String field
		){
		int state = mechanics.singleMove(field);
		BoardState boardState = new BoardState(state, this.board);
		return boardState;
	}
	
	@GetMapping("/singlePlayer")
	TicTacToeBoard singlePlayer(){
		//TODO

		return this.board;
	}
	
	@GetMapping("/multiPlayer")
	TicTacToeBoard multiPlayer(){
		//TODO
		mechanics.restartBoard();
		this.board.setNumberOfPlayers(2);
		this.board.setPlayer(0);
		return this.board;
	}
}