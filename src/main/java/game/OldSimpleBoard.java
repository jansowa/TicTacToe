package game;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import interfaces.OldBoard;

@Component
public class OldSimpleBoard implements OldBoard, Serializable {
	private int[] fields = new int[9];
	private int numberOfPlayers; //1 for singleplayer, 2 for multiplayer
	public int emptyFields;
	private int player;
	public int gameStatus;
	//gameStatus -2 impossible move, -1 game in progress,
	//0 O wins, 1 X wins, 2 draw game
	
	public OldSimpleBoard(){
		restartBoard();
		this.numberOfPlayers=0;
	}
	
	public OldSimpleBoard(int players){
		restartBoard();
		this.numberOfPlayers = players;
	}
	
	@Override
	public int getEmptyFields(){
		return emptyFields;
	}
	
	@Override
	public void setPlayers(int players){
		this.numberOfPlayers = players;
	}
	
	@Override
	public void restartBoard() {
		for(int i=0; i<9; i++){
			fields[i] = -1;
		}
		emptyFields=9;
		this.player=0;
	}
	
	public int isGameOver(){
		//-1 not over, 0 player O wins, 1 player X wins, 2 player draw game
		if(fields[0]!=-1){
			if(fields[0]==fields[1] && fields[1]==fields[2]){
				return fields[0];
			}
			else if(fields[0]==fields[4] && fields[4]==fields[8]){
				return fields[0];
			}
			else if(fields[0]==fields[3] && fields[3]==fields[6]){
				return fields[0];
			}
		}
		if(fields[1]!=-1 && fields[1]==fields[4] && fields[4]==fields[7]){
			return fields[1];
		}
		if(fields[2]!=-1){
			if(fields[2]==fields[5] && fields[5]==fields[8]){
				return fields[2];
			}
			else if(fields[2]==fields[4] && fields[4]==fields[6]){
				return fields[2];
			}
		}
		if(fields[3]!=-1 && fields[3]==fields[4] && fields[4]==fields[5]){
			return fields[3];
		}
		if(fields[6]!=-1 && fields[6]==fields[7] && fields[7]==fields[8]){
			return fields[6];
		}
		if(emptyFields==0){
			return 2;
		}
		return -1;
	}

	private static int strFieldToIntField(String field){
		return (((int) field.charAt(0))-65)*3 + ((int) field.charAt(1))-49;
	}
	
	public String intFieldToStringField(int field){
		return ""+(char) (field/3+65)+(char) (field%3 + 49);
	}
	
	public boolean isMovePossible(String field){
		if(fields[strFieldToIntField(field)]!=-1){
			return false;
		}
		return true;
	}
	
	@Override
	public void singleMove(String field, int player) {
		if(isMovePossible(field)){
			fields[strFieldToIntField(field)]=player;
			emptyFields--;
			this.player = (this.player+1)%2;
			gameStatus=isGameOver();
		}
		else{
			gameStatus=-2;
		}
	}

	@Override
	public int getPlayersNumber() {
		//1 for singleplayer, 2 for multiplayer
		return this.numberOfPlayers;
	}

	@Override
	public int getCurrentPlayer() {
		//returns 0 if current player is O, 1 if X
		return this.player;
	}

	@Override
	public int[] getBoard() {
		return this.fields;
	}
}
