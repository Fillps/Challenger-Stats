package sorting_algorithms;


import comparators.ComparatorDigitCatcher;

public class RadixSort extends SortingAlgorithmNonComparator {
	public static final String sigla = "RMSD";

	private static int maxLength;
	public RadixSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, ComparatorDigitCatcher<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, ComparatorDigitCatcher<T> comparator){
		maxLength = 0;
		for (T t : array){
			maxLength = Math.max(maxLength, comparator.getLength(t));
		}
		int digit = 0;
		int inferiorIndex = 0;
		int superiorIndex = array.length - 1;
		msd(array, comparator, inferiorIndex, superiorIndex, digit);
	}
	
	private static <T> void msd(T[] array, ComparatorDigitCatcher<T> comparator, int inferiorIndex, int superiorIndex, int digit){
		if (inferiorIndex >= superiorIndex || digit >= maxLength)
			return;
		T[] temp = array.clone();
		int[] cont = new int[Character.MAX_VALUE+3];
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			//System.out.println(superiorIndex);
			int digitVallue = comparator.getDigitValue(array[i], digit, maxLength);
			cont[digitVallue + 2]++;
		}
		for (int i = 1; i < Character.MAX_VALUE+3; i++){
			cont[i] += cont[i-1];
		}
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			int digitValue = comparator.getDigitValue(array[i], digit, maxLength) + 1;
			int contIndex = cont[digitValue]++ + inferiorIndex;
			temp[contIndex] = array[i];
		}
		for (int i = inferiorIndex; i <= superiorIndex; i++){
			array[i] = temp[i];
		}
		for (int i = 0; i < Character.MAX_VALUE+2; i++){
			msd(array, comparator, cont[i] + inferiorIndex, cont[i+1]  + inferiorIndex - 1, digit+1);
		}
		
	}
	

	
	
}
