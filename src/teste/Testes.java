package teste;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import comparators.ComparatorDigitCatcher;
import sorting_algorithms.BubbleSort;
import sorting_algorithms.HeapSort;
import sorting_algorithms.InsertionSortBuscaBinaria;
import sorting_algorithms.InsertionSortBuscaLinear;
import sorting_algorithms.MergeSort;
import sorting_algorithms.ShellSort;
import sorting_algorithms.SortingAlgorithm;
import sorting_algorithms.SortingAlgorithmNonComparator;
import sorting_algorithms.RadixSort;
import sorting_algorithms.QuickSortRandomizado;

public abstract class Testes {

	/*
	 * verifica se um array esta devidamente ordenado
	 */
	public static <T> boolean testeDeOrdenamento(T[] array, Comparator<T> comparator){
		for (int i = 0; i < (array.length - 1); i++){
			if (comparator.compare(array[i], array[i+1])>0)
				return false;
		}
		return true;
	}
	/*
	 * metodo que testa um determinado array com diversos algoritmos de ordenacao
	 * e salva os resultados em um arquivo txt em um determinado local
	 */
	public static <T> void testeDeDesempenho(T[] array, ComparatorDigitCatcher<T> comparator, String local) throws IOException{
		T[] clone;
		long beginTime;
		long endTime;
		long duration;
		String string;
		FileWriter file = new FileWriter(local, true);
		
		List<SortingAlgorithm> sortingAlgorithms= new ArrayList<SortingAlgorithm>();
		sortingAlgorithms.add(new BubbleSort());
		sortingAlgorithms.add(new HeapSort());
		sortingAlgorithms.add(new InsertionSortBuscaBinaria());
		sortingAlgorithms.add(new InsertionSortBuscaLinear());
		sortingAlgorithms.add(new MergeSort());
		sortingAlgorithms.add(new QuickSortRandomizado());
		sortingAlgorithms.add(new ShellSort());
		
		for (SortingAlgorithm sortingAlgorithm : sortingAlgorithms){
			clone = array.clone();
			beginTime = System.nanoTime();
			sortingAlgorithm.sort_array(clone, comparator);
			endTime = System.nanoTime();
			duration = (endTime - beginTime) / 1000000; // duracao em ms
			string = sortingAlgorithm.getSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
			file.write(string + "\r\n");
			if (!testeDeOrdenamento(clone, comparator)){
				System.out.println(string + " - nao ordenou.");
			}
			else
				System.out.println(string);
		}
		
		SortingAlgorithmNonComparator sortingAlgorithmNonComparator= new RadixSort();
		clone = array.clone();
		beginTime = System.nanoTime();
		sortingAlgorithmNonComparator.sort_array(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000; // duracao em ms
		string = sortingAlgorithmNonComparator.getSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		file.close();
		
	}

}
