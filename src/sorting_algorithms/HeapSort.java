package sorting_algorithms;

import java.util.Comparator;

class HeapSort {
	public static final String sigla = "HPST";
	
	private static int heap_size;
	private static int esq;
	private static int dir;
	private static int maior;
		
	public static <T> void troca(T[] array,int i,int j){
		T aux=array[i];
		array[i]=array[j];
		array[j]=aux;	
	}
	
	public static <T> void maxheapify(T[] array, int i, Comparator <T> comparator){
		esq=2*i;
		dir=2*i+1;
		
		if(esq<=heap_size && comparator.compare(array[esq],array[i])>0)
			maior=esq;
		else
			maior=i;
		
		if(dir<=heap_size && comparator.compare(array[dir],array[maior])>0)
			maior=dir;
		
		if(maior!=i){
			troca(array,i,maior);
			maxheapify(array,maior,comparator);
		}	
	}
	
	public static <T> void buildheap(T[] array, Comparator <T> comparator){
		heap_size=array.length-1;
		for(int i=heap_size/2; i>=0; i--){
			maxheapify(array,i,comparator);
		}	
	}
	public static <T> void sort(T[] array, Comparator<T> comparator){
		buildheap(array,comparator);
		
		for(int i=heap_size; i>0; i--){
			troca(array,0,i);
			heap_size-=1;
			maxheapify(array,0,comparator);
		}
	}
}
