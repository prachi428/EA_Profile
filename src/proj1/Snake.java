package proj1;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Snake implements Serializable{
	private transient Ball head;
	private int length;
	private boolean isAlive = true;
	private transient ArrayList<Ball> snake = new ArrayList<Ball>();
	private double x;
	private  double y;
	private boolean isShielded;
	
	
	public boolean isDead() {
		return !isAlive;
	}
	
//	int screenWidth;
	
	public int getSize() {
		return length;
	}
	
	/**
	 * 
	 * @param x Location of Snake along x-axis
	 * @param y Location of Snake's last Ball along v-axis
	 * @param g The parent Node on the scene
	 */
	public Snake(double x, double y, Pane g) {
		// TODO Auto-generated constructor stub
		length = 10;
		for (int i = 0; i <length; i++) {
			snake.add(i,new Ball(x,300+15*i,1));
			g.getChildren().add(snake.get(i));
//			g2.getChildren().add(snake.get(i));
		}
		isShielded=false;
		head = snake.get(0);
		this.x = x;
		this.y = 450;
	}
	
	
	/**
	 * 
	 * @param x Location of Snake along x-axis
	 * @param y Location of Snake along y-axis
	 * @param g The parent Node on the scene.
	 * @param length Length of the snake arraylist.
	 * @param shield Shield activation
	 */
	public Snake(double x, double y, Pane g, int length, boolean shield) {
		// TODO Auto-generated constructor stub
		this.length = length;
		for (int i = 0; i <length; i++) {
			snake.add(i,new Ball(x,300+15*i,1));
			g.getChildren().add(snake.get(i));
//			g2.getChildren().add(snake.get(i));
			System.out.println(i);
			
		}
		isShielded=shield;
		if(length!=0) {
			head = snake.get(0);
		}
		this.x = x;
		this.y = 300+15*length;

	}
	
	
	/**
	 * 
	 * @return the x translate property of head of the snake.
	 */
	double getx() {
		return x;
	}
	
	
	Ball getHead() {
		if(length>0) {
			return head;
		}else {
			return null;
		}
	}
	
	/**
	 * Moves the snake towards right
	 */
	public void moveRight() {
		move(7);
		
	}
	
	/**
	 * Moves the snake towards left
	 */
	public void moveLeft() {
		
		move(-7);
	}
	
	/**
	 * Moves the snake left or right depending on the sign of vel.
	 * @param vel change on x coordinate of the snake.
	 */
	private void move(int vel) {
		
		for(final Ball ball : snake) {
			ball.setTranslateX(ball.getTranslateX()+vel);
			x = x+vel;
				
			
		}
		
		
	}
	
	
	/***
	 * 
	 * @return The length of the snake
	 */
	public int getLength() {
		return length;
	}
	
	
	
	
//	public boolean isCollides(Node o) {
//		return head.intersects(o.getBoundsInParent());
//	}
	/**
	 * 
	 * @param l the absolute value indicates change in length 
	 * sign determines increment and decrement.
	 * @param g The parent that is added to the scene
	 */
	void changeLength(int l,Pane g) {
		if(l>0) {
			
			for (int i = 0; i < l; i++) {
				Ball b = new Ball(snake.get(0).getTranslateX(),y,1);
				snake.add(b);
				g.getChildren().add(b);
				y+=15;
				length+=1;
			}
		}else if(length>0){
			g.getChildren().remove(snake.get(length-1));
			snake.remove(length-1);
//			y=y+15;
			y=y-15;
			length--;
			if(length==0) {
				isAlive = false;
			}
//			length--;
			
		}
		
	}
	
	/**
	 * Whether snake is shielded or not.
	 * @return
	 */
	boolean Shielded() {
		return isShielded;
	}
	
	/**
	 * 
	 * @param b Sted shielded parameter to turn on the shield property
	 */
	void setShield(boolean b) {
		isShielded=b;
	}

}