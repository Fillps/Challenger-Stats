package teste;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import sorting_algorithms.SortingAlgorithms;

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
	public static <T> void testeDeDesempenho(T[] array, Comparator<T> comparator, String local) throws IOException{
		T[] clone;
		long beginTime;
		long endTime;
		long duration;
		String string;
		FileWriter file = new FileWriter(local, true);
		
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.bubbleSort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000; // duracao em ms
		string = SortingAlgorithms.bubbleSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.heapSort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.heapSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.insertionSortBuscaBinaria(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.insertionSortBuscaBinariaSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.insertionSortBuscaLinear(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.insertionSortBuscaLinearSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.mergeSort(array.clone(), comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.mergeSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.quickSortRandomizado(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.quickSortRandomizadoSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.radixSort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.radixSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		SortingAlgorithms.shellSort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = SortingAlgorithms.shellSortSigla() + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		file.close();
		
	}

}
