package Ping.PingTimeoutsSpeichern;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;


import javax.imageio.ImageIO;

import Ping.PingTimeoutsSpeichern.controller.MainWindowController;
import Ping.PingTimeoutsSpeichern.menu.MenuTrayIcon;
import Ping.PingTimeoutsSpeichern.properties.WindowProperties;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.*;
import java.util.*;

// Java 8 code
// https://gist.github.com/jewelsea/e231e89e8d36ef4e5d8a

public class SystemTrayIcon extends Application {

	// Config
	private final String programmName = "Ping-Kontrolle";
	private final String propertiesFile = "window.properties";
	private boolean mitSystemTrayIcon = false;

	private Stage primaryStage;
	private MainWindowController mainWindowController;
	private boolean isFensterMax;
	private Double fensterPosX;
	private Double fensterPosY;
	File fileProperties;

	// Models
    private WindowProperties windowProperties = new WindowProperties();

    // one icon location is shared between the application tray icon and task bar icon.
    // you could also use multiple icons to allow for clean display of tray icons on hi-dpi devices.

    // application stage is stored so that it can be shown and hidden based on system tray icon operations.
    private Stage stageEinstellungen;

    // a timer allowing the tray icon to provide a periodic notification event.
    private Timer notificationTimer = new Timer();

    // format used to display the current time in a tray icon notification.
    private DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

	@Override
	public void init() throws Exception {
		System.out.println("--- vor Window-Start ---");
		windowProperties.setMain(this);
		// Start-Koordinaten f�r das Fenster
////			fileProperties = new File(propertiesFile);
//			erstellePropertiesFile(fileProperties);
//	        if(fileProperties.exists()){
//	            getStartKoordinatenPropertie();
//	    	}
	}


