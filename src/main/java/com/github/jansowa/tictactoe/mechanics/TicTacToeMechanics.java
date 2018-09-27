package com.github.jansowa.tictactoe.mechanics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardgame.mechanics.BoardMechanics;
import com.github.jansowa.boardgame.mechanics.Move;
import com.github.jansowa.boardgame.domain.GameBoard;
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
		int[][] fields = this.getBoard().getFields();
		//checks first horizontal line, first vertical line and first diagonal line
		if((fields[0][0]!=-1) &&
			((fields[0][0]==fields[0][1] && fields[0][1]==fields[0][2]) ||
			(fields[0][0]==fields[1][1] && fields[1][1]==fields[2][2]) ||
			(fields[0][0]==fields[1][0] && fields[1][0]==fields[2][0]))){
				return fields[0][0];
		}
		
		//checks second vertical line
		if(fields[0][1]!=-1 && fields[0][1]==fields[1][1] && fields[1][1]==fields[2][1]){
			return fields[0][1];
		}
		
		//checks third vertical line and one diagonal line
		if((fields[0][2]!=-1) &&
			((fields[0][2]==fields[1][2] && fields[1][2]==fields[2][2])||
			(fields[0][2]==fields[1][1] && fields[1][1]==fields[2][0]))){
				return fields[0][2];
		}
		
		//checks second horizontal line
		if(fields[1][0]!=-1 && fields[1][0]==fields[1][1] && fields[1][1]==fields[1][2]){
			return fields[1][0];
		}
		
		//checks third horizontal line
		if(fields[2][0]!=-1 && fields[2][0]==fields[2][1] && fields[2][1]==fields[2][2]){
			return fields[2][0];
		}
		if(this.emptyFields()==0){
			return 2;
		}
		return -1;
	}

	@Override
	public void restartBoard() {
		int[][] empty = {
				{-1, -1, -1},
				{-1, -1, -1},
				{-1, -1, -1}
		};
		this.getBoard().setFields(empty);
		this.getBoard().setPlayer(0);
	}

	@Override
	public int emptyFields(){
		return emptyFields(this.getBoard());
	}
	
	public static int emptyFields(GameBoard board){
		int counter=0;
		int[][] fields = board.getFields();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(fields[i][j]==-1){
					counter++;
				}
			}
		}
		return counter;
	}
	
	@Override
	public void changeBoard(Move move){
		int[][] fields = this.getBoard().getFields();
		int currentPlayer = this.getBoard().getPlayer();
		int y = move.getCoordinates().getY();
		int x = move.getCoordinates().getX();
		fields[y][x]=currentPlayer;
		this.getBoard().setPlayer((currentPlayer+1)%2);
	}

	@Override
	public boolean isMovePossible(Move move) {
		int[][] fields = this.getBoard().getFields();
		int y = move.getCoordinates().getY();
		int x = move.getCoordinates().getX();
		return fields[y][x]==-1;
	}

	public static Move stringFieldToMove(String field){
		return new Move((int) field.charAt(0)-65, (int) field.charAt(1)-49);
	}
	
	public static String moveToStringField(Move move){
		return ""+(char) (move.getCoordinates().getY()+65) +
				Integer.toString(move.getCoordinates().getX()+1);
	}
}
