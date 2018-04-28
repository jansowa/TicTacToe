package interfaces;

public interface BoardMechanics {
	public void setBoard();
	public void restartBoard();
	public void singleMove(String field, int player);
	public int isGameOver(); //0 player O wins, 1 player X wins, 2 draw game, -1 not end
	public int getEmptyFields();
	public String intFieldToStringField(int field);
}
