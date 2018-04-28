package interfaces;

import java.io.IOException;

public interface GameDAO {
	public void setBoard(OldBoard board);
	public void saveGame(String name) throws IOException;
	public OldBoard loadGame(String name) throws IOException, ClassNotFoundException;
}
