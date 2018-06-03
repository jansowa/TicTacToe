package com.github.jansowa.tictactoe.domain;

import javax.persistence.Entity;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.domain.GameBoard;

@Component
@Entity
public class TicTacToeBoard extends GameBoard {
	//0 for player 0, 1 for player X, -1 for empty field
	public TicTacToeBoard(){
		super();
		int[] empty = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
		this.setFields(empty);
	}
	
	public TicTacToeBoard(String name, int numberOfPlayers, int player){
		super(name, numberOfPlayers, player);
		int[] empty = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
		this.setFields(empty);
	}
}
