package proj1;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

class DestroyBlock extends Token{
	 
	private transient Image im;
	
	/**
	 * 
	 * @param x Location of the token along x-axis.
	 * @param y Location of the token along y-axis.
	 */
	public DestroyBlock(double x, double y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		im = new Image("./icons/bomb.png");
		ImageView view = new ImageView(im);
		view.setFitWidth(40);
		view.setPreserveRatio(true);
		this.getChildren().add(view);
	}
	
	/**
	 * Removes all blocks in a particular range from the parent Node
	 * @param blocks Arraylist of blocks on the screen
	 * @param t Snake
	 * @param root Parent Node
	 * @return The score made after destruction of all the blocks lying in certain range.
	 */
	public int destroyAll(ArrayList<Block> blocks, Snake t,Pane root) {
		int score = 0;
		for(int j=0;j<blocks.size();j++){
     		 if(blocks.get(j).getVis()==true && 0 <= blocks.get(j).gety() && blocks.get(j).gety() <= 600)
     		 {
     			    score=score+blocks.get(j).getv();
					    blocks.get(j).setVisibilityFalse();
     		 }
     	 }
		return score;
	}
				
}


