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

import com.github.jansowa.boardgame.controller.GameController;
import com.github.jansowa.boardgame.domain.BoardState;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeUI;

@Data
@RestController
@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeController extends GameController {
	@Autowired
	public TicTacToeController(TicTacToeUI ui){
		super(ui);
	}

	@PostMapping("/singleMove")
	@Override
	public BoardState singleMove(
		@RequestParam String field
		){
		return super.singleMove(field);
	}

	@Override
	@GetMapping("/singlePlayer")
	public TicTacToeBoard singlePlayer(){
		return (TicTacToeBoard) super.singlePlayer();
	}

	@Override
	@GetMapping("/multiPlayer")
	public TicTacToeBoard multiPlayer(){
		return (TicTacToeBoard) super.multiPlayer();
	}
}