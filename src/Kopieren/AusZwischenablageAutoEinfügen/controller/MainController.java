package Kopieren.AusZwischenablageAutoEinfügen.controller;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import Kopieren.AusZwischenablageAutoEinfügen.Start;
import Kopieren.AusZwischenablageAutoEinfügen.listener.ClipboardListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;



public class MainController implements Initializable {

	
	@FXML private TextArea textArea;

	ClipboardListener clipboardListener;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		clipboardListener = new ClipboardListener(this);
		clipboardListener.start();


	}


    private String ausZwischenablageEinfuegen(){
    	String clipCont = null;

        Transferable data = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        DataFlavor[] df = data.getTransferDataFlavors();

        try{
	        clipCont = (String) data.getTransferData(DataFlavor.stringFlavor);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error -- " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }

		return clipCont;
    }

    public TextArea getTextArea() {return textArea;}

	public void setMainStage(Start start) {


	}

}
