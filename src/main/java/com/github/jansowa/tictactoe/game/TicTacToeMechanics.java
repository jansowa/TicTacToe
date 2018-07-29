package com.github.jansowa.tictactoe.game;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardGame.mechanics.BoardMechanics;
import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;

@Component
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class TicTacToeMechanics extends BoardMechanics {
	public TicTacToeMechanics(){
		super();
	}
	
	@Autowired
	public TicTacToeMechanics(TicTacToeBoard board){
		super(board);
	}
	
	@Override
	public int isGameOver() {
		//-1 not over, 0 player O wins, 1 player X wins, 2 draw game
		int[] fields = this.getBoard().getFields();
		//checks first horizontal line, first vertical line and first diagonal line
		if((fields[0]!=-1) &&
			((fields[0]==fields[1] && fields[1]==fields[2]) ||
			(fields[0]==fields[4] && fields[4]==fields[8]) ||
			(fields[0]==fields[3] && fields[3]==fields[6]))){
				return fields[0];
		}
		
		//checks second vertical line
		if(fields[1]!=-1 && fields[1]==fields[4] && fields[4]==fields[7]){
			return fields[1];
		}
		
		//checks third vertical line and one diagonal line
		if((fields[2]!=-1) &&
			((fields[2]==fields[5] && fields[5]==fields[8])||
			(fields[2]==fields[4] && fields[4]==fields[6]))){
				return fields[2];
		}
		
		//checks second horizontal line
		if(fields[3]!=-1 && fields[3]==fields[4] && fields[4]==fields[5]){
			return fields[3];
		}
		
		//checks third horizontal line
		if(fields[6]!=-1 && fields[6]==fields[7] && fields[7]==fields[8]){
			return fields[6];
		}
		if(this.emptyFields()==0){
			return 2;
		}
		return -1;
	}

	@Override
	public void restartBoard() {
		for(int i=0; i<9; i++){
			this.getBoard().getFields()[i]=-1;
			this.getBoard().setPlayer(0);
		}
	}

	@Override
	public int emptyFields(){
		return emptyFields(this.getBoard());
	}
	
	public static int emptyFields(GameBoard board){
		int counter=0;
		int[] fields = board.getFields();
		for(int i=0; i<9; i++){
			if(fields[i]==-1){
				counter++;
			}
		}
		return counter;
	}
	
	@Override
	public void changeBoard(String field){
		int fields[] = this.getBoard().getFields();
		int currentPlayer = this.getBoard().getPlayer();
		fields[strFieldToIntField(field)]=currentPlayer;
		this.getBoard().setPlayer((currentPlayer+1)%2);
	};

	@Override
	public boolean isMovePossible(String field) {
		int[] fields = this.getBoard().getFields();
		if(fields[strFieldToIntField(field)]!=-1){
			return false;
		}
		return true;
	}
	
	public static int strFieldToIntField(String field){
		return (((int) field.charAt(0))-65)*3 + ((int) field.charAt(1))-49;
	}
	
	public static String intFieldToStringField(int field){
		return ""+(char) (field/3+65)+(char) (field%3 + 49);
	}
}
