package proj1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverPage extends Application {

	@Override
    public void start(Stage mainstage) throws Exception 
    {
    	
        Parent root = FXMLLoader.load(getClass().getResource("proj1/gopage.fxml"));
        mainstage.setTitle("Snake Game");
        mainstage.setScene(new Scene(root));
        mainstage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
