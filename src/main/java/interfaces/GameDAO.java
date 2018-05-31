package interfaces;

import com.github.jansowa.boardGame.domain.GameBoard;

public interface GameDAO {
	public void saveBoard(GameBoard board);
	public GameBoard loadBoard(String name);
}
