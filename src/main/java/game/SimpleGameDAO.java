package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import interfaces.Board;
import interfaces.GameDAO;

@Component
public class SimpleGameDAO implements GameDAO {
	private Board board;
	
	@Autowired
	public SimpleGameDAO(Board board){
		this.board = board;
	}
	
	@Override
	public void setBoard(Board board){
		this.board = board;
	}
	
	public Board getBoard(){
		return board;
	}
	
	@Override
	public void saveGame(String name) throws IOException {
		ObjectOutputStream objectOut;
		FileOutputStream fileOut;
		fileOut = new FileOutputStream(name+".bin");
		objectOut = new ObjectOutputStream(fileOut);
		objectOut.writeObject(board);
		objectOut.close();
	}

	@SuppressWarnings("resource")
	@Override
	public Board loadGame(String name) throws IOException, ClassNotFoundException {
		ObjectInputStream objectIn;
		FileInputStream fileIn;
		fileIn = new FileInputStream(name+".bin");
		objectIn = new ObjectInputStream(fileIn);
		return (Board) objectIn.readObject();
	}

}
