package model.sorting_algorithms;

import java.util.Comparator;

public abstract class SortingAlgorithm {
	
	private String sigla;
	
	public SortingAlgorithm(String sigla){
		this.sigla = sigla;
	}
	
	public static <T> void swap(T[] array,int i,int j){
		T aux=array[i];
		array[i]=array[j];
		array[j]=aux;	
	}
	
	public abstract <T> void sort_array(T[] array, Comparator<T> comparator);
	
	public String getSigla(){
		return this.sigla;
	}
	
}
