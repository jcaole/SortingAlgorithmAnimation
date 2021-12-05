package semesterProject;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.AbstractSort;
import algorithms.Bar;
import algorithms.InsertionSort;

class TestInsertionSort {
	final int SIZE = 5;
	private AbstractSort insert;
	private Bar[] bars;
	private int[] barsValues;
	Random rand;
	
	@BeforeEach
	void init() {
		insert = new InsertionSort();
		rand = new Random();
		bars = new Bar[SIZE];
		barsValues = new int[SIZE];
		for(int i = 0; i < SIZE; i++) {
			bars[i] = new Bar(1 + rand.nextInt(SIZE));
			barsValues[i] = bars[i].getValue();
		}
	}

	@Test
	void testStartSorting() {
		Arrays.sort(barsValues);
		insert.startSort(bars);
		Bar[] result = insert.getArray();
		for (int i = 0; i < SIZE; i++) {
			assertTrue(barsValues[i] == result[i].getValue());
		}
	}

}