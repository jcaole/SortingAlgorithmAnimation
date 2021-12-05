package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class SelectionSort extends AbstractSort{  
    private ArrayList<Transition> transitions;    
    private static final Color MIN_COLOR = Color.ORANGE;    
    private static final Color LOWER_MIN_COLOR = Color.RED;
    
    private Bar[] array;
    
    public SelectionSort() {
    	this.transitions = new ArrayList<>();
    }
    
    private ParallelTransition ColorNode(Bar arr[], int x, int y, Color A, Color B) {
        ParallelTransition pTransition = new ParallelTransition();
        pTransition.getChildren().addAll(colorBar(arr, A, x), colorBar(arr, B, y));
        return pTransition;
    }
    
    @SuppressWarnings("exports")
	@Override
    public ArrayList <Transition> startSort(Bar[] arr) {
        transitions = new ArrayList<>();
//        int minIdx;
        
        this.array = new Bar[arr.length];
        for(int i = 0; i < arr.length; i++) {
        	this.array[i] = arr[i];
        }
        selectionSort(array);
        
        return transitions;
    }
    private void selectionSort(Bar[] arr) {
    	int minIdx;
    	
    	for (int i = 0; i < arr.length - 1; i++) {
            minIdx = i;
            transitions.add(colorBar(arr, LOWER_MIN_COLOR, minIdx));
            for (int j = i+1; j < arr.length; j++) {
                transitions.add(colorBar(arr, SELECT_COLOR, j));
                if (arr[j].getValue() < arr[minIdx].getValue()) {
                    if (minIdx == i) transitions.add(ColorNode(arr, minIdx, j, MIN_COLOR, LOWER_MIN_COLOR));
                    else transitions.add(ColorNode(arr, minIdx, j, Color.BLUEVIOLET, LOWER_MIN_COLOR));
                    minIdx = j;
                }
                else transitions.add(colorBar(arr, START_COLOR, j));
            }
            if (minIdx != i) transitions.add(swap(arr, i, minIdx));
            transitions.add(colorBar(arr, SORTED_COLOR, i, minIdx));
        }
        transitions.add(colorBar(Arrays.asList(arr), Color.BLUEVIOLET));
    }
    
	@Override
	public Bar[] getArray() {
		return this.array;
	}
}