    // sets up the javafx application.
    // a tray icon is setup for the icon, but the main stage remains invisible until the user
    // interacts with the tray icon.
    @Override public void start(final Stage stage) {
        // stores a reference to the stage.

        this.stageEinstellungen = new Stage();

    	Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	startStageEinstellungen(stageEinstellungen);
            	if (!mitSystemTrayIcon) {
					showStageEinstellungen();
				}

            }
       });



        // sets up the tray icon (using awt code run on the swing thread).
    	if (mitSystemTrayIcon) {
//			javax.swing.SwingUtilities.invokeLater(this::addAppToTray);
			javax.swing.SwingUtilities.invokeLater(this::addMenuTray);
		}


    }

    private void addMenuTray(){
    	MenuTrayIcon MenuTrayIcon = new MenuTrayIcon(windowProperties, stageEinstellungen);
    }


    @Override
    public void stop() throws Exception {
    	System.out.println("--- Ausf�hren nachdem Window geschlossen wird ---");
    	saveAlleFensterProperties();
    	mainWindowController.friendFinder.cancel();
    	Platform.exit();
    	System.exit(0);
		System.out.println("--- Ende SystemTrayIcon ---");
    }


    public void saveAlleFensterProperties() {
    	if(windowProperties.gibtEsPropertiesFile()){
        	saveFensterPropertiesModulRP();

        	windowProperties.saveStartKoordinatenPropertie();
        	System.out.println("Properties gespeichert");
        }
	}

	public void saveFensterPropertiesModulRP() {
		windowProperties.setFensterModulRPPosX(stageEinstellungen.getX());
        windowProperties.setFensterModulRPPosY(stageEinstellungen.getY());
        windowProperties.setFensterModulRPMax(stageEinstellungen.isMaximized());
        windowProperties.setFensterModulRPWidth(stageEinstellungen.getWidth());
        windowProperties.setFensterModulRPHeight(stageEinstellungen.getHeight());
	}

	public void startStageEinstellungen(final Stage stageEinstellungen) {
        this.stageEinstellungen = stageEinstellungen; // stores a reference to the stage.

		try {
			FXMLLoader loader = new FXMLLoader(SystemTrayIcon.class.getResource("view/fxml/MainWindowView.fxml"));
//				FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/fxml/MainWindowViewTableView.fxml"));
			AnchorPane root = loader.load();

			stageEinstellungen.setTitle(programmName);
			stageEinstellungen.getIcons().add(new Image(SystemTrayIcon.class.getResourceAsStream( "view/images/PING.png" )));
			stageEinstellungen.setMinWidth(400.00);
			stageEinstellungen.setMinHeight(300.00);
//				primaryStage.initStyle(StageStyle.UNDECORATED); // ohne Fenster-Rahmen

			stageEinstellungen.setOnHiding(event -> Platform.runLater(this::saveAlleFensterProperties));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(SystemTrayIcon.class.getResource("view/css/MainWindowCSS.css").toExternalForm());

			// nur Verwenden, wenn es keinen FensterRahmen gibt
//		        ResizeListener listener = new ResizeListener(scene, primaryStage);
//		        scene.setOnMouseMoved(listener);
//		        scene.setOnMousePressed(listener);
//		        scene.setOnMouseDragged(listener);

			// letzte Position vom Fenster Speichern
			if(windowProperties.gibtEsPropertiesFile()){
				windowProperties.loadStartKoordinatenPropertie();

				stageEinstellungen.setMaximized(windowProperties.isFensterModulRPMax());
		        if (!stageEinstellungen.isMaximized()) {
	        		windowProperties.setFensterModulRPPosX(stageEinstellungen.getX());
	        		windowProperties.setFensterModulRPPosY(stageEinstellungen.getY());
	        		windowProperties.setFensterModulRPWidth(stageEinstellungen.getWidth());
	        		windowProperties.setFensterModulRPHeight(stageEinstellungen.getHeight());
		        }
			}
			else{
				windowProperties.erstellePropertiesFile();
			}

			mainWindowController = loader.getController();
			mainWindowController.setMainWindowStage(this, stageEinstellungen);

			stageEinstellungen.setScene(scene);

			if(mitSystemTrayIcon){
				Platform.setImplicitExit(false);
			}

			// Wenn das Fenster wieder minimiert wird, dann hol dir die alten Fensterdaten
			stageEinstellungen.maximizedProperty().addListener(new ChangeListener<Boolean>() {
			    @Override
			    public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
			        System.out.println("maximized:" + t1.booleanValue());
			        if(!t1.booleanValue()){
						stageEinstellungen.setX(windowProperties.getFensterModulRPPosX());
						stageEinstellungen.setY(windowProperties.getFensterModulRPPosY());
						stageEinstellungen.setWidth(windowProperties.getFensterModulRPWidth());
						stageEinstellungen.setHeight(windowProperties.getFensterModulRPHeight());
			        }
			    }
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("--- Window gestartet ---");
    }


    /**
     * Shows the application stage and ensures that it is brought ot the front of all stages.
     */
    private void showStageEinstellungen() {
        if (stageEinstellungen != null) {
			if(windowProperties.gibtEsPropertiesFile()){

				if(!isScreenWidthOKForStage(stageEinstellungen)){
					stageEinstellungen.setX(0);
					stageEinstellungen.setY(0);

					stageEinstellungen.centerOnScreen();
				}
			}
			stageEinstellungen.show();
			stageEinstellungen.toFront();
        }
    }

	public void setStartKoordinatenPropertie(boolean maxFenster, double x, double y)  {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(propertiesFile);

			// set the properties value
			prop.setProperty("maxFenster",  maxFenster + "");
			prop.setProperty("X", x + "");
			prop.setProperty("Y", y + "");

			prop.store( output, "Start-Koordinaten vom Fenster" );
			// save properties to project root folder

//			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public String getStartKoordinatenPropertie() throws IOException {

		String result = "";
		InputStream inputStream = null;

		try {
			Properties prop = new Properties();

			inputStream = new FileInputStream(propertiesFile);

			prop.load(inputStream);

			// get the property value and print it out
			String maxFenster = prop.getProperty("maxFenster");
			String x = prop.getProperty("X");
			String y = prop.getProperty("Y");

			isFensterMax = Boolean.valueOf(maxFenster);
			fensterPosX = Double.valueOf(x);
			fensterPosY = Double.valueOf(y);


		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}

	private boolean erstellePropertiesFile(File file) {
        if (file != null) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating " + file.toString());
            }
            if (file.isFile() && file.canWrite() && file.canRead())
                return true;
        }
        return false;
    }

	private int getScreens(double sceneWidth) {

		ObservableList<Screen> screenList = Screen.getScreens();

		int countSizeX = 0;
		for (Screen screen : screenList) {
			countSizeX = (int) (countSizeX + screen.getVisualBounds().getWidth());
		}

		countSizeX = (int) (countSizeX - sceneWidth);
		return countSizeX;
    }

	   private boolean isScreenWidthOKForStage(Stage mainStageModulRP2){

			ObservableList<Screen> screenBounds = Screen.getScreens();

			double screenWidth = 0;


			for (int i = 0; i < screenBounds.size(); i++) {
				screenWidth = screenWidth + screenBounds.get(i).getBounds().getWidth();
				System.out.println(i + " - " + screenBounds.get(i).getBounds().getWidth() + " X " + screenBounds.get(i).getBounds().getHeight());
			}

			if(windowProperties.getFensterModulRPPosX() == null || windowProperties.getFensterModulRPWidth() == null){
				return false;
			}

			double stagePosXPlusStage = windowProperties.getFensterModulRPPosX() + windowProperties.getFensterModulRPWidth();

			System.out.println("screenWidthMinusStage: " + stagePosXPlusStage + "  <=  screenWidth: " + screenWidth + "      scene.getWidth(): " + mainStageModulRP2.getScene().getWidth());

			if (stagePosXPlusStage <= screenWidth) {
				System.out.println("true");
				return true;
			}

			return false;

		}

		// Getter
		public String getProgrammName() {return programmName;}
		public String getPropertiesFile() {return propertiesFile;}

    public static void main(String[] args) throws IOException, java.awt.AWTException {
        // Just launches the JavaFX application.
        // Due to way the application is coded, the application will remain running
        // until the user selects the Exit menu option from the tray icon.
        launch(args);
    }
}
