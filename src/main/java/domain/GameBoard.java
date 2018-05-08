package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class GameBoard {
	@Id
	private String name;
	private int[] fields;
	private int numberOfPlayers;
	private int player; //actual player
	
	public GameBoard(){
		this.name = "";
		this.numberOfPlayers = 2;
		this.player = 0;
		this.fields = null;
	}
	
	public GameBoard(String name, int numberOfPlayers, int player){
		this.name = name;
		this.numberOfPlayers = numberOfPlayers;
		this.player = player;
		this.fields = null;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getFields() {
		return fields;
	}

	public void setFields(int[] fields) {
		this.fields = fields;
	}

	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public int getPlayer() {
		return this.player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

}
