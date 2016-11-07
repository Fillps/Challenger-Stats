package model.sorting_algorithms;

import java.util.Comparator;

public class BubbleSort extends SortingAlgorithm {
	
	public static final String sigla = "BBST";
	
	public BubbleSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		boolean troca;
		do{
			troca = false;
			for(int i=0 ; i < (array.length - 1) ; i++){
				if(comparator.compare(array[i], array[i+1]) > 0){
					swap(array, i, i+1);
					troca = true;
				}
			}
		} while(troca);
	}
}
