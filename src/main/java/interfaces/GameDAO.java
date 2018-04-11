package interfaces;

import java.io.IOException;

public interface GameDAO {
	public void saveGame(String name) throws IOException;
	public Board loadGame(String name) throws IOException, ClassNotFoundException;
}
