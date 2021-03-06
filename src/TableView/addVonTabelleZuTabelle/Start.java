package TableView.addVonTabelleZuTabelle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Orginal
// https://stevenschwenke.de/javaFXSeriesThreadingIssues

public class Start extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/tableToTableTransition.fxml"));


        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Tabelle zu Tabelle verschieben");
        stage.setScene(scene);
        stage.show();
    }
}
