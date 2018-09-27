package com.github.jansowa.tictactoe.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.domain.GameBoard;

@Component
@Entity
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeBoard extends GameBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8700039785267460848L;
	//0 for player 0, 1 for player X, -1 for empty field
	@Id
	@GeneratedValue
	private long id;
	
	public TicTacToeBoard(){
		super();
		int[][] empty = {
				{-1, -1, -1}, 
				{-1, -1, -1}, 
				{-1, -1, -1}
		};
		this.setFields(empty);
	}
	
	public TicTacToeBoard(String name, int numberOfPlayers, int player){
		super(name, numberOfPlayers, player);
		int[][] empty = {
				{-1, -1, -1}, 
				{-1, -1, -1}, 
				{-1, -1, -1}
		};
		this.setFields(empty);
	}
	
	public long getId(){
		return this.id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}
