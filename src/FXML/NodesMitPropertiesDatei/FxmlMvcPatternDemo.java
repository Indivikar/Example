package FXML.NodesMitPropertiesDatei;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlMvcPatternDemo extends Application {

	ResourceBundle resourceBundle;
	MainController mainController;

    public static void main(String[] args) throws ClassNotFoundException
    {
        Application.launch(FxmlMvcPatternDemo.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {

    	FXMLLoader loader = new FXMLLoader(
    			            FxmlMvcPatternDemo.class.getResource("MainView.fxml"),
    			            resourceBundle = ResourceBundle.getBundle(FxmlMvcPatternDemo.class.getPackage().getName()+".MainView")/*properties file*/
    			);


		Parent root = loader.load();

		mainController = loader.getController();
		mainController.setMainStage(this);

        stage.setScene(new Scene(root));
        stage.show();
    }

    // Getter
	public final ResourceBundle getResourceBundle() {return resourceBundle;}

	// Setter
	public final void setResourceBundle(ResourceBundle resourceBundle) {this.resourceBundle = resourceBundle;}
}
