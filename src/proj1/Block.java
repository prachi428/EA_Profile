package proj1;

//package Project1;


import java.io.Serializable;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
/**
* Blocks that the snake destroys to score
* @author prachi
*
*/
public class Block extends StackPane implements Serializable{

   private double x;  
	 private double y;
   private transient Rectangle r;
   private boolean visible;
	 private int value;
   private transient Text text;
   
   double getx() {
  	 return x;
   }
   
   double gety() {
  	 return y;
   }
   
   int getv() {
  	 return value;
   }
   
   void setv(int i) {
	  	 value=i;
	   }
   
   /**
    * 
    * @param x Location of the token along x-axis.
    * @param y Location of the token along y-axis.
    * @param val Number of balls needed to break the block
    */
   Block(double x, double y, int val ) 
   {
  	 this.x=x;
  	 this.y = y;
  	 r = new Rectangle(x, y, 100, 100);
  	 text = new Text(Integer.toString(val));
  	 text.setStroke(Color.BLACK);
  	 value=val;
  	 r.setFill(Color.RED);
		 r.setArcHeight(15);
		 r.setArcWidth(15);
		 this.getChildren().addAll(r,text);
	 	 this.setTranslateX(x);
	 	 this.setTranslateY(y);
	 	 visible=true;
   }
   
   /**
    * 
    * @return Boolean value depending on whether the block is there on the screen or not.
    */
   boolean getVis() {
  	 return visible;
   }

   /**
    * Implements downward motion of the blocks.Also, sets the visibilty of the blocks so
    * that they are visible when the loop is complete.
    */
   void move() {
  	 if(visible==true)
  	 {this.setTranslateY(this.getTranslateY()+2);
  	 y=getTranslateY()+2;}
  	 
  	 if(visible==false)
  	 { y=y+2;}
  	 
  	 if(y>4500)
  	 {
  		 value=1+(int)(Math.random()*5);
  		 y=0;
  		 text = new Text(Integer.toString(value));
 			 this.getChildren().remove(1);
 			 this.getChildren().add(1,text);
  		 if(visible==false){
  			 visible=true;
  			 this.setVisible(visible);
  		 }
  		 this.setTranslateX(x);
  	 	 this.setTranslateY(y);
  	 }
  	 
   }

   /**
    * sets visibility of the StackPane and sets visibility field
    */
   void setVisibilityFalse() {
  	 visible=false;
  	 this.setVisible(visible);
   }

   
   public void setY(int y)
   {
  	 this.y=y;
   }

   /**
    * Checks whether the snake is touching the block and decrements the block's value accordingly.
    * @param head The first Ball of the Snake
    * @return boolean value indicating if the snake touches the block.
    */
   boolean checkCollision(Ball head) {
  	 if(head != null && this.getBoundsInParent().intersects(head.getBoundsInParent())) {
  		 value--;
 			 text = new Text(Integer.toString(value));
 			 this.getChildren().remove(1);
 			 this.getChildren().add(1,text);
 			 System.out.println("true");
// 			 this.setTranslateY(getTranslateY()-1);
  		 return true;
  		 
  	 }
  		 
  	 return false;
   }
   
   /**
    * Overrides equals method in object class.
    * Two objects equal if runtime class type and coordinates are same
    */
   @Override
  public boolean equals(Object obj) {
  	// TODO Auto-generated method stub
  	 if(obj.getClass()==this.getClass()) {
  		 Block b = (Block)obj;
  		 if(b.getTranslateX()==getTranslateX() && b.getTranslateY()==getTranslateY()) {
  			return true;
  		 }
  		 
  	 }
  	 return false;
  	
  }
   

}