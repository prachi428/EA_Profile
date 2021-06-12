package proj1;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class Magnet extends Token{
	private transient Image im;
	
	/**
	 * 
	 * @param x Location of the token along x-axis.
	 * @param y Location of the token along y-axis.
	 */
	public Magnet(double x, double y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		im = new Image("magnet.png");
		ImageView view=new ImageView(im);
		view.setFitWidth(40);
		view.setPreserveRatio(true);
		this.getChildren().add(view);
	}
	
	/**
	 * Activates functionality of Magnet by removing all Balls from the screen.
	 * @param to List of tokens on the game screen.
	 * @param t Snake
	 * @param root The main pane added to scene.
	 */
	public void ActMagnet(ArrayList<Token> to, Snake t,Pane root) {
		for(int j=0;j<to.size();j++){
   		 if(to.get(j).getClass()==Ball.class && to.get(j).getVis()==true && Math.pow(Math.pow((t.getHead().getx()-to.get(j).getx()),2 )+ Math.pow((t.getHead().gety()-to.get(j).gety()),2 ), 0.5)<=300) {
   			Ball b=(Ball)(to.get(j));
			t.changeLength(b.getVal(), root);
			to.get(j).setVis(false);;
   		 }
	}
	
	}
}

