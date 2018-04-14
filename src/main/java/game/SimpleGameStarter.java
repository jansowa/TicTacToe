package game;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleGameStarter {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		/*ApplicationContext context = new ClassPathXmlApplicationContext("simpleContext.xml");*/
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		GameApp simpleGameApp = context.getBean("gameApp", game.GameApp.class);
		simpleGameApp.playGame();
	}
}