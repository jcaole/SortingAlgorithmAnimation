package algorithms;

import java.util.Random;

import javafx.scene.paint.Color;

public class RandomBars {

	public RandomBars() {}

	@SuppressWarnings("exports")
	public static Bar[] randomBars(int length, Color color) {
		Bar[] arr = new Bar[length];
		Random r = new Random();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = new Bar(1 + r.nextInt(arr.length));
			arr[i].setX(i * (MainWindow.WINDOW_WIDTH / arr.length) + 5);
			arr[i].setFill(color);
			setBarDimension(arr[i], arr.length);
		}
		return arr;

	}

	private static void setBarDimension(Bar bar, int length) {
		bar.setWidth(MainWindow.WINDOW_WIDTH / length - MainWindow.XGAP);
		bar.setHeight(
				((MainWindow.WINDOW_HEIGHT - MainWindow.BUTTONROW_BOUNDARY) / length) * bar.getValue());

	}
}
