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
	TicTacToeBoard singleMove(
		@RequestParam String field
		){
		mechanics.singleMove(field);
		return this.board;
	}
}