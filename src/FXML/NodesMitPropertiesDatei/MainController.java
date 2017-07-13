package FXML.NodesMitPropertiesDatei;

import com.example.javafx.choice.ChoiceCell;
import com.example.javafx.choice.ChoiceModel;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class MainController implements Initializable {

	FxmlMvcPatternDemo fxmlMvcPatternDemo;

	@FXML private TextField textField;
	
	@FXML private void button(ActionEvent event) {
		if(textField.getText().isEmpty()){
			textField.setText(fxmlMvcPatternDemo.getResourceBundle().getString("textfieldText"));
		}else {
			textField.setText("");
		}
		
	}
	
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    	
    }

	public void setMainStage(FxmlMvcPatternDemo fxmlMvcPatternDemo) {
		this.fxmlMvcPatternDemo = fxmlMvcPatternDemo;
		System.out.println(fxmlMvcPatternDemo.getResourceBundle().getString("test"));

	}

}
