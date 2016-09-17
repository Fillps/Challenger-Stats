package sorting_algorithms;

import java.util.Comparator;

public class SortingAlgorithms {
	public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator){
		return BubbleSort.sort(array, comparator);
	}
	public static <T> T[] heapSort(T[] array, Comparator<T> comparator){
		return HeapSort.sort(array, comparator);
	}
	public static <T> T[] insertionSortBuscaBinaria(T[] array, Comparator<T> comparator){
		return InsertionSortBuscaBinaria.sort(array, comparator);
	}
	public static <T> T[] insertionSortBuscaLinear(T[] array, Comparator<T> comparator){
		return InsertionSortBuscaLinear.sort(array, comparator);
	}
	public static <T> T[] mergeSort(T[] array, Comparator<T> comparator){
		return MergeSort.sort(array, comparator);
	}
	public static <T> T[] quickSortRandomizado(T[] array, Comparator<T> comparator){
		return QuickSortRandomizado.sort(array, comparator);
	}
	public static <T> T[] radixSort(T[] array, Comparator<T> comparator){
		return RadixSort.sort(array, comparator);
	}
	public static <T> T[] shellSort(T[] array, Comparator<T> comparator){
		return ShellSort.sort(array, comparator);
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
