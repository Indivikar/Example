package DokumentenBearbeitung.Word.main;


import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.AltChunkType;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HTMLEditorSample extends Application {

	@Override
	public void start(Stage stage) {
		stage.setTitle("HTMLEditor Sample");
		stage.setWidth(500);
		stage.setHeight(500);
		Scene scene = new Scene(new Group());

		VBox root = new VBox();
		root.setPadding(new Insets(8, 8, 8, 8));
		root.setSpacing(5);
		root.setAlignment(Pos.BOTTOM_LEFT);

		final HTMLEditorControl htmlEditor = new HTMLEditorControl();
		htmlEditor.setPrefHeight(245);
		//htmlEditor.setHtmlText("");

		//htmlEditor.setHtmlText(null);
		htmlEditor.setStyle("-fx-font-family: Arial;");
		htmlEditor.setHtmlText("<html><body><head></head>Hello<body></html>");



		final TextArea htmlCode = new TextArea();
		htmlCode.setWrapText(true);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().add("noborder-scroll-pane");
		scrollPane.setContent(htmlCode);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefHeight(180);

		Button showHTMLButton = new Button("erstelle HTML Code und eine Word-Datei");
		root.setAlignment(Pos.CENTER);
		showHTMLButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {				
				htmlCode.setText(htmlEditor.getHtmlText());
				save(htmlEditor.getHtmlText());
			}
		});

		root.getChildren().addAll(htmlEditor, showHTMLButton, scrollPane);
		scene.setRoot(root);
		stage.setScene(scene);
		stage.show();
	}

	private void save(String html){
		WordprocessingMLPackage wordMLPackage;
		try {
			wordMLPackage = WordprocessingMLPackage.createPackage();
//			String html = "<html><head><title>Import me</title></head><body><p>Hello World!</p></body></html>"; 

			wordMLPackage.getMainDocumentPart().addAltChunk(AltChunkType.Html, html.getBytes()); 


			wordMLPackage.save(new java.io.File("d:\\test.docx"));
			
		} catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


