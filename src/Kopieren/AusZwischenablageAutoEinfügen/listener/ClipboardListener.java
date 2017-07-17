package Kopieren.AusZwischenablageAutoEinfügen.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.*;
import java.io.IOException;

import Kopieren.AusZwischenablageAutoEinfügen.controller.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.Clipboard;
import javafx.util.Duration;


public class ClipboardListener{

	MainController mainController;
	String oldString = "";
	Timeline repeatTask;

    public ClipboardListener(MainController mainController) {
		this.mainController = mainController;

	    final Clipboard clipboard = Clipboard.getSystemClipboard();
        repeatTask = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (clipboard.hasString()) {
                    String newString = clipboard.getString();
                    mainController.getTextArea().setText(newString);
//                    if(!oldString.equals(newString)) {
//                        System.out.printf("String changed in clipboard: " + newString);
//
//                        oldString = newString;
//                    }
//                    else {
////                        System.out.println("String not changed.");
//                    }
                }
            }
        }));

	}

    public void start(){
        repeatTask.setCycleCount(Timeline.INDEFINITE);
        repeatTask.play();
    }

    public void stop(){
        repeatTask.stop();
    }

}
