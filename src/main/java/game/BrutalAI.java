package game;

import interfaces.AI;
import interfaces.Board;

public class BrutalAI implements AI {
	Board board;
	
	public BrutalAI(Board board){
		this.board = board;
	}
	
	private String firstMove(){
		//first move
		return "B2";
	}
	
	private String secondMove(){
		//TODO second move
		return "";
	}
	
	private String thirdMove(){
		//TODO third move
		return "";
	}
	
	private String fourthMove(){
		///TODO fourth move
		return "";
	}
	
	private String fifthMove(){
		//TODO fifth move
		for(int i=0; i<9; i++){
			if(board.getBoard()[i]==-1){
				//return field
			}
		}
		return "";
	}
	
	@Override
	public String nextAImove(){
		//TODO very brutal AI move
		if(board.getEmptyFields()==9){
			return firstMove();
		}
		if(board.getEmptyFields()==7){
			return secondMove();
		}
		if(board.getEmptyFields()==5){
			return thirdMove();
		}
		if(board.getEmptyFields()==3){
			return fourthMove();
		}
		if(board.getEmptyFields()==1){
			return fifthMove();
		}
		return "";
	}
}
