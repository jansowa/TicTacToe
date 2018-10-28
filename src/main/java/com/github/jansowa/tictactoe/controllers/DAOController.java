package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeDAOUI;

@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class DAOController {
	@Autowired
	private TicTacToeDAOUI daoUi;
	
	@GetMapping("/loadGame")
	public TicTacToeBoard loadGame(
			@RequestParam String gameName){
		return daoUi.loadGame(gameName);
	}
	
	@PostMapping("/saveGame")
	public TicTacToeBoard saveGame(
			@RequestParam String gameName){
		return daoUi.saveGame(gameName);
	}
}