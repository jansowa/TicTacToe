package game;

import interfaces.AI;
import interfaces.OldBoard;
import interfaces.OldGameDAO;
import interfaces.GameView;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfiguration {
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
