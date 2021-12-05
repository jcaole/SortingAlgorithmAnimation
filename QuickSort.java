package algorithms;

import java.util.ArrayList;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class QuickSort extends AbstractSort{
	
	private static final Color PIVOT_COLOR = Color.DARKGREEN;
	private ArrayList<Transition> transitions;
	Bar[] array;
	
	public QuickSort() {
		this.transitions = new ArrayList<>();
	}
	
	
	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		this.array = new Bar[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.array[i] = arr[i];
		}
		quickSort(array,0,array.length-1);
		transitions.add(colorBar(Arrays.asList(arr), Color.BLUEVIOLET));
		return transitions;
	}


	private void quickSort(Bar[] arr, int low, int high) {
		if(low>=0 && high>=0 && low<high) {
			int partition = partition(arr, low, high);
			quickSort(arr, low, partition-1);
			quickSort(arr,partition+1,high);
		}
		
	}

	private int partition(Bar[] arr, int low, int high) {
		int pivot = arr[high].getValue();
		int pivotIndex = low;
		transitions.add(colorBar(arr,PIVOT_COLOR,high));
		
		for(int i = low; i<high ; i++) {
			transitions.add(colorBar(arr,SELECT_COLOR,i));
			if(arr[i].getValue()<=pivot) {
				transitions.add(swap(arr,pivotIndex,i));
				transitions.add(colorBar(arr,START_COLOR,pivotIndex));
				pivotIndex++;
			}else {
				transitions.add(colorBar(arr,START_COLOR,i));
			}
		}
		transitions.add(swap(arr,pivotIndex,high));
		transitions.add(colorBar(arr,START_COLOR,pivotIndex));
		return pivotIndex;
	}

	@Override
	public Bar[] getArray() {
		return this.array;

	}

}
