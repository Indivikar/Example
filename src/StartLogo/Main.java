package StartLogo;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


	@Override
    public void start(Stage stage) throws Exception {
        try {

            Parent 	root = FXMLLoader.load(Main.class.getResource("fxml/main.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("css/main.css").toExternalForm());
            stage.setScene(scene);

            scene.setFill(null);

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setWidth(600);
            stage.setHeight(200);
            stage.show();

            // in die Mitte vom Bildschirm
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}