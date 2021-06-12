package proj1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class leaderboardpage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage leaderstage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("proj1/ldpage.fxml"));
        leaderstage.setTitle("Leader Board");
        leaderstage.setScene(new Scene(root));
        leaderstage.show();
    }
    
}
