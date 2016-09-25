package sorting_algorithms;


public class SortingAlgorithm {
	
	public static <T> void swap(T[] array,int i,int j){
		T aux=array[i];
		array[i]=array[j];
		array[j]=aux;	
	}
}
