package sorting_algorithms;

import java.util.Comparator;

public class SortingAlgorithms {
	public static <T> void bubbleSort(T[] array, Comparator<T> comparator){
		BubbleSort.sort(array, comparator);
	}
	public static <T> void heapSort(T[] array, Comparator<T> comparator){
		HeapSort.sort(array, comparator);
	}
	public static <T> void insertionSortBuscaBinaria(T[] array, Comparator<T> comparator){
		InsertionSortBuscaBinaria.sort(array, comparator);
	}
	public static <T> void insertionSortBuscaLinear(T[] array, Comparator<T> comparator){
		InsertionSortBuscaLinear.sort(array, comparator);
	}
	public static <T> void mergeSort(T[] array, Comparator<T> comparator){
		MergeSort.sort(array, comparator);
	}
	public static <T> void quickSortRandomizado(T[] array, Comparator<T> comparator){
		QuickSortRandomizado.sort(array, comparator);
	}
	public static <T> void radixSort(T[] array, Comparator<T> comparator){
		RadixSort.sort(array, comparator);
	}
	public static <T> void shellSort(T[] array, Comparator<T> comparator){
		ShellSort.sort(array, comparator);
	}
	public static String bubbleSortSigla(){
		return BubbleSort.sigla;
	}
	public static String heapSortSigla(){
		return HeapSort.sigla;
	}
	public static String insertionSortBuscaBinariaSigla(){
		return InsertionSortBuscaBinaria.sigla;
	}
	public static String insertionSortBuscaLinearSigla(){
		return InsertionSortBuscaLinear.sigla;
	}
	public static String mergeSortSigla(){
		return MergeSort.sigla;
	}
	public static String quickSortRandomizadoSigla(){
		return QuickSortRandomizado.sigla;
	}
	public static String radixSortSigla(){
		return RadixSort.sigla;
	}
	public static String shellSortSigla(){
		return ShellSort.sigla;
	}
}
