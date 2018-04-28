package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import interfaces.OldBoard;
import interfaces.OldGameDAO;

@Component
public class OldSimpleGameDAO implements OldGameDAO {
	private OldBoard board;
	
	@Autowired
	public OldSimpleGameDAO(OldBoard board){
		this.board = board;
	}
	
	@Override
	public void setBoard(OldBoard board){
		this.board = board;
	}
	
	public OldBoard getBoard(){
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
	public OldBoard loadGame(String name) throws IOException, ClassNotFoundException {
		ObjectInputStream objectIn;
		FileInputStream fileIn;
		fileIn = new FileInputStream(name+".bin");
		objectIn = new ObjectInputStream(fileIn);
		return (OldBoard) objectIn.readObject();
	}

}
