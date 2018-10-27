package com.github.jansowa.tictactoe.domain;

import lombok.Data;

@Data
public class BoardState {
	private final TicTacToeBoard board;
	private final int state;
}
