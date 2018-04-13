package interfaces;

public interface GameView {
	public int startMenu(); //1 for multiplayer, 2 for singleplayer, 3 for load game
	
	public void printBoard();
	
	public void aiMove();
	
	public void endGame(int result);
	//0 player O wins, 1 player X wins, 2 draw game
	
	public String takeInput();
	//"save" for save game, "menu" for main menu or field for single move
	
	public String saveGame(); //returns name of game to save
	
	public String loadGame(); //returns name of game to load
}