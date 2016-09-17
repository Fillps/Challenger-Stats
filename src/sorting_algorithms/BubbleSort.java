package sorting_algorithms;

import java.util.Comparator;

class BubbleSort {
	public static <T> T[] sort(T[] array, Comparator<T> comparator){
		int m = array.length - 1;
		int k = 1;
		boolean troca;
		do{
			troca = false;
			for(int i=0 ; i < m ; i++){
				if(comparator.compare(array[i], array[i+1])==1){
					T temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					k = i;
					troca = true;
				}
			}
			m = k;
		} while(troca);
		return array;
	}
}
