package main;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 * http://java-buddy.blogspot.ch/2014/08/bind-javafx-progressbarprogressproperty.html
 */
public class JavaFXExecutorService extends Application {
 
    @Override
    public void start(Stage primaryStage) {
         
        CountDownLatch countDownLatch1 = new CountDownLatch(5);
        CountThread countThread1 = new CountThread("A", countDownLatch1, 5);
        ProgressBar progressBar1 = new ProgressBar();
        progressBar1.progressProperty().bind(countThread1.processProperty);
         
        CountDownLatch countDownLatch2 = new CountDownLatch(10);
        CountThread countThread2 = new CountThread("B", countDownLatch2, 10);
        ProgressBar progressBar2 = new ProgressBar();
        progressBar2.progressProperty().bind(countThread2.processProperty);
         
        Button btn = new Button();
        btn.setText("Start");
        btn.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Start");
                new Thread(countThread1).start();
                new Thread(countThread2).start();
            }
        });
         
        VBox vBox = new VBox();
        vBox.getChildren().addAll(btn, progressBar1, progressBar2);
         
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
         
        Scene scene = new Scene(root, 300, 250);
         
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
}
 
class CountThread implements Runnable {
 
    int num_of_count = 5;
    CountDownLatch counter;
    String name;
     
    DoubleProperty processProperty;
 
    CountThread(String n, CountDownLatch c, int num) {
        name = n;
        counter = c;
        num_of_count = num;
        processProperty = new SimpleDoubleProperty(num_of_count);
    }
 
    @Override
    public void run() {
 
        for (int i = 0; i < num_of_count; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CountThread.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            processProperty.set((double)(counter.getCount())/(double)num_of_count);
            System.out.println(name + " : " + counter.getCount());
            counter.countDown();
             
        }
    }
 
}