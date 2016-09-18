package teste;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import sorting_algorithms.SortingAlgorithms;

/*
 * classe que testa um determinado array com diversos algoritmos de ordenacao
 * e salva os resultados em um arquivo txt em um determinado local
 */
public class TesteDeDesempenho {
	public <T> TesteDeDesempenho(T[] array, Comparator<T> comparator, String local) throws IOException{
		long beginTime;
		long endTime;
		long duration;
		String string;
		FileWriter file = new FileWriter(local, true);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.bubbleSort(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000; // duracao em ms
		string = SortingAlgorithms.bubbleSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.heapSort(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.heapSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.insertionSortBuscaBinaria(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.insertionSortBuscaBinariaSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.insertionSortBuscaLinear(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.insertionSortBuscaLinearSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.mergeSort(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.mergeSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.quickSortRandomizado(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.quickSortRandomizadoSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.radixSort(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.radixSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		beginTime = System.nanoTime();
		SortingAlgorithms.shellSort(array, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.shellSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration + "\n";
		file.write(string);
		
		file.close();
		
	}
}
