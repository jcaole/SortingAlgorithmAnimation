package algorithms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@SuppressWarnings("exports")
	@Override
	public void start(Stage stage) {
		MainWindow window = new MainWindow();
		Scene scene = new Scene(window,MainWindow.WINDOW_WIDTH+10,MainWindow.WINDOW_HEIGHT+10);
		stage.setTitle("Algorithm Visualization");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}