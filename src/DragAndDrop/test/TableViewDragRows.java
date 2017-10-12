package DragAndDrop.test;

import java.util.function.Function;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TableViewDragRows extends Application {

    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    @Override
    public void start(Stage primaryStage) {
        TableView<Person> tableView1 = new TableView<>();
        tableView1.getColumns().add(createCol("First Name", Person::firstNameProperty, 150));
        tableView1.getColumns().add(createCol("Last Name", Person::lastNameProperty, 150));
        tableView1.getColumns().add(createCol("Email", Person::emailProperty, 200));

        tableView1.getItems().addAll(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
        
        TableView<Person> tableView2 = new TableView<>();
        tableView2.getColumns().add(createCol("First Name", Person::firstNameProperty, 150));
        tableView2.getColumns().add(createCol("Last Name", Person::lastNameProperty, 150));
        tableView2.getColumns().add(createCol("Email", Person::emailProperty, 200));

        tableView2.getItems().addAll(
            new Person("J", "S", "jacob.smith@example.com"),
            new Person("I", "J", "isabella.johnson@example.com"),
            new Person("E", "W", "ethan.williams@example.com"),
            new Person("E", "J", "emma.jones@example.com"),
            new Person("M", "B", "michael.brown@example.com")
        );

//        setupGestureTarget(tableView2);
        
        	// boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben
        	dragAndDropRowsBetweenTwoTables(tableView1, tableView2, false, false, false, false);
        	dragAndDropRowsBetweenTwoTables(tableView2, tableView1, false, false, true, true);
        
//        tableView1.getItems().addListener(new ListChangeListener<Person>(){
//
//			@Override
//			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Person> c) {
//				if(tableView1.getItems().isEmpty()){
//					tableView1.getItems().add(new Person("", "", ""));					
//				} 		
//			}
//        	
//        });
        
//        tableView1.setRowFactory(tv -> {
//            TableRow<Person> row = new TableRow<>();
//
//		    row.hoverProperty().addListener((observable) -> {
//		        final Person person = row.getItem();
//
//		        if (row.isHover() && person != null) {
//		        	row.setCursor(Cursor.CLOSED_HAND);
//		        } else {
//		        	row.setCursor(Cursor.DEFAULT);
//		        }
//		    });
//
//            row.setOnDragDetected(event -> {
//                if (! row.isEmpty()) {
//                    Integer index = row.getIndex();
//                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
//                    db.setDragView(row.snapshot(null, null));
//                    ClipboardContent cc = new ClipboardContent();
//                    cc.put(SERIALIZED_MIME_TYPE, index);
//                    db.setContent(cc);
//                    event.consume();
//                }
//            });
//
//            row.setOnDragOver(event -> {
//                Dragboard db = event.getDragboard();
//                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
////                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
//                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                        event.consume();
////                    }
//                }
//            });
//
//            row.setOnDragDropped(event -> {
//                Dragboard db = event.getDragboard();
//                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//                	TableView<Person> targetTableView;
//                	
//                	if(row.getTableView().isFocused()){               		
//                		targetTableView = tableView1;
//                		
//                    } else {
//                    	targetTableView = tableView2;
//					}
//                	
//                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
//                    Person draggedPerson = targetTableView.getItems().remove(draggedIndex);
//
//                    int dropIndex ;
//
//                    if (row.isEmpty()) {
//                        dropIndex = targetTableView.getItems().size() ;
//                    } else {
//                        dropIndex = row.getIndex();
//                    }
//                    
////                    if(tableView1.getItems().size() == 1) {
////                    	System.out.println("dummy");
////    					String firstName = tableView1.getItems().get(0).getFirstName();
////    					String lastName = tableView1.getItems().get(0).getLastName();
////    					String eMail = tableView1.getItems().get(0).getEmail();
////   					
////
////    				}
//                    
//                    if(dropIndex >= tableView1.getItems().size()){
//                    	dropIndex = tableView1.getItems().size();
//                    }
//                    System.out.println("dropIndex: " + dropIndex);
//                    System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + tableView1.getItems().size());
//                    
//                    
//                    tableView1.getItems().add(dropIndex, draggedPerson);
//                    dummyHandler(tableView1);
//                    dummyHandler(tableView2);
//                    
//                    event.setDropCompleted(true);
//                    tableView1.getSelectionModel().select(dropIndex);
//                    event.consume();
//                }
//            });
//
//            return row ;
//        });

        
//        tableView2.setRowFactory(tv -> {
//            TableRow<Person> row = new TableRow<>();
//
//		    row.hoverProperty().addListener((observable) -> {
//		        final Person person = row.getItem();
//
//		        if (row.isHover() && person != null) {
//		        	row.setCursor(Cursor.CLOSED_HAND);
//		        } else {
//		        	row.setCursor(Cursor.DEFAULT);
//		        }
//		    });
//
//            row.setOnDragDetected(event -> {
//                if (! row.isEmpty()) {
//                    Integer index = row.getIndex();
//                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
//                    db.setDragView(row.snapshot(null, null));
//                    ClipboardContent cc = new ClipboardContent();
//                    cc.put(SERIALIZED_MIME_TYPE, index);
//                    db.setContent(cc);
//                    event.consume();
//                }
//            });
//
//            row.setOnDragOver(event -> {
//                Dragboard db = event.getDragboard();
//                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
////                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
//                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                        event.consume();
////                    }
//                }
//            });
//
//            row.setOnDragDropped(event -> {
//                Dragboard db = event.getDragboard();
//                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//                	
//                	TableView<Person> targetTableView;
//                	
//                	// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden
//                	if(row.getTableView().isFocused()){
//                		// sortieren
//                		targetTableView = tableView2;
//                    } else {
//                    	// neues Item 
//                    	targetTableView = tableView1;
//					}
//                	
//                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
//                    Person draggedPerson;
//                    if(true){
//                    	draggedPerson = targetTableView.getItems().remove(draggedIndex);
//                    }
//                    
//                    int dropIndex ;
//                    
//                    if (row.isEmpty()) {
//                        dropIndex = targetTableView.getItems().size() ;
////                        System.out.println("row.isEmpty(): " + dropIndex);
//                    } else {                  	
//                        dropIndex = row.getIndex();
////                        System.out.println("nicht row.isEmpty(): " + dropIndex);
//                    }
//                    
//                    // Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
//                    if(dropIndex > tableView2.getItems().size()){
//                    	dropIndex = tableView2.getItems().size();
//                    }
//                    
////                    System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + tableView2.getItems().size());
//                    
//                    tableView2.getItems().add(dropIndex, draggedPerson);
//
//                    // Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die 
//                    // DragAndDrop funktion nicht mehr
//                    dummyHandler(tableView1);
//                    dummyHandler(tableView2);
//                    
//                    event.setDropCompleted(true);
//                    tableView2.getSelectionModel().select(dropIndex);
//                    event.consume();
//                }
//            });
//
//            return row ;
//        });

        HBox hbox = new HBox(tableView1, tableView2);
        hbox.setSpacing(10);
        
        Scene scene = new Scene(hbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dragAndDropRowsBetweenTwoTables(TableView<Person> quellTable, TableView<Person> zielTable, 
    							boolean nachDropAusQuellTableRemoven, boolean darfEsDenNeuenEintragMehrmalsGeben, 
    							boolean darfQuellTableNeuenEintragAdden, boolean darfQuellTablePerDragAndDropSortiertWerden) {
		
    	quellTable.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();

		    row.hoverProperty().addListener((observable) -> {
		        final Person person = row.getItem();

		        if (row.isHover() && person != null) {
		        	row.setCursor(Cursor.CLOSED_HAND);
		        } else {
		        	row.setCursor(Cursor.DEFAULT);
		        }
		    });

            row.setOnDragDetected(event -> {
                if (! row.isEmpty()) {
                    Integer index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
//                    if (row.getIndex() != ((Integer)db.getContent(SERIALIZED_MIME_TYPE)).intValue()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
//                    }
                }
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                	
                	TableView<Person> targetTableView;
                	
                	// Soll die Tabelle sortiert oder ein neues Item hinzugefügt werden
                	if(row.getTableView().isFocused()){
                		// sortieren
                		targetTableView = quellTable;               		
                    } else {
                    	// neues Item 
                    	targetTableView = zielTable;
					}
                	
                    int draggedIndex = (Integer) db.getContent(SERIALIZED_MIME_TYPE);
                    Person draggedPerson = targetTableView.getItems().get(draggedIndex);

                    
                    int dropIndex ;
                    
                    if (row.isEmpty()) {
                        dropIndex = targetTableView.getItems().size() ;
//                        System.out.println("row.isEmpty(): " + dropIndex);
                    } else {                  	
                        dropIndex = row.getIndex();
//                        System.out.println("nicht row.isEmpty(): " + dropIndex);
                    }
                    
                    // Wenn der "dropIndex" grösser ist als der "Listen-Index" setze ihn neu, damit es kein fehler gibt
                    if(dropIndex > quellTable.getItems().size()){
                    	dropIndex = quellTable.getItems().size();
                    }
                    
//                    System.out.println("draggedIndex: " + draggedIndex + " - tableView2.getItems().size(): " + tableView2.getItems().size());
     
                    if(darfQuellTableNeuenEintragAdden || row.getTableView().isFocused() && darfQuellTablePerDragAndDropSortiertWerden){
	                    if (!gibtEsDenEintragSchon(quellTable, draggedPerson, darfEsDenNeuenEintragMehrmalsGeben) || row.getTableView().isFocused()) {
	                    	
	                    	if(nachDropAusQuellTableRemoven || row.getTableView().isFocused()){                       	
		                    	draggedPerson = targetTableView.getItems().remove(draggedIndex);
		                    }
							quellTable.getItems().add(dropIndex, draggedPerson);
	
						}
                    }

                    // Setzt  ein Dummy, wenn der TableView leer ist, wenn der Dummy nicht gesetzt wird, dann wird der "Placeholder" eingeblendet und dann geht die 
                    // DragAndDrop funktion nicht mehr
                    dummyHandler(zielTable);
                    dummyHandler(quellTable);
                    
                    event.setDropCompleted(true);
                    quellTable.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row ;
        });
	}
    
    private boolean gibtEsDenEintragSchon(TableView<Person> quellTable, Person draggedPerson, boolean darfEsDenNeuenEintragMehrmalsGeben){
    	
    	if(darfEsDenNeuenEintragMehrmalsGeben){
    		return false;
    	}
    	
    	for (int i = 0; i < quellTable.getItems().size(); i++) {
        	
        	if (quellTable.getItems().get(i).equals(draggedPerson)) {											
				return true; 
			}
		}
    	
		return false;   	
    }
    
    
    private void dummyHandler(TableView<Person> tableView1) {
        if(tableView1.getItems().size() <= 2){
            for (int i = 0; i < tableView1.getItems().size(); i++) {
				Person items = tableView1.getItems().get(i);
				System.out.println("dummy");	
				if(items.getFirstName().equals("") && items.getLastName().equals("") && items.getEmail().equals("")){
					System.out.println("remove dummy");	    						
					tableView1.getItems().remove(i);
				}
			}
        }
        
		if(tableView1.getItems().isEmpty()){
			tableView1.getItems().add(new Person("", "", ""));					
		} 
	}
    
    
    void setupGestureTarget(final TableView<Person> target){
        
        target.setOnDragOver(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                System.out.println("onDragOver");
               
                /* accept it only if it is  not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
               
                event.consume();
            }
        });

        target.setOnDragEntered(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasString()) {
                    
                }
               
                event.consume();
            }
        });

        target.setOnDragExited(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                
               
                event.consume();
            }
        });
       
        target.setOnDragDropped(new EventHandler <DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                /* data dropped */
                System.out.println("onDragDropped");
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);
               
                event.consume();
            }
        });   
    }
    
    private TableColumn<Person, String> createCol(String title,
            Function<Person, ObservableValue<String>> mapper, double size) {

        TableColumn<Person, String> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> mapper.apply(cellData.getValue()));
        col.setPrefWidth(size);

        return col ;
    }


   public class Person {
        private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
        private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
        private final StringProperty email = new SimpleStringProperty(this, "email");

        public Person(String firstName, String lastName, String email) {
            this.firstName.set(firstName);
            this.lastName.set(lastName);
            this.email.set(email);
        }

        public final StringProperty firstNameProperty() {
            return this.firstName;
        }

        public final String getFirstName() {
            return this.firstNameProperty().get();
        }

        public final void setFirstName(final String firstName) {
            this.firstNameProperty().set(firstName);
        }

        public final StringProperty lastNameProperty() {
            return this.lastName;
        }

        public final String getLastName() {
            return this.lastNameProperty().get();
        }

        public final void setLastName(final String lastName) {
            this.lastNameProperty().set(lastName);
        }

        public final StringProperty emailProperty() {
            return this.email;
        }

        public final String getEmail() {
            return this.emailProperty().get();
        }

        public final void setEmail(final String email) {
            this.emailProperty().set(email);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
