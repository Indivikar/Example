package Streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ComboBox.combobox.ComboBox;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ListViewFilterMitStreams extends Application  {

	ObservableList<String> items = FXCollections.observableArrayList();
	ObservableList<String> filteredItems = FXCollections.observableArrayList();

	TextField textfield = new TextField();
	ChoiceBox<String> box = new ChoiceBox<>(FXCollections.observableArrayList("1", "2", "3"));
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ListView Experiment 1");

        items.add("Item 2");
        items.add("Ite 3");        
        items.add("Item 3");
               
        ListView<String> listView = new ListView<>();

    	List<String> sortedList = items.stream()
		        .sorted().collect(Collectors.toList());
        	filteredItems.clear();
        	filteredItems.addAll(sortedList);
        
        listView.setItems(filteredItems);
       
        
        
        box.getSelectionModel().selectedItemProperty().addListener( 
        		(ObservableValue<? extends String> observable, String oldValue, String newValue) -> filtern() 
        );
               
        textfield.textProperty().addListener((observable, oldValue, newValue) -> {
        	filtern();
        });
        
        VBox hbox = new VBox(textfield, box, listView);

        Scene scene = new Scene(hbox, 300, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private void filtern() {
    	List<String> filteredList = items.stream()
		        .sorted()
		        .filter(string -> string.toLowerCase().contains(textfield.getText().toLowerCase()))
		        .filter(string -> string.toLowerCase().contains(box.getSelectionModel().getSelectedItem().toLowerCase()))
		        .collect(Collectors.toList());
        	filteredItems.clear();
        	filteredItems.addAll(filteredList);

	}
    

    public static void main(String[] args) {
        Application.launch(args);
    }
}
