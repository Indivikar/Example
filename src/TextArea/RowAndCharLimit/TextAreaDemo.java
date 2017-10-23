package TextArea.RowAndCharLimit;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	public class TextAreaDemo extends Application {

		private TextField textFieldMaxRows;
		private TextField textFieldMaxCharsInARow;
		
	    @Override
	    public void start(Stage primaryStage) throws Exception {

	        Label labelMaxRows = new Label("max. Rows");
	        textFieldMaxRows = new TextField();
	        textFieldMaxRows.setEditable(false);
	        VBox vBoxMaxRows = new VBox();
	        vBoxMaxRows.getChildren().addAll(labelMaxRows, textFieldMaxRows);
	        
	        Label labelMaxCharsInARow = new Label("max. Chars in a Row");
	        textFieldMaxCharsInARow = new TextField();
	        textFieldMaxCharsInARow.setEditable(false);
	        VBox vBoxMaxCharsInARow = new VBox();
	        vBoxMaxCharsInARow.getChildren().addAll(labelMaxCharsInARow, textFieldMaxCharsInARow);

	        TextArea textArea = new TextArea();
	        textArea.setTextFormatter(createTextFormatter(3, 7));
	        
	        HBox hBox = new HBox();
	        hBox.setSpacing(10);
	        hBox.getChildren().addAll(vBoxMaxRows, vBoxMaxCharsInARow);
	        
	        VBox root = new VBox();
	        root.setPadding(new Insets(10));
	        root.setSpacing(10);
	        root.getChildren().addAll(hBox, textArea);

	        Scene scene = new Scene(root, 320, 300);

	        primaryStage.setTitle("JavaFX TextArea (o7planning.org)");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private <T> TextFormatter<T> createTextFormatter(int maxRows, int maxCharsInARow) {
	    	final IntegerProperty lines = new SimpleIntegerProperty(1);

	    	textFieldMaxRows.setText(maxRows + "");
	    	textFieldMaxCharsInARow.setText(maxCharsInARow + "");
	    	
	        return new TextFormatter<>(change -> {

	            if (change.isAdded()) {  	        	
	            	String str = change.getControlNewText().substring(0, change.getRangeStart());
	            	lines.set(countChar(str, '\n') + 1);

	                if (countChar(change.getControlNewText(), '\n') >= maxRows) {
	                    	return null;
					}
	            }
	            
	            if(lines.get() > 0){
		           	String[] part = change.getControlNewText().split("\n");
		            for (int i = lines.get() -1; i < part.length; i++) {
						System.out.println(part[i]);
						if(part[i].length() <= maxCharsInARow){
							return change;
						} else {
							return null;
						}
					}
	            }

	            
	            return change;
	        });
	    }
	    
	    public int countChar(String input, char toCount){
	        int counter = 0;
	        for(char c: input.toCharArray()){
	            if(c==toCount)
	                counter++;
	        }
	        return counter;
	    }
	    
	    public static void main(String[] args) {
	        Application.launch(args);
	    }

	}


