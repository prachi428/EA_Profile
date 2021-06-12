package proj1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class mpcontroller {

   @FXML
   private Button startbutton;

   @FXML
   private Button resumebutton;
   
   @FXML
   private Button instructionbutton;
   
   @FXML
   private Button leaderboardbutton;
   
   @FXML
   private Button exitbutton;
   
   
    public void initialize() {
        // TODO
    }

    @FXML
    private void handlestartButtonAction(ActionEvent event) throws Exception 
    {
    	GameBoard g=new GameBoard();
    	g.start(new Stage());
    }
    
    @FXML
    private void handleresumeButtonAction(ActionEvent event) 
    {
    	
    	
    	
    }
    
    @FXML
    private void handleinstructionButtonAction(ActionEvent event) throws Exception  
    {
            instructionpage i=new instructionpage();
            i.start(new Stage());
    }
    
    @FXML
    private void handleleaderboardButtonAction(ActionEvent event) throws Exception
    {
    	    leaderboardpage l =new leaderboardpage();
            l.start(new Stage());
    }
    
    @FXML
    private void handleexitButtonAction(ActionEvent event) {
    	
    	Stage primarystage = (Stage)(exitbutton.getScene().getWindow());
    	
    	primarystage.close();
    	
    }
    
   
}