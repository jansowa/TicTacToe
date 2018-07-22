package com.github.jansowa.tictactoe.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.github.jansowa.boardGame.domain.GameBoard;

@Component
@Entity
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeBoard extends GameBoard implements Serializable {
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
