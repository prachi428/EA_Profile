package proj1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class lpcontroller {

   @FXML
   private Button mainpagebutton;

    public void initialize() {
        // TODO
    }

    @FXML
    private void handlemainpageButtonAction(ActionEvent event) throws Exception 
    {
    	mainpage m=new mainpage();
    	m.start(new Stage());
    	
    }
}