
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent; 

    public class App extends Application {
        double x, y = 0;

        public void start(Stage stage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
            Scene sc = new Scene(root, 1000, 700);
            stage.initStyle(StageStyle.UNDECORATED);
            

            
            
            
            root.setOnMousePressed(evt -> {
                x = evt.getSceneX();
                y = evt.getSceneY();
            });
            root.setOnMouseDragged(evt -> {
                stage.setX(evt.getScreenX()- x);
                stage.setY(evt.getScreenY()- y);
            });

            stage.setScene(sc);
            stage.show();
        }

            



    public static void main(String[] args) {
        Application.launch(args);
    }



}