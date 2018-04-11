package interfaces;

public interface Board {
	public void setPlayers(int players); //set singleplayer or multiplayer
	public void restartBoard();
	public void singleMove(String field, int player);
	public int isGameOver(); //0 player O wins, 1 player X wins, 2 draw game, -1 not end
	public int getPlayersNumber(); //1 for single player, 2 for multi player
	public int getCurrentPlayer(); //0 for O player, 1 for X player
	public int[] getBoard();
	public int getEmptyFields();
}
