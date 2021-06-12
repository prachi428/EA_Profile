package proj1;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle implements Serializable {

    private double x;  
	 private double y;
	 private double height;
    private boolean visible;

    /**
     * 
     * @param x  location of Token along x-axis.
     * @param y location of Token along y-axis.
     * @param height length of the wall
     */
    Wall(double x, double y,double height) 
    {
   	 this.x=x;
   	 this.y=y;
   	 this.height = height;
   	 visible=true;
   	 
   	 this.setFill(new Color(Math.random(),Math.random(),Math.random(),1));
		 this.setHeight(height);
		 this.setWidth(3);
	 	 this.setTranslateX(x);
	 	 this.setTranslateY(y);
		 
   }
    
    /**
     * Moves the wall on the screen to make a loop.
     */
    void move() {
   	 
   	 if(visible==true)
   	 {this.setTranslateY(this.getTranslateY()+2);
   	
   	 y=getTranslateY()+2;}
   	 
   	 if(visible==false){ y=y+2;}
   	 if(y>4500){
   		 y=0;
   		 if(visible==false){
   			 visible=true;
   			 this.setVisible(visible);
   		 }
   		 this.setTranslateX(x);
   	 	 this.setTranslateY(y);
   	 }
   	 
    }
    
  
    /**
     *Checks whether snake touches the Token.
	  * @param The first Ball of snake as input.
	  * @return true or false depending on whether it intersects with the token or not.
     */
    boolean checkCollision(Ball head) {
   	 if(head!= null && this.getBoundsInParent().intersects(head.getBoundsInParent())) {

   		 return true;
   		 
   	 }
   		 
   	 return false;
    }
    
    @Override
   public boolean equals(Object obj) {
   	 if(obj.getClass()==this.getClass()) {
   		 Wall b = (Wall)obj;
   		 if(b.getTranslateX()==getTranslateX() && b.getTranslateY()==getTranslateY()) {
   			 return true;
   		 }
   		 
   	 }
   	 return false;
   	
   }
    
    public double getx()
    {
    	return x;
    }

    
    public double gety()
    {
    	return y;
    }
    
    
    public void setx(double x)
    {
    	this.x=x;
    }
    
    
    public void sety(double y)
    {
    	this.y=y;
    }
    
    public boolean getVis()
    {
    	return visible;
    }
    
    
    public void setVis(boolean v)
    {
    	visible=v;
    }
    
    public double getLength() {
   	 return height;
    }


}