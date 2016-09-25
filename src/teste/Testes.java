package teste;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import sorting_algorithms.BubbleSort;
import sorting_algorithms.HeapSort;
import sorting_algorithms.InsertionSortBuscaBinaria;
import sorting_algorithms.InsertionSortBuscaLinear;
import sorting_algorithms.MergeSort;
import sorting_algorithms.ShellSort;
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
	public static <T> void testeDeDesempenho(T[] array, Comparator<T> comparator, String local) throws IOException{
		T[] clone;
		long beginTime;
		long endTime;
		long duration;
		String string;
		FileWriter file = new FileWriter(local, true);
		
		
		clone = array.clone();
		beginTime = System.nanoTime();
		BubbleSort.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000; // duracao em ms
		string = BubbleSort.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		HeapSort.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = HeapSort.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		InsertionSortBuscaBinaria.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = InsertionSortBuscaBinaria.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		InsertionSortBuscaLinear.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = InsertionSortBuscaLinear.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		MergeSort.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = MergeSort.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		QuickSortRandomizado.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = QuickSortRandomizado.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		RadixSort.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = RadixSort.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		clone = array.clone();
		beginTime = System.nanoTime();
		ShellSort.sort(clone, comparator);
		endTime = System.nanoTime();
		duration = (endTime - beginTime) / 1000000;
		string = ShellSort.sigla + ", " + comparator.toString() + ", " + array.length + ", " + duration;
		file.write(string + "\r\n");
		if (!testeDeOrdenamento(clone, comparator)){
			System.out.println(string + " - nao ordenou.");
		}
		else
			System.out.println(string);
		
		file.close();
		
	}

}
