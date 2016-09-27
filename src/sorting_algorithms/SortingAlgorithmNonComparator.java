package sorting_algorithms;

import comparators.ComparatorDigitCatcher;

public abstract class SortingAlgorithmNonComparator {

	private String sigla;
	
	public SortingAlgorithmNonComparator(String sigla){
		this.sigla = sigla;
	}
	
	public static <T> void swap(T[] array,int i,int j){
		T aux=array[i];
		array[i]=array[j];
		array[j]=aux;	
	}
	
	public abstract <T> void sort_array(T[] array, ComparatorDigitCatcher<T> comparator);
	
	public String getSigla(){
		return this.sigla;
	}
}
