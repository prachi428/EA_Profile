package proj1;

import javafx.scene.layout.Pane;

public class GameLoop implements Runnable{

	Pane root = new Pane();
	Pane mainroot = new Pane();
	Snake snake ;
	
	public GameLoop(Pane root, Pane mainroot, Snake snake) {
		// TODO Auto-generated constructor stub
		this.root = root;
		this.mainroot = mainroot;
		this.snake = snake;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
