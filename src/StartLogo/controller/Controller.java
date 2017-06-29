package StartLogo.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Controller implements Initializable{

	@FXML private VBox vBox;
	@FXML private ImageView imageViewLogo;
	private Task<ObservableList<Integer>> meinWorker;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize(URL location, ResourceBundle resources) {

		meinWorker = createWorker();

		vBox.opacityProperty().unbind();
		imageViewLogo.opacityProperty().unbind();

		vBox.opacityProperty().bind(meinWorker.progressProperty());
		imageViewLogo.opacityProperty().bind(meinWorker.progressProperty());

		new Thread(meinWorker).start();
	}

	private Task<ObservableList<Integer>> createWorker() {
		System.out.println("Start Task - Daten zählen");
        return new Task<ObservableList<Integer>>() {
        	double count = 0.0;

			@Override
            protected ObservableList<Integer> call() throws Exception {

                while (count < 1) {
                		Thread.sleep(20);
                		count = count + 0.005;
                        System.out.println(count);
//						results.add(i);
                        updateProgress(count, 1);
                 }

                System.out.println("Task Ende - Daten zählen");
                return null;
            }

        };

    }

}
