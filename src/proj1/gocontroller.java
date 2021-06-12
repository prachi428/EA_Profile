package proj1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class gocontroller {

	   @FXML
	   private Button mainpagebutton;
	   
	   @FXML
	   private Button restartbutton;
	   
	   @FXML
	    private void handlemainpageButtonAction(ActionEvent event) throws Exception 
	    {
	    	mainpage m=new mainpage();
	    	m.start(new Stage());
	    }
	    
	    @FXML
	    private void handlerestartButtonAction(ActionEvent event) throws Exception 
	    {
	    	GameBoard g=new GameBoard();
	    	g.start(new Stage());
	    }
}
