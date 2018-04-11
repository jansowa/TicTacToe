package game;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

//@Component
public class SimpleGameAppTemp {
	//@Autowired
	private static SimpleGameView view;
	private static ApplicationContext context;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		context = new ClassPathXmlApplicationContext("context.xml");
		view = context.getBean("view", SimpleGameView.class);
		//view = new SimpleGameView();
		SimpleGameAppTemp gameApp = new SimpleGameAppTemp();
		while(true){
			gameApp.view.printBoard();
			gameApp.view.takeInput();
			if(gameApp.view.board.gameStatus==-2){
				System.out.println("BAD INPUT!");
			}
			else if(gameApp.view.board.gameStatus==2){
				gameApp.view.printBoard();
				System.out.println("DRAW GAME!");
				break;
			}
			else if(gameApp.view.board.gameStatus==0){
				gameApp.view.printBoard();
				System.out.println("Player O wins!");
				break;
			}
			else if(gameApp.view.board.gameStatus==1){
				gameApp.view.printBoard();
				System.out.println("Player 1 wins!");
				break;
			}
		}
	}
}
