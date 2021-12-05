package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class MergeSort extends AbstractSort{
//	Bar[] array;
	
	private Bar[] temp;

	private ArrayList<Transition> merge(Bar[] arr, int p, int q, int r) {

		ArrayList<Transition> transitions = new ArrayList<>();
		
		List<Bar> tempList = new ArrayList<>();
		
		for(int i = p; i <=r; i++) {
			temp[i] = arr[i];
			tempList.add(temp[i]);
		}
		int i = p;
		int j = q + 1;
		int k = p;
		
		while((i <= q) && (j <= r)) {
			if(temp[i].getValue() <= temp[j].getValue()) {
				arr[k++] = temp[i++];
			}
			else {
				arr[k++] = temp[j++];
			}
		}
	    while (i <= q) {
	        arr[k++] = temp[i++];
	      }
	    while (j <= r) {
	    	arr[k++] = temp[j++];
	    }
	    transitions.add(colorBar(tempList, SELECT_COLOR));
	    
	    ParallelTransition pTransition = new ParallelTransition();
	    
	    for(int x = p; x <= r; x++) {
	    	for(int y = p; y <= r; y++) {
	    		if(temp[x].equals(arr[y])) {
	    			pTransition.getChildren().add(temp[x].moveX(DX * (y - x)));
	    		}
	    	}
	    }
	    transitions.add(pTransition);
	    transitions.add(colorBar(tempList, SELECT_COLOR));
	    
	    return transitions;
	}
	
	
	private ArrayList<Transition> mergeSort(Bar[] array, int p, int r) {
		ArrayList<Transition> transitions = new ArrayList<>();
		
		if(p < r) {
			int q = (p + r) / 2;
			transitions.addAll(mergeSort(array, p, q));
			transitions.addAll(mergeSort(array, q + 1, r));
			transitions.addAll(merge(array, p, q, r));
		}
		return transitions;
	}
	
	@SuppressWarnings("exports")
	@Override
	public ArrayList<Transition> startSort(Bar[] arr) {
		ArrayList<Transition> transitions = new ArrayList<>();
		this.temp = new Bar[arr.length];
		
		transitions.addAll(mergeSort(arr, 0, arr.length - 1));
		
		transitions.add(colorBar(Arrays.asList(arr), Color.BLUEVIOLET));
		return transitions;
		
	}

	@Override
	public Bar[] getArray() {
		return this.temp;
	}
}