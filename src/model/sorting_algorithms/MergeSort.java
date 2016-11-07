package model.sorting_algorithms;

import java.util.Comparator;

public class MergeSort extends SortingAlgorithm {
	public static final String sigla = "MGST";

	public MergeSort(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}
	
	private static <T> void merge(T[] array,int inicio,int meio,int fim,Comparator<T> comparator){
		T[] aux=array.clone();				
		int i=inicio;
		int j=meio+1;
		int k=inicio;
		
		while(i<=meio && j<=fim){
			if(comparator.compare(aux[j],aux[i])>0){
				array[k]=aux[i];
				i++;
			}
			else{
				array[k]=aux[j];
				j++;
			}
			k++;
		}
		while(i<=meio){
			array[k]=aux[i];
			k++;
			i++;
		}
		
	}
	
	private static <T> void doMergeSort(T[] array,int inicio, int fim,Comparator<T> comparator){
		if(inicio<fim){
			int meio=Math.floorDiv(inicio+fim, 2);
			doMergeSort(array,inicio,meio,comparator);
			doMergeSort(array,meio+1,fim,comparator);
			merge(array,inicio,meio,fim,comparator);			
		}		
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		int fim=array.length-1;
		doMergeSort(array,0,fim,comparator);
	}
}
