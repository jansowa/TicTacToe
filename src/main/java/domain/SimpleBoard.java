package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import interfaces.Board;

@Entity
public class SimpleBoard implements Board {
	@Id
	private String name;
	private int[] fields;
	private int numberOfPlayers;
	private int player;
	
	public SimpleBoard(){
		
	}
	
	@Override
	public int[] getFields() {
		return this.fields;
	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
	}

	@Override
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	@Override
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	@Override
	public int getPlayer() {
		return this.player;
	}

	@Override
	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
