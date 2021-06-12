package proj1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class ppcontroller {

   @FXML
   private Button mainpagebutton;

   @FXML
   private Button resumebutton;
   
   @FXML
   private Button restartbutton;
   
    public void initialize() {
        // TODO
    }

    @FXML
    private void handlemainpageButtonAction(ActionEvent event) throws Exception 
    {
    	mainpage m=new mainpage();
    	m.start(new Stage());
    }
    
    @FXML
    private void handleresumeButtonAction(ActionEvent event) throws Exception 
    {
    	GameBoard g=new GameBoard();
    	g.start(new Stage());
    }
    
    @FXML
    private void handlerestartButtonAction(ActionEvent event) throws Exception 
    {
    	GameBoard g=new GameBoard();
    	g.start(new Stage());
    }
    
}