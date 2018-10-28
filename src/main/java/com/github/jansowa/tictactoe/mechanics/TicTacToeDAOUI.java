package com.github.jansowa.tictactoe.mechanics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.ai.Bot;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Service
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeDAOUI {
	@Autowired
	private TicTacToeBoard board;
	@Autowired
	private HibernateGameDAO dao;
	@Autowired
	private TicTacToeUI ticTacToeUI;
	@Autowired
	private TicTacToeMechanics mechanics;
	@Autowired
	private Bot ai;
	
	public TicTacToeBoard loadGame(String gameName){
		this.board = dao.loadGame(gameName);
		this.mechanics.setBoard(this.board);
		this.ticTacToeUI.setBoard(this.board);
		this.ai.setBoard(this.board);
		return this.board;
	}
	public TicTacToeBoard saveGame(String gameName){
		this.board.setName(gameName);
		dao.saveGame(this.board);
		return this.board;
	}
}
