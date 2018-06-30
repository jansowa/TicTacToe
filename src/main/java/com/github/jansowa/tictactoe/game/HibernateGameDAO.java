package com.github.jansowa.tictactoe.game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.GameDAO;


@Component
public class HibernateGameDAO extends GameDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	@Autowired
	public HibernateGameDAO(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void saveGame(GameBoard board) {
		entityManager.getTransaction().begin();
		entityManager.persist(board);
		entityManager.getTransaction().commit();
	}

	@Override
	public GameBoard loadGame(String name) {
		GameBoard board;
		entityManager.getTransaction().begin();
		board = entityManager.find(GameBoard.class, name);
		entityManager.getTransaction().commit();
		return board;
	}
}