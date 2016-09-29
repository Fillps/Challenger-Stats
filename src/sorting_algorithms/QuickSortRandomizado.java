package sorting_algorithms;

import java.util.Comparator;
import java.util.Random;

public class QuickSortRandomizado extends SortingAlgorithm {
	public static final String sigla = "QSRM";

	public QuickSortRandomizado(){
		super(sigla);
	}

	public <T> void sort_array(T[] array, Comparator<T> comparator){
		sort(array,comparator);
	}

    public static <T> int particiona(T[] array, int inf, int sup, Comparator<T> comparator) {
            int pp = inf; //array[0]
            int i = inf + 1; //array[1]
            int j = sup; //array.length - 1

            while(j > i) {
                while((comparator.compare(array[i], array[pp]) < 0) && (i < pf)) i++;
                while((comparator.compare(array[j], array[pp]) > 0) && (j > pi)) j--;
                if(comparator.compare(array[i], array[j]) > 0) {
                    swap(array, i, j);
                }
            }
            swap(array, j, pp);
            return j;
    }


    public static <T> void quicksort(T[] array, int inf, int sup, Comparator<T> comparator){
        //int inf = array[0];
        //int sup = array.length - 1;
        int pivo;
		if (sup > inf){
            pivo = particiona(array, inf, sup);
            quicksort(array, inf, pivo - 1);
            quicksort(array, pivo + 1, sup);
		}

    }

    public static <T> int particaoRandomizada(T[] array, int inf, int sup, Comparator<T> comparator) {
        int r;
        Random gerador = new Random();
        r = gerador.nextInt(sup+1)
        swap(array, i, r);
        return particiona(array, inf, sup);
    }

    public static <T> void sort(T[] array, int inf, int sup, Comparator<T> comparator){
        int k;
        if(f > i) {
            k = particaoRandomizada(array, inf, sup);
            quicksort(array, inf, k-1);
            quicksort(array, k+1, sup);
        }
    }

}
