package Vererbung;

import Vererbung.controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Start extends Application {

	// Ordner
	private final String cssFolder = "view/css/";
	private final String fxmlFolder = "view/fxml/";
	private final String imageFolder = "view/images/";

	// Kassen
	MainController mainController;

	// Stages
	private Stage primaryStage;

	@Override
	public void init() throws Exception {

	}

	@Override
    public void start(Stage stage) throws Exception {
    	this.primaryStage = stage;


//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

//        Parent root = FXMLLoader.load(FXMLDocumentController.class.getResource("Login.fxml"));

    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFolder + "MainController.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
//        stage.setTitle(programmName);
//        stage.getIcons().add(stageIcon);

//        scene.setFill(null);
//        stage.initStyle(StageStyle.TRANSPARENT);
//        scene.getStylesheets().add(Start.class.getResource("view/css/Login.css").toExternalForm());


        stage.setX(1200);
        stage.show();

        mainController = loader.getController();
        mainController.setMainStage(this);


    }

	@Override
    public void stop() throws Exception {

    }


    // Getter

	// Setter

    public static void main(String[] args) {
        launch(args);
    }

}

