package sorting_algorithms;

import java.util.Comparator;

public class RadixSort extends SortingAlgorithm {
	public static final String sigla = "BBST";

	public RadixSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		/*
		 * TODO
		 */
	}
}
