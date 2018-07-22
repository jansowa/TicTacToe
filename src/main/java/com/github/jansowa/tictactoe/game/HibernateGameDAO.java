package com.github.jansowa.tictactoe.game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.GameDAO;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;


@Component
public class HibernateGameDAO extends GameDAO {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		entityManager.getTransaction().commit();
		return board;
	}
}