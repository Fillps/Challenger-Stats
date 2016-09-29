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

	private static <T> int particiona(T[] array, Comparator<T> comp, int inf, int sup, int pivotIndex) {
		T pivot = array[pivotIndex];
		swap(array, pivotIndex, sup);
		int index = inf;
		for (int i = inf; i < sup; i++) {
			if (comp.compare(array[i], pivot) <= 0) {
				swap(array, i, index);
				index++;
			}
		}
		swap(array, sup, index);
		return index;
	}

	public static <T> void sort(T[] array, Comparator<T> comparator) {
		sort(array, comparator, 0, array.length - 1);
	}

	public static <T> void sort(T[] array, Comparator<T> comparator, int inf, int sup) {
		if (sup > inf) {
			int pivotIndex = random.nextInt(sup - inf) + inf;
			pivotIndex = particiona(array, comparator, inf, sup, pivotIndex);
			sort(array, comparator, inf, pivotIndex - 1);
			sort(array, comparator, pivotIndex + 1, sup);
		}
	}

}
