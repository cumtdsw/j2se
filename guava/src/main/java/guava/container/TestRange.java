package guava.container;

import com.google.common.collect.Range;

public class TestRange {

	public static void main(String[] args) {
		Range<Integer> numberRange = Range.closed(1,10);
		//both return true meaning inclusive
		numberRange.contains(10);
		numberRange.contains(1);
		
		numberRange = Range.open(1,10);
		//both return false meaning exclusive
		numberRange.contains(10);
		numberRange.contains(1);
	}
}
