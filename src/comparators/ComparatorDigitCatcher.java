package comparators;

import java.util.Comparator;

public interface ComparatorDigitCatcher<T> extends Comparator<T> {

	public int getDigitValue(T object, int digitIndex, int maxLength);
	public int getLength(T object);
	public ComparatorDigitCatcher<T> reversedDigitCatcher();
	
}
