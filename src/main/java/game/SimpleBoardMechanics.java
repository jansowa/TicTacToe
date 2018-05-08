package game;

import interfaces.Board;
import interfaces.BoardMechanics;

public class SimpleBoardMechanics implements BoardMechanics {
	private Board board;
	
	public SimpleBoardMechanics(Board board){
		this.board = board;
	}
	
	@Override
	public void setBoard(Board board) {
		this.board = board;

	}
	
	@Override
	public int[] getEmptyFields(){
		int[] fields = new int[9];
		for(int i=0; i<9; i++){
			fields[i] = -1;
			//-1 represents empty fields
		}
		return fields;
	}

	@Override
	public void restartBoard() {
		for(int i=0; i<9; i++){
			board.getFields()[i] = -1;
			//-1 represents empty field
		}
	}

	@Override
	public void singleMove(String field, int player) {
		// TODO Auto-generated method stub

	}

	@Override
	public int isGameOver() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countEmptyFields() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String intFieldToStringField(int field) {
		// TODO Auto-generated method stub
		return null;
	}

}
