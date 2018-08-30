package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardGame.ai.Bot;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.HibernateGameDAO;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;


@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class DAOController {
	@Autowired
	TicTacToeBoard board;
	@Autowired
	HibernateGameDAO dao;
	@Autowired
	TicTacToeController ticTacToeController;
	@Autowired
	TicTacToeMechanics mechanics;
	@Autowired
	Bot ai;
	
	@GetMapping("/loadGame")
	public TicTacToeBoard loadGame(
			@RequestParam String gameName){
		this.board = dao.loadGame(gameName);
		this.ticTacToeController.setBoard(this.board);
		this.mechanics.setBoard(this.board);
		this.ai.setBoard(this.board);
		return this.board;
	}
	
	@PostMapping("/saveGame")
	public TicTacToeBoard saveGame(
			@RequestParam String gameName){
		this.board.setName(gameName);
		dao.saveGame(this.board);
		return this.board;
	}
}
