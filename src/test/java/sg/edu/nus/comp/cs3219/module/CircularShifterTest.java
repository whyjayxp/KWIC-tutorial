package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}
	
	@Test
	public void test1() {
		inputLineStorage.addLine("Key Word in Context");
		assertEquals(4, afterShiftLineStorage.size());
		
		assertEquals("Key Word in Context", afterShiftLineStorage.get(0).toString());
		assertEquals("Word in Context Key", afterShiftLineStorage.get(1).toString());
		assertEquals("in Context Key Word", afterShiftLineStorage.get(2).toString());
		assertEquals("Context Key Word in", afterShiftLineStorage.get(3).toString());
	}
	
	@Test
	public void test2() {
		inputLineStorage.addLine("the after the");
		assertEquals(0, afterShiftLineStorage.size());
	}

}
