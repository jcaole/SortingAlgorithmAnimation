package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class InsertionSort extends AbstractSort{
	private ArrayList<Transition> transitions;
	Bar[] array;

	public InsertionSort() {
		this.transitions = new ArrayList<>();
	}

	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		this.array = new Bar[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.array[i] = arr[i];
		}
		insertionSort(array);

		return transitions;
	}

	private void insertionSort(Bar[] arr) {
		int n = arr.length;

		for (int i = 1; i < n; i++) {
			int j = i - 1;
			Bar key = arr[i];

			ParallelTransition pTransition = new ParallelTransition();
			transitions.add(colorBar(arr, SELECT_COLOR, i));

			while (j >= 0 && arr[j].getValue() > key.getValue()) {
				pTransition.getChildren().add(arr[j].moveX(DX));
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;

			pTransition.getChildren().add(key.moveX(DX * (j + 1 - i)));
			transitions.add(pTransition);
			transitions.add(colorBar(arr, Color.BLUEVIOLET, j + 1));
		}
		transitions.add(colorBar(Arrays.asList(arr), START_COLOR));
	}

	@Override
	public Bar[] getArray() {
		return this.array;
	}
}