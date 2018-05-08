package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class TicTacToeBoard extends GameBoard {
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
