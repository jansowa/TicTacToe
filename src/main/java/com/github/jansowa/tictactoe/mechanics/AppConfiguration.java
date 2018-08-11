package com.github.jansowa.tictactoe.game;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.jansowa.tictactoe")
public class AppConfiguration {
	/*@Bean
	public TicTacToeBoard board(){
		return new TicTacToeBoard();
	}*/
	/*@Bean
	public Board board(){
		return new SimpleBoard();
	}
	
	@Bean
	public GameView view(){
		return new SimpleGameView(board());
	}
	
	@Bean
	public AI ai(){
		return new BrutalAI(board());
	}
	
	@Bean
	public GameDAO gameDAO(){
		return new SimpleGameDAO(board());
	}
	
	@Bean
	public GameApp gameApp(){
		return new GameApp(view(), board(), ai(), gameDAO());
	}*/
}
