package sorting_algorithms;

import java.util.Comparator;
import java.util.Random;

public class QuickSortRandomizado extends SortingAlgorithm {
	public static final String sigla = "QSRM";

	private static Random random = new Random();

	public QuickSortRandomizado() {
		super(sigla);
	}

	public <T> void sort_array(T[] array, Comparator<T> comparator) {
		sort(array, comparator);
	}
	
	public static <T> void sort(T[] array, Comparator<T> comparator) {
		quickSort(array, comparator, 0, array.length - 1);
	}
	
	public static <T> void quickSort(T[] array, Comparator<T> comparator, int inferiorIndex, int superiorIndex) {
		if (superiorIndex > inferiorIndex) {
			int pivotIndex = random.nextInt(superiorIndex - inferiorIndex) + inferiorIndex;
			pivotIndex = particiona(array, comparator, inferiorIndex, superiorIndex, pivotIndex);
			quickSort(array, comparator, inferiorIndex, pivotIndex - 1);
			quickSort(array, comparator, pivotIndex + 1, superiorIndex);
		}
	}
	
	private static <T> int particiona(T[] array, Comparator<T> comparator, int inferiorIndex, int superiorIndex, int pivotIndex) {
		T pivot = array[pivotIndex];
		swap(array, pivotIndex, superiorIndex);
		int index = inferiorIndex;
		for (int i = inferiorIndex; i < superiorIndex; i++) {
			if (comparator.compare(array[i], pivot) <= 0) {
				swap(array, i, index);
				index++;
			}
		}
		swap(array, superiorIndex, index);
		return index;
	}

	

}
