package TrayIconMitJavaFX.TrayIconMitLeerenPopUp;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;

// Java 8 code
public class JavaFXTray extends Application {

	private static final String iconImageLoc = "http://icons.iconarchive.com/icons/scafer31000/bubble-circle-3/16/GameCenter-icon.png";

	private Stage stage;
	StackPane layout;
	private Timer notificationTimer = new Timer();

	private DateFormat timeFormat = SimpleDateFormat.getTimeInstance();

	@Override
	public void start(final Stage stage) {
		this.stage = stage;

		Platform.setImplicitExit(false);

		javax.swing.SwingUtilities.invokeLater(this::addAppToTray);

		stage.initStyle(StageStyle.TRANSPARENT);

		layout = new StackPane();
		layout.setStyle("-fx-background-color: rgba(255, 255, 255, 1.0);");
		layout.setPrefSize(300, 200);





		layout.setOnMouseClicked(event -> stage.hide());

		Scene scene = new Scene(layout);
		scene.setFill(Color.TRANSPARENT);

		stage.setScene(scene);

	}

	/**
	 * For this dummy app, the (JavaFX scenegraph) content, just says "hello, world". A real app, might load an FXML or something like that.
	 *
	 * @return the main window application content.
	 */
//	private Node createContent() {
//		Label hello = new Label("hello, world");
//		hello.setStyle("-fx-font-size: 40px; -fx-text-fill: forestgreen;");
//		Label instructions = new Label("(click to hide)");
//		instructions.setStyle("-fx-font-size: 12px; -fx-text-fill: orange;");
//
//		VBox content = new VBox(10, hello, instructions);
//		content.setAlignment(Pos.CENTER);
//
//		return content;
//	}

	/**
	 * Sets up a system tray icon for the application.
	 */
	private void addAppToTray() {
		try {
			// ensure awt toolkit is initialized.
			java.awt.Toolkit.getDefaultToolkit();

			// app requires system tray support, just exit if there is no support.
			if (!java.awt.SystemTray.isSupported()) {
				System.out.println("No system tray support, application exiting.");
				Platform.exit();
			}

			// set up a system tray icon.
			java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
			URL imageLoc = new URL(iconImageLoc);
			java.awt.Image image = ImageIO.read(imageLoc);
			java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image);

			// if the user double-clicks on the tray icon, show the main app stage.
			// trayIcon.addActionListener(event -> Platform.runLater(() -> {
			// showStage(null, null);
			// }));

			// regardles of the mouse button you've clicked, the stage will be shown anyway

			trayIcon.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Platform.runLater(() -> {
						showStage(Double.valueOf(e.getX()), Double.valueOf(e.getY()));
						System.out.println("oben: " + Double.valueOf(e.getX()) + " x " + Double.valueOf(e.getY()));
					});
				}

				@Override
				public void mousePressed(java.awt.event.MouseEvent e) {}

				@Override
				public void mouseReleased(java.awt.event.MouseEvent e) {}

				@Override
				public void mouseEntered(java.awt.event.MouseEvent e) {}

				@Override
				public void mouseExited(java.awt.event.MouseEvent e) {}
			});

			java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
			exitItem.addActionListener(event -> {
				notificationTimer.cancel();
				Platform.exit();
				tray.remove(trayIcon);
			});

//			notificationTimer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					javax.swing.SwingUtilities.invokeLater(() -> trayIcon.displayMessage("hello", "The time is now " + timeFormat.format(new Date()),
//							java.awt.TrayIcon.MessageType.INFO));
//				}
//			}, 5_000, 60_000);

			tray.add(trayIcon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	/**
	 * Shows the application stage and ensures that it is brought ot the front of all stages.
	 */
	private void showStage(Double clickX, Double clickY) {
		if (stage != null) {

			if (clickX != null && clickY != null) {

				final Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

				clickX = clickX + 50;

				Point2D fromCoordsUR = new Point2D(clickX + layout.getPrefWidth(), clickY);
				Point2D fromCoordsLL = new Point2D(clickX, clickY + layout.getPrefHeight());
				System.out.println("fromCoordsUR x fromCoordsLL: " + layout.getPrefWidth() + " x " + layout.getPrefHeight());


				if (screenBounds.getMaxY() < fromCoordsLL.getY()) {
					System.out.println("screenBounds.getHeight(): " + screenBounds.getHeight());
					clickY = screenBounds.getHeight() - layout.getPrefHeight();
					System.out.println("clickY: " + clickY);
				}
				if (screenBounds.getMaxX() < fromCoordsUR.getX()) {
					clickX = screenBounds.getWidth() - layout.getPrefWidth();
					System.out.println("clickX: " + clickX);
				}

				stage.setX(clickX);
				stage.setY(clickY);
			}

			stage.show();
			stage.toFront();
		}
	}

	public static void main(String[] args) throws IOException, java.awt.AWTException {
		launch(args);
	}
}

