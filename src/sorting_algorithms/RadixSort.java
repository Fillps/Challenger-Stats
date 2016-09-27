package sorting_algorithms;

import comparators.ComparatorDigitCatcher;

public class RadixSort extends SortingAlgorithmNonComparator {
	public static final String sigla = "RMSD";

	public RadixSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, ComparatorDigitCatcher<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, ComparatorDigitCatcher<T> comparator){
		/*
		 * TODO
		 */
	}

	
	
}
