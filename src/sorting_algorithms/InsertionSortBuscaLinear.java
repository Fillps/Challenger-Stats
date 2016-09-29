package sorting_algorithms;

import java.util.Comparator;

public class InsertionSortBuscaLinear extends SortingAlgorithm {
	public static final String sigla = "ISBL";
	
	public InsertionSortBuscaLinear(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		 for(int i=1 ; i < array.length ; i++){
			T chave = array[i];
			int j = i-1;
			while((j >= 0) && comparator.compare(array[j], chave) > 0){
				array[j+1] = array[j];
				j = j-1;
			}
			array[j+1] = chave;
		 }
	}
	
}