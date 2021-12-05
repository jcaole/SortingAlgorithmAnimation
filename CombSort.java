package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class CombSort extends AbstractSort{
	private static final Color ROOT_COLOR = Color.MAGENTA;
	private ArrayList<Transition> transitions;
	Bar[] array;
	
	public CombSort() {
		this.transitions = new ArrayList<>();
	}

	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		this.array = new Bar[arr.length];
		for (int i = 0; i < arr.length; i++) {
			this.array[i] = arr[i];
		}
		
		combSort();
		transitions.add(colorBar(Arrays.asList(array), Color.BLUEVIOLET));
		return transitions;
	}

	private void combSort() {
		int n = array.length;
		int gap = n;
		boolean swapped = true;
		while(gap !=1 || swapped == true) {
			gap = getNext(gap);
			transitions.add(colorBar(array,ROOT_COLOR,gap));
			swapped=false;
			
			for(int i = 0; i<n-gap;i++) {
				transitions.add(colorBar(array,START_COLOR,i));
				if(array[i].getValue()>array[i+gap].getValue()) {
					transitions.add(swap(array,i,i+gap));
					transitions.add(colorBar(array,SELECT_COLOR,i));
					swapped = true;
				}
			}
			
		}
		
		
	}

	private int getNext(int gap) {
		gap = (gap*10)/13;
		if(gap<1) {
			return 1;
		}
		return gap;
	}

	@Override
	public Bar[] getArray() {
		return this.array;
	}


}
