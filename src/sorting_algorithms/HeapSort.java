package sorting_algorithms;

import java.util.Comparator;

public class HeapSort extends SortingAlgorithm {
	public static final String sigla = "HPST";
	
	private static int heap_size;
	private static int esq;
	private static int dir;
	private static int maior;
	
	private static <T> void maxHeapify(T[] array, int i, Comparator <T> comparator){
		esq=2*i;
		dir=2*i+1;
		
		if(esq<=heap_size && comparator.compare(array[esq],array[i])>0)
			maior=esq;
		else
			maior=i;
		
		if(dir<=heap_size && comparator.compare(array[dir],array[maior])>0)
			maior=dir;
		
		if(maior!=i){
			swap(array,i,maior);
			maxHeapify(array,maior,comparator);
		}	
	}
	
	private static <T> void buildHeap(T[] array, Comparator <T> comparator){
		heap_size=array.length-1;
		for(int i=heap_size/2; i>=0; i--){
			maxHeapify(array,i,comparator);
		}	
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		buildHeap(array,comparator);
		
		for(int i=heap_size; i>0; i--){
			swap(array,0,i);
			heap_size-=1;
			maxHeapify(array,0,comparator);
		}
	}
}
