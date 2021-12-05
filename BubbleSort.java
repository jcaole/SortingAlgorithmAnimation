package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class BubbleSort extends AbstractSort{

	private ArrayList<Transition> transitions;
	Bar [] array;

	boolean swap;

	public BubbleSort() {
		this.transitions = new ArrayList<>();
		swap = false;
		
	}
	
	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		this.array = new Bar[arr.length];
		for(int i = 0; i< arr.length; i++) {
			this.array[i] = arr[i];
		}
		bubbleSort();

		transitions.add(colorBar(Arrays.asList(arr), Color.BLUEVIOLET));
		return transitions;
	}

	private void bubbleSort() {
		for (int i = 0; i < this.array.length; i++) {
			swap = false;
			for (int j = 0; j < this.array.length - 1 - i; j++) {
				this.transitions.addAll(compareBar(this.array, j, j + 1));
			}

			if (!swap) {
				break;
			}
		}

	}

	private ArrayList<Transition> compareBar(Bar[] arr, int a, int b) {
		ArrayList<Transition> transitions = new ArrayList<>();

		transitions.add(colorBar(arr, SELECT_COLOR, a, b));

		if (arr[a].getValue() > arr[b].getValue()) {
			transitions.add(swap(arr, a, b));
			swap = true;
		}

		transitions.add(colorBar(arr, START_COLOR, a, b));

		return transitions;
	}

	@Override
	public Bar[] getArray() {
		return this.array;
	}
	
	

}