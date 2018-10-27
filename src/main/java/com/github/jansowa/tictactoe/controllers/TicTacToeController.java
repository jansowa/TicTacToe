package com.github.jansowa.tictactoe.controllers;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.tictactoe.domain.BoardState;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeUI;

@Data
@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeController {
	@Autowired
	private TicTacToeUI ui;

	@PostMapping("/singleMove")
	BoardState singleMove(
		@RequestParam String field
		){
		return ui.singleMove(field);
	}

	@GetMapping("/singlePlayer")
	TicTacToeBoard singlePlayer(){
		return ui.singlePlayer();
	}

	@GetMapping("/multiPlayer")
	TicTacToeBoard multiPlayer(){
		return ui.multiPlayer();
	}
}