package interfaces;

public interface Board {
	public String getName();
	public void setName(String name);
	
	public int[] getFields();
	public void setFields(int[] fields);
	
	public int getNumberOfPlayers();
	public void setNumberOfPlayers(int numberOfPlayers);
	
	public int getPlayer();
	public void setPlayer(int player);
}
