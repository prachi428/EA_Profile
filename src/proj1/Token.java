package proj1;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
/**
* 
* @author prachi shreeparna
*@version	1.0.0 Dec 2, 2018 
*
*/
public abstract class Token extends StackPane implements Serializable{

private double location_x;
private double location_y;
private boolean visible = true; 


/**
 * 
 * @param locx location of Token along x-axis.
 * @param locy location of Token along x-axis
 */
public Token(double locx, double locy) {
//	ImageView view=new ImageView(im);
	
	location_x = locx;
	location_y = locy;
	this.setTranslateX(location_x);
	this.setTranslateY(location_y);

}

/**
 * 
 * @return Location of the token along x-axis.
 */
double getx() {
	return location_x;
}

/**
 * 
 * @return Location of the token along y-axis.
 */
double gety() {
	return location_y;
}

/**
 * 
 * @return Visibility in the parent node
 */
boolean getVis() {
	return visible;
}

/**
 * Adjusts visibility of the component.
 * @param b visibility parameter.
 */
void setVis(boolean b) {
	visible = b;
	setVisible(b);
}


/**
 * makes sure the tokens that the snake has already consumed appear again.
 * Also, implements motion
 */
void move() {
	 
	 if(visible==true){
		 this.setTranslateY(this.getTranslateY()+2);
		 location_y=getTranslateY()+2;
	 }
	 
	 if(visible==false){
		 location_y=location_y+1;
	 }
	 
	 if(location_y>4500){
	 location_y=0;
	 if(visible==false){
		 visible=true;
		 this.setVisible(visible);
//		 root.getChildren().add(this);
	 }
	 this.setTranslateX(location_x);
 	 this.setTranslateY(location_y);
 }
	 
}


/**
 * Checks whether snake touches the Token.
 * @param head as input.
 * @return true or false depending on whether it intersects with the token or not.
 */
boolean checkCollision(Ball head) {
	 if(head != null && this.getBoundsInParent().intersects(head.getBoundsInParent())) {
			 System.out.println("true");
		 return true;
	 }   		 
	 return false;
}



}

