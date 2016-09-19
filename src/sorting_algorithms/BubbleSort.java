package sorting_algorithms;

import java.util.Comparator;

class BubbleSort {
	public static final String sigla = "BBST";
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		boolean troca;
		do{
			troca = false;
			for(int i=0 ; i < (array.length - 1) ; i++){
				if(comparator.compare(array[i], array[i+1])==1){
					T temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					troca = true;
				}
			}
		} while(troca);
	}
}
