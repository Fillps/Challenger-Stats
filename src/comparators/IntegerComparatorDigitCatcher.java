package comparators;

public class IntegerComparatorDigitCatcher implements ComparatorDigitCatcher<Integer> {

	@Override
	public int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	@Override
	public int getDigitValue(Integer n, int digitIndex, int maxLength) {
		String number = String.valueOf(n);
		String string_0 = new String(new char[maxLength -number.length()]).replace('\0','0');
		
		number = string_0 + number;
		
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
}
