package model.sorting_algorithms.comparators;

public class IntegerComparatorDigitCatcher implements ComparatorDigitCatcher<Integer> {
	public static final String tipo = "numerico";
	
	@Override
	public int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}
	
	@Override
	public String toString(){
		return tipo;
	}

	@Override
	public int getDigitValue(Integer n, int digitIndex, int maxLength) {
		String number = String.format("%0" + String.valueOf(maxLength) + "d", n);
		try{
			char digit = number.charAt(digitIndex);
			return Character.getNumericValue(digit);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	@Override
	public int getLength(Integer n) {
		int length = String.valueOf(n).length();
		return length;
	}

	@Override
	public ComparatorDigitCatcher<Integer> reversedDigitCatcher() {
		return new IntegerComparatorDigitCatcherReversed();
	}
	
}

class IntegerComparatorDigitCatcherReversed extends IntegerComparatorDigitCatcher {
	
	public int compare(Integer a, Integer b) {
		return (super.compare(a, b))*(-1);
	}
	
	public int getDigitValue(Integer a, int digitIndex, int maxLength){
		return (9 - (super.getDigitValue(a, digitIndex, maxLength)));
	}
	
	public ComparatorDigitCatcher<Integer> reversedDigitCatcher() {
		return new IntegerComparatorDigitCatcher();
	}
}
