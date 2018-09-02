package com.github.jansowa.tictactoe.ai;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;

import com.github.jansowa.boardGame.ai.AlphaBetaPruningBot;
import com.github.jansowa.boardGame.ai.Bot;
import com.github.jansowa.boardGame.ai.Evaluate;
import com.github.jansowa.boardGame.domain.GameBoard;
import com.github.jansowa.boardGame.mechanics.Move;
import com.github.jansowa.tictactoe.domain.TicTacToeBoard;
import com.github.jansowa.tictactoe.mechanics.TicTacToeMechanics;

@Controller
@Scope(
		value=WebApplicationContext.SCOPE_SESSION)
public class AlphaBetaPruningTicTacToeBot extends AlphaBetaPruningBot {
	//public static final int bot = 0; //"O", maximizer
	//public static final int huPlayer = 1; //"X", minimizer
	
	@Autowired
	public AlphaBetaPruningTicTacToeBot(GameBoard board, Evaluate evaluate, TicTacToeMechanics mechanics) {
		super(board, evaluate, mechanics);
	}

	@Override
	public ArrayList<Move> emptyIndexes(GameBoard board){
		ArrayList<Move> list = new ArrayList<Move>();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if(board.getFields()[i][j]==-1){
					list.add(new Move(i, j));
				}
			}
		}
		return list;
	}

	@Override
	public void undoMove(Move move) {
		this.getBoard().getFields()[move.getCoordinates().getY()][move.getCoordinates().getX()]=-1;
		this.getBoard().setPlayer((this.getBoard().getPlayer()+1)%2);
	}
	
	@Override
	public Move findBestMove(GameBoard board){
		int[][] empty = {
				{-1, -1, -1},
				{-1, -1, -1},
				{-1, -1, -1}
		};
		if(Arrays.deepEquals(board.getFields(), empty)){
			return new Move(1, 1);
		}
		return super.findBestMove(board);
	}
	
}
