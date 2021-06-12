package proj1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static proj1.GameBoard.deserializewalls;

public class Bonus extends Application{

	
	private int score=0;
	private Label dispscore = new Label("Score: "+Integer.toString(score));
	private long ti;
	private long t_start = System.currentTimeMillis();
	private Label timee = new Label("Timer: "+Long.toString(ti)); 
	private static Pane root;
	private final int height = 600;
	private final int width = 500;
	private Button pause;
	private Snake t;
	private ArrayList<Block> blocks;
	private ArrayList<Token> to;
	private static ArrayList<Wall> w;
	private boolean col;
	
	/**
	 * resets the board and assigns blocks, tokens, walls and snake their positions
	 */
	private void setBoard2() {
		blocks  = new ArrayList<Block>();
		to  = new ArrayList<Token>();
		w = new ArrayList<>();
		blocks.add(new Block(0,0,3));
		blocks.add(new Block(320,-575,2));
		for(int i = 0;i<5;i++) {
			blocks.add(new Block(i*100, -1775,(int)(Math.ceil(5*Math.random()))));
		}
		
//		blocks.add(new Block(0,0,2));
		blocks.add(new Block(200,-100,5));
		blocks.add(new Block(0,-450,12));
		blocks.add(new Block(100,-450,25));
		blocks.add(new Block(400,-750,42));
		blocks.add(new Block(200,-750,50));
		for(int i = 0;i<5;i++) {
			blocks.add(new Block(i*100, -1350,(int)(Math.ceil(5*Math.random()))));
		}
		blocks.add(new Block(400,-2450,50));
		blocks.add(new Block(300,-2450,50));
		blocks.add(new Block(200,-2450,50));
		for(int i = 0;i<5;i++) {
			blocks.add(new Block(i*100, -2900,(int)(Math.ceil(5*Math.random()))));
		}
		blocks.add(new Block(120,-3500,25));
		blocks.add(new Block(20,-3500,25));
		blocks.add(new Block(20,-3700,25));
		blocks.add(new Block(20,-3200,25));
		blocks.add(new Block(380,-3800,49));
		w.add(new Wall(400,-2550,100));
		w.add(new Wall(300,-2520,70));
		w.add(new Wall(400,-870,120));
		w.add(new Wall(20,-3600,140));
		w.add(new Wall(120,-3600,110));
		w.add(new Wall(200,-2800,120));
		w.add(new Wall(300,-2800,240));
		w.add(new Wall(150,-3600,110));
		w.add(new Wall(400,-870,120));
		w.add(new Wall(200,-870,120));
		w.add(new Wall(300,-870,120));
		w.add(new Wall(500,-870,120));
		w.add(new Wall(400,-1470,120));
		w.add(new Wall(200,-1470,100));
		to.add(new Ball(450,-2580,5));
		to.add(new Ball(250,-2920,3));
		to.add(new Ball(300,-1490,5));
		to.add(new Ball(400,-880,2));
		to.add(new Ball(120,-1380,1));
		to.add(new Ball(450,-2280,5));
		to.add(new Ball(450,-400,1));
		to.add(new Ball(500,-540,3));
//		to.add(new Magnet(330,-307));
//		to.add(new Magnet(450,-840));
//		to.add(new Shield(50,-50));
//		to.add(new Shield(340,-3600));
//		to.add(new DestroyBlock(220,-2450));
//		to.add(new DestroyBlock(345,-695));
		
		root.getChildren().addAll(blocks);
		root.getChildren().addAll(w);
		root.getChildren().addAll(to);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		root = new Pane();
		pause = new Button("||");
		root.getChildren().add(pause);
		pause.setTranslateX(0);
		pause.setTranslateY(0);
		root.getChildren().add(dispscore);
		dispscore.setTranslateX(width/2);
		dispscore.setTranslateY(0);
	    dispscore.setTextFill(Color.AQUA);
	    root.getChildren().add(timee);
		timee.setTranslateX(width-70);
		timee.setTranslateY(0);
		timee.setTextFill(Color.AQUA);

		deserializeSnake();
	    if(t==null) {
	    	setBoard2();
	    	t = new Snake(width/2, 15,root);
	    	score = 0;
	    }else {
	    	deserializetokens();
			deserializeblocks();
			deserializewalls();
			
			root.getChildren().addAll(blocks);	
			root.getChildren().addAll(to);
			root.getChildren().addAll(w);
	    }
	    
	    Scene scene=new Scene(root,width,height);
		scene.setOnKeyPressed(e -> {
	    	 switch (e.getCode()) {
		    	 case RIGHT:
		    		 if(!col && t.getHead()!=null && t.getHead().getTranslateX()+8<width) {
		    			 t.moveRight();
		    			 
		    		 }
		    	 break;
		    	 case LEFT:
		    		 if(!col && t.getHead()!=null && t.getHead().getTranslateX()-7>0) {
		    			 t.moveLeft();
		    			 
		    		 }
		    	 }	
		     });

		
	}

