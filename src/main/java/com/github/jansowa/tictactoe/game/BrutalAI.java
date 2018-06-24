package com.github.jansowa.tictactoe.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.boardGame.domain.GameBoard;

@Component
public class BrutalAI extends AI{
	private String brutalState;
	private int[] fields;
	
	@Autowired
	public BrutalAI(GameBoard board){
		super(board);
		brutalState = "";
		fields = this.getBoard().getFields();
	}
	
	private String firstMove(){
		return "B2";
	} //ready func
	
	private String secondMove(){
		if(fields[1]==1 || fields[2]==1
				||fields[3]==1 || fields[6]==1){
			brutalState="A1";
			return "A1";
		}
		else if(fields[5]==1 || fields[7]==1){
			brutalState="C3";
			return "C3";
		}
		else { //player at C3 or A1
			brutalState="A3";
			return "A3";
		}
	} //ready func
	
	private String thirdMove(){
		if(brutalState.equals("A1"))
		{
			if(fields[8]==-1){
				return "C3";
				//endgame
			}
			else if(fields[1]==1){
				brutalState = "A1B1";
				return "B1";
			}
			else if(fields[2]==1){
				brutalState = "A1B3";
				return "B3";
			}
			else if(fields[3]==1){
				brutalState = "A1A2";
				return "A2";
			}
			else if(fields[6]==1){
				brutalState = "A1C2";
				return "C2";
			}
		} //full block
		else if(brutalState.equals("C3")){
			if(fields[0]==-1){
				return "A1";
				//endgame
			}
			else if(fields[5]==1){
				brutalState = "C3C2";
				return "C2";
			}
			else if(fields[7]==1){
				brutalState = "C3B3";
				return "B3";
			}
		} //full block
		else if(brutalState.equals("A3")){
			if(fields[6]==-1){
				return "C1";
				//endgame
			}
			else if(fields[8]==1){//player at C3 and C1
				brutalState = "A3C2";
				return "C2";
			}
			else{
				brutalState = "A3B1";
				return "B1";
			}
		} //full block
		return "";
	} //ready func
	
	private String fourthMove(){
		if(brutalState.equals("A3C2")){
			if(fields[1]==-1){
				return "A2";
			} //endgame
			else{
				return "B3";
			}
		} //full block
		else if(brutalState.equals("A3B1")){
			if(fields[5]==-1){
				return "B3";
			}
			else{
				return "A2";
			}
		}
		else if(brutalState.equals("C3C2")){
			if(fields[1]==-1){
				return "A2";
			}//end game
			else{
				return "C1";
			}//end game
		} //full block
		else if(brutalState.equals("C3B3")){
			if(fields[2]==-1){
				return "A3";
			}//end game
			else{
				return "B1";
			}//end game
		} //full block
		else if(brutalState.equals("A1B1")){
			if(fields[5]==-1){
				return "B3";
			}//end game
			else{
				return "C1";
			}//end game
		} //full block
		else if(brutalState.equals("A1B3")){
			if(fields[3]==-1){
				return "B1";
			}//end game
			else{
				return "A2";
			}
		} //full block
		else if(brutalState.equals("A1A2")){
			if(fields[2]==-1){
				return "A3";
			}//end game
			else{
				return "C2";
			}//end game
		}//full block
		else if(brutalState.equals("A1C2")){
			if(fields[1]==-1){
				return "A2";
			}//end game
			else{
				return "B1";
			}
		}//full block
		return "";
	} //ready func
	
	private String fifthMove(){
		for(int i=0; i<9; i++){
			if(fields[i]==-1){
				return TicTacToeMechanics.intFieldToStringField(i);
			}
		}
		return "";
	} //ready func
	
	@Override
	public String nextAIMove(){
		//very brutal AI move
		int emptyFields = TicTacToeMechanics.emptyFields(this.getBoard());
		
		if(emptyFields==9){
			return firstMove();
		}
		if(emptyFields==7){
			return secondMove();
		}
		if(emptyFields==5){
			return thirdMove();
		}
		if(emptyFields==3){
			return fourthMove();
		}
		if(emptyFields==1){
			return fifthMove();
		}
		return "";
	}
}
