package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;


@RestController
@Component
public class DAOController {
	@Autowired
	TicTacToeBoard board;
	
	@GetMapping("/loadGame")
	TicTacToeBoard loadGame(
			@RequestParam String gameName){
		//TODO
		System.out.println(gameName);
		return this.board;
	}
	
	@PostMapping("/saveGame")
	TicTacToeBoard saveGame(
			@RequestParam String gameName){
		//TODO
		System.out.println(gameName);
		return this.board;
	}
}
