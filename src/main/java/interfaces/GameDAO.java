package interfaces;

public interface GameDAO {
	public void setBoard(Board board);
	public void saveBoard(String name);
	public Board loadBoard(String name);
}
