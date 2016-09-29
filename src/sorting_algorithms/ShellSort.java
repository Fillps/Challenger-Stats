package sorting_algorithms;

import java.util.Comparator;

public class ShellSort extends SortingAlgorithm {
	public static final String sigla = "SHST";

	public ShellSort(){
		super(sigla);
	}

	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}

	public static <T> void sort(T[] array, Comparator<T> comparator){
	    int h = 1;
		int n = array.length;

		while(h < n) h = h * 3 + 1;
		h = h / 3;
		int j;
		T chave;

		while (h > 0) {
            for(int i = h; i < n; i++){
                chave = array[i];
                j = i;
                while ((j >= h) && (comparator.compare(array[j-h], chave) > 0)) {
                    swap(array, j, j-h);
                    j = j - h;
                }
                array[j] = chave;
            }
            h = h / 2;
		}
     }
}