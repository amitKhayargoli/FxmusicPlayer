import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
          Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("music.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Music");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } 

        catch (IOException e) {
            e.printStackTrace(); 
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