	private void deserializeSnake() {
		try {

			FileInputStream fileIn = new FileInputStream("snake.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			int object = (int)in.readObject();
			boolean s = (boolean)in.readObject();
			if(object!=0) {
				t = new Snake(width/2,15,root,object,s);
			}
			in.close();
			fileIn.close();


		}
		catch (IOException ex) {
			System.out.println("IOException is caught");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("IOException is caught");
		}
	}

	/**
	 * serializes blocks
	 */
	public void serializeblocks() {
		try {
	        FileOutputStream fileOut = new FileOutputStream("blocks.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        for (int i = 0; i < blocks.size(); i++) 
	        {
	            out.writeObject(blocks.get(i));
	        }
	        out.close();
	        fileOut.close();
		}
	   catch (IOException ex) {System.out.println("IOException is caught"); } 
	        
	}
	
	/**
	 * deserialises blocks
	 */
	public void deserializeblocks() {
		try{
			blocks = new ArrayList<>();
			FileInputStream file = new FileInputStream("blocks.txt");
			ObjectInputStream in = new ObjectInputStream(file); 
			Object object=in.readObject();
      
			while (object != null) {
				blocks.add(new Block(((Block)object).getx(),((Block)object).gety(),((Block)object).getv()));
				object = in.readObject();
        	}
         
			in.close(); 
			file.close();
		} 
		catch(IOException ex){ /* System.out.println("IOException is caught");*/ }
		catch(ClassNotFoundException ex)  { System.out.println("ClassNotFoundException is caught");  } 
	}
	
	
	
	/**
	 * serializes tokens
	 */
	public void serializetokens() {
		try {
	        FileOutputStream fileOut = new FileOutputStream("tokens.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        for (int i = 0; i < to.size(); i++) 
	        {
	            out.writeObject(to.get(i));
	        }
	        out.close();
	        fileOut.close();
		}
	   catch (IOException ex) {System.out.println("IOException is caught"); } 
	        
	}
	
	/**
	 * deserializes tokens
	 */
	public void deserializetokens() {
		try{
			to = new ArrayList<>();
        	FileInputStream file = new FileInputStream("tokens.txt");
        	ObjectInputStream in = new ObjectInputStream(file); 
        	Object object=in.readObject();
      
         	while (object != null) {
         		System.err.println(object.getClass().toString());
        	 	if(object.getClass()== Ball.class) {
        	 		
        		 	to.add(new Ball(((Ball)object).getx(),((Ball)object).gety(),((Ball)object).getVal()));
        	 	}else if(object.getClass()== Magnet.class) {
        		 	to.add(new Magnet(((Magnet)object).getx(),((Magnet)object).gety()));
        	 	}else if(object.getClass()== Shield.class) {
        		 	to.add(new Shield(((Shield)object).getx(),((Shield)object).gety()));
        	 	}else {
        		 	to.add(new DestroyBlock(((DestroyBlock)object).getx(),((DestroyBlock)object).gety()));
        	 	}
        	 object = in.readObject();
         	}
         
         	in.close(); 
        	file.close();
    	} 
		catch(IOException ex){ 
//			System.out.println("IOException is caught"); 
		}
		catch(ClassNotFoundException ex)  { System.out.println("ClassNotFoundException is caught");  } 
	}
	
	/**
	 * serializes walls
	 */
	public static void serializewalls()
	{
		try {
	        FileOutputStream fileOut = new FileOutputStream("w.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//	        for (int i = 0; i < blocks.size(); i++) 
//	        {
//	            out.writeObject(blocks.get(i));
//	        }
	        for (int i = 0; i < w.size(); i++) 
		        {
		            out.writeObject(w.get(i));
		        }
	        
	        
	        out.close();
	        fileOut.close();
		}
	   catch (IOException ex) {System.out.println("IOException is caught"); } 
	        
	}


	

}
