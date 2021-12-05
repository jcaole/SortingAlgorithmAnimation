/**
 * 
 */
package semesterProject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.Bar;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

class TestBar {

	Bar tu;
	int value;
	TranslateTransition t;
	
	@BeforeEach 
    void init() {
		value = 5;
		tu = new Bar(value);
		t = new TranslateTransition();
    }
	
	@Test
	void testGetValue() {
		assertEquals(value,tu.getValue());
	}
	
	@Test
	void testmoveX() {
		int location = 5;
		t.setNode(tu);
		t.setDuration(Duration.millis(100));
		t.setByX(location);
		TranslateTransition tt = tu.moveX(value);
		assertEquals(t.getDuration(),tt.getDuration());
		assertEquals(t.getNode(),tt.getNode());
		assertEquals(t.getByX(),tt.getByX());
	}

}
