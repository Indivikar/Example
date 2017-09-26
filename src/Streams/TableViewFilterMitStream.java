package Streams;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewFilterMitStream extends Application {

    private TableView<Person> table = new TableView<Person>();
    private ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("Jacob", "Williams", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Emma", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Brown", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
    private ObservableList<Person> filteredItems = FXCollections.observableArrayList();

    private TextField textField;
    private ComboBox<String> comboBoxLastName;
    private ComboBox<String> comboBoxFirstName;
    private ObservableList<String> optionsFirstName = FXCollections.observableArrayList();
    private ObservableList<String> optionsLastName = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);

        
        
        final Label labelFilter = new Label("Filter");
        labelFilter.setFont(new Font("Arial", 20));
        
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        table.setItems(filteredItems);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        textField = new TextField();
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
        	filtern();
        });

        comboBoxFirstName = new ComboBox<>(optionsFirstName);
        comboBoxFirstName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				filtern();
			}
        });

        comboBoxLastName = new ComboBox<>(optionsLastName);
        comboBoxLastName.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				filtern();
			}
        });

        setItems();
        
        final HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(textField, comboBoxFirstName, comboBoxLastName);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(labelFilter, hbox, label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    private void filtern() {
    	
	    	List<Person> filteredList = data.stream()
//			        .sorted()
			        .filter(string -> string.getFirstName().toLowerCase().contains(textField.getText().toLowerCase())
			        		|| string.getLastName().toLowerCase().contains(textField.getText().toLowerCase())
			        		|| string.getEmail().toLowerCase().contains(textField.getText().toLowerCase()))
//			        .filter(string -> string.getLastName().toLowerCase().contains(textField.getText().toLowerCase()))
//			        .filter(string -> string.getEmail().toLowerCase().contains(textField.getText().toLowerCase()))
			        .filter(string -> isFirsName(string.getFirstName(), comboBoxFirstName.getSelectionModel().getSelectedItem()))
			        .filter(string -> isLastName(string.getLastName(), comboBoxLastName.getSelectionModel().getSelectedItem()))
			        .collect(Collectors.toList());
	        filteredItems.clear();
	        filteredItems.addAll(filteredList);
    	
	}

    private boolean isFirsName(String string, String comboBoxItem){
    	
    	if(comboBoxItem == null || comboBoxItem.equals("Alle")){
    		return true;
    	}
    	
    	if(string.toLowerCase().contains(comboBoxItem.toLowerCase())){
    		return true;
    	}
    	
		return false;    	
    }
    
    private boolean isLastName(String string, String comboBoxItem){
    	
    	if(comboBoxItem == null || comboBoxItem.equals("Alle")){
    		return true;
    	}
    	
    	if(string.toLowerCase().contains(comboBoxItem.toLowerCase())){
    		return true;
    	}
    	
		return false;    	
    }
    
    private void setItems() {
    	
    		// Set TableView Items
	    	List<Person> filteredList = data.stream()
			        .collect(Collectors.toList());
	        filteredItems.clear();
	        filteredItems.addAll(filteredList);
    	
	        // Set ComboBoxFirstName Items
	        Set<String> firstNameDerPersonen = data.stream()	        		
	                .map(Person::getFirstName)
	                .sorted()
	                .collect(Collectors.toSet());
	        optionsFirstName.clear();
	        optionsFirstName.add("Alle");
	        optionsFirstName.addAll(firstNameDerPersonen);
	        comboBoxFirstName.getSelectionModel().select(0);
	        
	        
	        // Set ComboBoxLastName Items
	        Set<String> lastNameDerPersonen = data.stream()	        		
	                .map(Person::getLastName)
	                .sorted()
	                .collect(Collectors.toSet());
	        optionsLastName.clear();
	        optionsLastName.add("Alle");
	        optionsLastName.addAll(lastNameDerPersonen);
	        comboBoxLastName.getSelectionModel().select(0);
	}
    
    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}