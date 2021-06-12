package proj1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * 
 * @author prachi
 *
 */
public class GameBoard extends Application implements Serializable{
	
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

	int select;
	
	@Override
    public void start(Stage stage) throws Exception 
    {  
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
//		sound = new Media(Main.class.getResource("s.mp3").toExternalForm());
//		MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();
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
	    dispscore.setText(Integer.toString(score));

		Scene scene=new Scene(root,width,height);
		scene.setOnKeyPressed(e -> {
			System.out.println(e.getCode());

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

		new AnimationTimer() {public void handle(long now) {
			
			
			
			if(t.getLength()==0){
				stop();
			}
			
			
			for(int i=0;i<to.size();i++){
				to.get(i).move();
				 
			 }

			
			for(int i=0;i<blocks.size();i++){
				blocks.get(i).move(); 
			}
			

			for(int i=0;i<w.size();i++){
				w.get(i).move(); 
			}
			
			
			Platform.runLater(() -> {
			for(int i=blocks.size()-1;i >= 0;i--) {
				if(blocks.get(i).getVis() && blocks.get(i).checkCollision(t.getHead())) {
					if(t.Shielded()) {
						if(t_start-ti>5000) {
							t.setShield(false);timee.setVisible(false);
						}else {
							score=score+blocks.get(i).getv()+1;
							dispscore.setText("Score: " +Integer.toString(score));
							blocks.get(i).setVisibilityFalse();
							timee.setText("Timer: "+Long.toString(t_start-ti));
							///////////////////////////////////////////////
						}
						
					}
				else{
					if(t.getLength()>1) {
						t.changeLength(-1, root);
						try {
					        Thread.sleep(100);
					    } catch (InterruptedException e) {
					        // TODO Auto-generated catch block
					        e.printStackTrace();
					    }
						
					}else {
						t.changeLength(-1, root);
						stop();
					}
					if(blocks.get(i).getv()<1) {
						
						dispscore.setText("Score: " +Integer.toString(score));
							blocks.get(i).setVisibilityFalse();
					}
				}
					return;
			}
				
				
			}
			
			for (int i = 0; i < w.size(); i++) {
				if(w.get(i).checkCollision(t.getHead())) {
					col = true;
					return;
				}
				col = false;
			}
			
			
			});
			
			stage.setOnCloseRequest( event ->
		    {
		        System.out.println("CLOSING");
		        serializeblocks();
		        serializetokens();
		        serializewalls();
		        serializeSnake();
		    });
			
			Platform.runLater(() -> {		
			for(int i=0;i<to.size();i++){
				if(to.get(i).getVis() && to.get(i).checkCollision(t.getHead())) {
					if(to.get(i).getClass()==Ball.class) {
						Ball b = (Ball)to.get(i);
						t.changeLength(b.getVal(),root);

						to.get(i).setVis(false);;
					}else if(to.get(i).getClass()==Magnet.class) {
//							continue;
							
						Magnet m = (Magnet)to.get(i);
						m.ActMagnet(to, t, root);
						to.get(i).setVis(false);;
						
					}else if(to.get(i).getClass()==Shield.class) {
						t.setShield(true);
						to.get(i).setVis(false);
						ti = System.currentTimeMillis();
					}else {
						DestroyBlock m = (DestroyBlock)to.get(i);
						to.get(i).setVis(false);
						score = m.destroyAll(blocks, t, root);
					    dispscore.setText("Score: " +Integer.toString(score));

					}
						
					
				}

				
				 
		
			}
			});
			
		
		}}.start();
		
		
		
		
//		scene.setFill(Color.BLACK);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
		stage.setTitle("game");
		stage.setScene(scene);
		stage.show();
    }

	private void chain_blocks()
	{
		
		 for(int i=0;i<5;i++)
		 {
			 blocks.add(new Block(100*i,-500,i+1));
		 }
		 
		 
	}
	
	private void setBoard()
	{
		blocks  = new ArrayList<Block>();
		to  = new ArrayList<Token>();
		chain_blocks();
		blocks.add(new Block(0,0,10));
		blocks.add(new Block(250,-300,30));
		blocks.add(new Block(240,0,40));
//		to.add(new Magnet(200,-100));
//		to.add(new DestroyBlock(150,-150));
		to.add(new Shield(150,-150));
		to.add(new Ball(50,10,5));
		to.add(new Ball(300,-300,3));
		to.add(new Ball(120,72,2));
		to.add(new Ball(433,27,3));
		
	}
	
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

	/**
	 * deserialises blocks
	 */
	public void deserializeblocks()
	{
		
	try{
		blocks = new ArrayList<>();
        FileInputStream file = new FileInputStream("blocks.txt");
        ObjectInputStream in = new ObjectInputStream(file); 
        Object object=in.readObject();
      
         while (object != null) {
        	 blocks.add(new Block(((Block)object).getx(),((Block)object).gety(),((Block)object).getv()));
        	 
        	 object = in.readObject();}
         
        in.close(); 
        file.close();
    } 
	catch(IOException ex){ /* System.out.println("IOException is caught");*/ }
	catch(ClassNotFoundException ex)  { System.out.println("ClassNotFoundException is caught");  } 
		}
	
	
	/**
	 * serializes blocks
	 */
	public void serializeblocks()
	{
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
	 * deserializes walls
	 */
	public static void deserializewalls()
	{
		
	try{
		w = new ArrayList<>();
        FileInputStream file = new FileInputStream("w.txt");
        ObjectInputStream in = new ObjectInputStream(file); 
        Object object=in.readObject();
      
         while (object != null) {
//        	 blocks.add(new Block(((Block)object).getx(),((Block)object).gety(),((Block)object).getv()));
        	 
        	 w.add(new Wall(((Wall)object).getx(),((Wall)object).gety(),100));
        	 object = in.readObject();}
         
        in.close(); 
        file.close();
    } 
	catch(IOException ex){ /* System.out.println("IOException is caught");*/ }
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
	
	
	
	/***
	 * Serializes snake
	 */
	public void serializeSnake() {
		try {
//			w = new ArrayList<Wall>();
	        FileOutputStream fileOut = new FileOutputStream("snake.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(t.getLength());
	        out.writeObject(t.Shielded());
	        out.close();
	        fileOut.close();
		}
	   catch (IOException ex) {System.out.println("IOException is caught"); }
	}
	
	
	
	/**
	 * desrializes snake
	 * @throws IndexOutOfBoundsException In case snake dies, the null value of t has to be handled.
	 */
	public void deserializeSnake() throws IndexOutOfBoundsException{
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
	 * Serializes score
	 */
	public void serializeScore() {
		try {
//			w = new ArrayList<Wall>();
	        FileOutputStream fileOut = new FileOutputStream("w.txt");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	       out.writeObject(score);
	        out.close();
	        fileOut.close();
		}
	   catch (IOException ex) {System.out.println("IOException is caught"); } 
	        
	}
	
	/**
	 * deserializes score value from the previuos game
	 */
	public void deserializeScore(){
		try {
			
	        FileInputStream fileIn = new FileInputStream("snake.txt");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        int object = (int)in.readObject(); 
        	score = object;
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

	public static void main(String[] args) {
		launch(args);
	}
	
	
}
