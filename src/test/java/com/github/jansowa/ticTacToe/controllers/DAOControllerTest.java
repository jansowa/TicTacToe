package com.github.jansowa.ticTacToe.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import com.github.jansowa.boardGame.mechanics.AI;
import com.github.jansowa.tictactoe.controllers.DAOController;
import com.github.jansowa.tictactoe.controllers.TicTacToeController;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.HibernateGameDAO;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

public class DAOControllerTest {
	TicTacToeBoard board, testBoard1, testBoard2, testBoard3;
	
	@InjectMocks
	private DAOController daoController = new DAOController();
	
	@Mock
	private HibernateGameDAO hibernateDAOMock;
	@Mock
	private TicTacToeController ticTacToeController;
	@Mock
	private TicTacToeMechanics mechanics;
	@Mock
	private AI ai;
	
	@Before
	public final void setUp(){
		MockitoAnnotations.initMocks(this);
		testBoard1 = new TicTacToeBoard();
		int[][] fields1 =	{
				{-1, 0, 1},
				{1, 0, -1},
				{-1, 0, 1}
			};
		testBoard1.setFields(fields1);
		testBoard2 = new TicTacToeBoard();
		int[][] fields2 =	{
				{1, 1, 1},
				{0, -1, 0},
				{-1, 0, -1}
		};
		testBoard2.setFields(fields2);
		testBoard3 = new TicTacToeBoard();
		int[][] fields3 =	{
				{-1, 0, -1},
				{-1, 1, -1},
				{0, 1, 0}
		};
		testBoard3.setFields(fields3);
		Mockito.when(hibernateDAOMock.loadGame("testBoard1")).thenReturn(testBoard1);
		Mockito.when(hibernateDAOMock.loadGame("testBoard2")).thenReturn(testBoard2);
		Mockito.when(hibernateDAOMock.loadGame("testBoard3")).thenReturn(testBoard3);
	}
	
	@Test
	public final void testLoadGame() {
		this.board = daoController.loadGame("testBoard1");
		assertArrayEquals(testBoard1.getFields(), this.board.getFields());
		this.board = daoController.loadGame("testBoard2");
		assertArrayEquals(testBoard2.getFields(), this.board.getFields());
		this.board = daoController.loadGame("testBoard3");
		assertArrayEquals(testBoard3.getFields(), this.board.getFields());
	}
}
