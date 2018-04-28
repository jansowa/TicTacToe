package game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import interfaces.Board;
import interfaces.GameDAO;

@Component
public class HibernateGameDAO implements GameDAO {
	private Board board;
	
	@Autowired
	public HibernateGameDAO(Board board){
		// TODO constructor
	}
	
	@Override
	public void setBoard(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveBoard(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public Board loadBoard(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
