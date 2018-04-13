package game;

import interfaces.AI;
import interfaces.Board;

public class BrutalAI implements AI {
	Board board;
	String brutalState;
	
	public BrutalAI(Board board){
		this.board = board;
		brutalState="";
	}
	
	@Override
	public void setBoard(Board board){
		this.board = board;
	}
	
	private String firstMove(){
		return "B2";
	} //ready func
	
	private String secondMove(){
		if(this.board.getBoard()[1]==1 || this.board.getBoard()[3]==1
				||this.board.getBoard()[2]==1 || this.board.getBoard()[6]==1){
			brutalState="A1";
			return "A1";
		}
		else if(this.board.getBoard()[5]==1 || this.board.getBoard()[7]==1){
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
			if(this.board.getBoard()[8]==-1){
				return "C1";
				//endgame
			}
			else if(this.board.getBoard()[1]==1){
				brutalState = "A1B1";
				return "B1";
			}
			else if(this.board.getBoard()[2]==1){
				brutalState = "A1B3";
				return "B3";
			}
			else if(this.board.getBoard()[3]==1){
				brutalState = "A1A2";
				return "A2";
			}
			else if(this.board.getBoard()[6]==1){
				brutalState = "A1C2";
				return "C2";
			}
		} //full block
		else if(brutalState.equals("C3")){
			if(this.board.getBoard()[0]==-1){
				return "A1";
				//endgame
			}
			else if(this.board.getBoard()[5]==1){
				brutalState = "C3C2";
				return "C2";
			}
			else if(this.board.getBoard()[7]==1){
				brutalState = "C3B3";
			}
		} //full block
		else if(brutalState.equals("A3")){
			if(this.board.getBoard()[6]==-1){
				return "C1";
				//endgame
			}
			else if(this.board.getBoard()[8]==1){//player at C3 and C1
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
		//TODO third move
		if(brutalState.equals("A3C2")){
			if(this.board.getBoard()[1]==-1){
				return "A2";
			} //endgame
			else{
				return "B3";
			}
		} //full block
		else if(brutalState.equals("A3B1")){
			if(this.board.getBoard()[5]==-1){
				return "B3";
			}
			else{
				return "A2";
			}
		}
		else if(brutalState.equals("C3C2")){
			if(this.board.getBoard()[1]==-1){
				return "A2";
			}//end game
			else{
				return "C1";
			}//end game
		} //full block
		else if(brutalState.equals("C3B3")){
			if(this.board.getBoard()[2]==-1){
				return "A3";
			}//end game
			else{
				return "B1";
			}//end game
		} //full block
		else if(brutalState.equals("A1B1")){
			if(this.board.getBoard()[5]==-1){
				return "B3";
			}//end game
			else{
				return "C1";
			}//end game
		} //full block
		else if(brutalState.equals("A1B3")){
			if(this.board.getBoard()[3]==-1){
				return "B1";
			}//end game
			else{
				return "A2";
			}
		} //full block
		else if(brutalState.equals("A1A2")){
			if(this.board.getBoard()[2]==-1){
				return "A3";
			}//end game
			else{
				return "C2";
			}//end game
		}//full block
		else if(brutalState.equals("A1C2")){
			if(this.board.getBoard()[1]==-1){
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
			if(board.getBoard()[i]==-1){
				return board.intFieldToStringField(i);
			}
		}
		return "";
	} //ready func
	
	@Override
	public String nextAImove(){
		//very brutal AI move
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
