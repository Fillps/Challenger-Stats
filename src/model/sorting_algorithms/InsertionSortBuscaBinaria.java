package model.sorting_algorithms;

import java.util.Comparator;

public class InsertionSortBuscaBinaria extends SortingAlgorithm {
	public static final String sigla = "ISBB";
	
	public InsertionSortBuscaBinaria(){
		super(sigla);
	}
	
	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}
	
	private static <T> int buscaBinaria(T[] array,T chave,int inf,int sup,Comparator<T> comparator){
		int meio;
		
		while(inf<=sup){
			meio=(inf+sup)/2;
			if(comparator.compare(chave,array[meio])>0)
				inf=meio+1;
			else if(comparator.compare(chave,array[meio])<0)
				sup=meio-1;
			else
				return meio;
		}
		return inf;	
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator){
		int pos;
		T chave;
		
		for(int i=1; i<array.length; i++){
			chave=array[i];
			pos=buscaBinaria(array,chave,0,i-1,comparator);
			for(int j=i; j>pos; j--)
				array[j]=array[j-1];			
			array[pos]=chave;
		}	
	}
}
