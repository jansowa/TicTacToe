package game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.GameBoard;
import domain.TicTacToeBoard;
import interfaces.GameDAO;

//@Component
public class HibernateGameDAO implements GameDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@Autowired
	public HibernateGameDAO(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void saveBoard(GameBoard board) {
		entityManager.getTransaction().begin();
		entityManager.persist(board);
		entityManager.getTransaction().commit();
	}

	@Override
	public GameBoard loadBoard(String name) {
		GameBoard board;
		entityManager.getTransaction().begin();
		board = entityManager.find(GameBoard.class, name);
		entityManager.getTransaction().commit();
		return board;
	}
}