package proj1;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class instructionpage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage insstage) throws Exception
    {
    	 Parent root = FXMLLoader.load(getClass().getResource("proj1/inspage.fxml"));
         insstage.setTitle("Instructions");
         insstage.setScene(new Scene(root));
         insstage.show();
    }
    
    
    
}
