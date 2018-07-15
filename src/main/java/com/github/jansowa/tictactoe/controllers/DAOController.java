package com.github.jansowa.tictactoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.game.HibernateGameDAO;
import com.github.jansowa.tictactoe.game.TicTacToeMechanics;


@RestController
@Component
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
	AI ai;
	
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
