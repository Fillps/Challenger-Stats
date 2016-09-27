package comparators;

import java.util.Comparator;

public interface ComparatorDigitCatcher<T> extends Comparator<T> {

	public int getValueDigit(T object, int digitIndex);
	public int getLength(T object);
	
}
