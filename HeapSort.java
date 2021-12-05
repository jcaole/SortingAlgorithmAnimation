package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class HeapSort extends AbstractSort{

	private static final Color ROOT_COLOR = Color.YELLOW;
	private ArrayList<Transition> transitions;
	Bar[] array;

	public HeapSort() {
		this.transitions = new ArrayList<>();
	}

	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		this.array = new Bar[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.array[i] = arr[i];
		}
		heapSort();

		transitions.add(colorBar(Arrays.asList(arr), Color.BLUEVIOLET));
		return transitions;
	}

	private void heapSort() {
		// build max heap
		transitions.add(colorBar(selectSubTree(this.array, this.array.length), SELECT_COLOR));
		for (int i = this.array.length / 2 - 1; i >= 0; i--) {
			heap(this.array, i, this.array.length);
		}
		transitions.add(colorBar(selectSubTree(this.array, this.array.length), START_COLOR));
		for (int i = this.array.length - 1; i > 0; i--) {
			transitions.add(colorBar(this.array, ROOT_COLOR, 0));
			transitions.add(swap(this.array, 0, i));
			transitions.add(colorBar(this.array, START_COLOR, i));
			transitions.add(colorBar(selectSubTree(this.array, i), SELECT_COLOR));
			heap(this.array, 0, i);
			transitions.add(colorBar(selectSubTree(this.array, i), START_COLOR));
		}
	}

	private void heap(Bar[] arr, int i, int n) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		int max = i;

		if (left < n && arr[max].getValue() < arr[left].getValue()) {
			max = left;
		}

		if (right < n && arr[max].getValue() < arr[right].getValue()) {
			max = right;
		}

		if (max != i) {
			transitions.add(swap(arr, i, max));
			heap(arr, max, n);
		}

	}

	private ArrayList<Bar> selectSubTree(Bar[] arr, int n) {
		ArrayList<Bar> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(arr[i]);
		}

		return list;
	}

	@Override
	public Bar[] getArray() {
		return this.array;
	}

}