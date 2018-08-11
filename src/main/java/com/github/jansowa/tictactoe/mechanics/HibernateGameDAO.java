package com.github.jansowa.tictactoe.game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.GameDAO;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;


@Component
public class HibernateGameDAO extends GameDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private static final Logger log = LoggerFactory.getLogger(HibernateGameDAO.class);
	
	public HibernateGameDAO(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	@Override
	public void saveGame(GameBoard board) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(board.clone());
		} catch (CloneNotSupportedException e) {
			log.error("saveGame function: ", e);
		}
		entityManager.getTransaction().commit();
	}

	@Override
	public TicTacToeBoard loadGame(String name) {
		TicTacToeBoard board = null;
		entityManager.getTransaction().begin();
		try {
			board = (TicTacToeBoard) entityManager.find(TicTacToeBoard.class, name).clone();
		} catch (CloneNotSupportedException e) {
			log.error("loadGame function: ", e);
		}
		entityManager.getTransaction().commit();
		return board;
	}
}