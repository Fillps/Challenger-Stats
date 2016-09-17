package sorting_algorithms;

import java.util.Comparator;

public class SortingAlgorithms {
	public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator){
		return BubbleSort.sort(array, comparator);
	}
}
