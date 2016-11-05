package sorting_algorithms.comparators;

import java.util.Comparator;

public interface ComparatorDigitCatcher<T> extends Comparator<T> {

	int getDigitValue(T object, int digitIndex, int maxLength);
	int getLength(T object);
	ComparatorDigitCatcher<T> reversedDigitCatcher();
	
}
