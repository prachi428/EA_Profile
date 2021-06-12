
package proj1;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

//extend token??
public class Ball extends Token implements Serializable{
	private int value;	
	private transient Text text;
	private transient Circle c;

	
	/**
	 * 
	 * @param x Location of the token along x-axis.
	 * @param y Location of the token along y-axis.
	 * @param value The value by which snake's length.
	 */
	public Ball(double x, double y, int value){
		super(x,y);
		this.value = value;
		text = new Text(Integer.toString(value));
		text.setStroke(Color.BLACK);
		c = new Circle(x,y,7.5f);
//		c.setRadius(7.5f);
		c.setFill(Color.WHITE);
		this.getChildren().addAll(c,text);
	}
	
	/**
	 * 
	 * @return Value of the ball
	 */
	public int getVal() {
		return value;
	}
	
	/***
	 * resets Value of the ball
	 * @param i
	 */
	public void setVal(int i) {
		value=i;
	}
	

	/**
	 * Overrides Equals method in object class.
	 * Equal if x and y translate properties are equal
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj.getClass() == this.getClass()) {
			Ball b = (Ball)obj;
			if(getTranslateX() == b.getTranslateX() && getTranslateY() == b.getTranslateY()) {
				return true;
			}
		}
		return false;
	}
	

}

