package proj1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Shield extends Token{
	//add timer
	private transient Image im;
	
	/**
	 * 
	 * @param x Location of the token along x-axis.
	 * @param y Location of the token along y-axis.
	 */
	public Shield(double x, double y) {
		// TODO Auto-generated constructor stub
		super(x,y);
		im = new Image("/shield (1).png");
		ImageView view = new ImageView(im);
		view.setFitWidth(40);
		view.setPreserveRatio(true);
		this.getChildren().add(view);
	}

	
}
