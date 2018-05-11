package domain;

import javax.persistence.Entity;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.domain.GameBoard;

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
