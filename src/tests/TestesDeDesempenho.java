package tests;

import java.io.IOException;

import model.sorting_algorithms.comparators.ComparatorDigitCatcher;
import model.sorting_algorithms.comparators.Comparators;
import model.database.files.WriterAndReader;

import net.rithms.riot.api.endpoints.match.dto.*;
import model.sorting_algorithms.MergeSort;


/*
 * ETAPA II: TESTES DE DESEMPENHO (ATE 02/10/2016)
 * 
 * Desenvolvimento e tests de desempenho de algoritmos de classificacao
 * Preparar o subconjunto de dados, criando duas versoes (ambas ordenadas de maneira decrescente): 
 * uma ordenada pelo atributo numerico e outra ordenada pelo atributo categorico. Salve em arquivos 
 * para poder carregar esses dados para depois nao precisar refazer a ordenacao.
 * Implementar pelo menos os seguintes algoritmos:
 * 		Insertion Sort com Busca Linear (ISBL)
 *		Insertion Sort com Busca Binaria (ISBB)
 * 		Shell Sort (SHST)
 * 		Bubble Sort (BBST)
 * 		Quick Sort Randomizado (QSRM)
 * 		Merge Sort (MGST)
 * 		Heap Sort (HPST)
 * 		Radix Sort MSD (RMSD) 
 * Para todos os algoritmos, contabilizar o tempo de execucao para pelo menos 3 tamanhos de array, tanto numerico quanto categorico:
 * 100 elementos
 * 1.000 elementos
 * 10.000 elementos
 * 100.000 elementos (opcional)
 * 1.000.000 elementos (opcional)
 *  
 * Formato dos resultados:
 * BBST, numerico, 1000, 10.
 * BBST, numerico, 10000, 100.
 * BBST, categorico, 1000, 70.
 * ISBL, categorico, 1000, 1250.
 *
 */
public class TestesDeDesempenho {
	
	public TestesDeDesempenho() throws IOException{
		String resultados = "arquivos\\resultados_challenger_stats.txt";
		String local1 = "arquivos\\MatchDetail.V6_17.Reverse.Id.array.ser";
		String local2 = "arquivos\\MatchDetail.V6_17.Reverse.FirstPlayerName.array.ser";
		ComparatorDigitCatcher<MatchDetail> comparator1 = Comparators.matchDetailById();
		ComparatorDigitCatcher<MatchDetail> comparator2 = Comparators.matchDetailByFirstPlayerName();
		
		System.out.println("Iniciando testes do array:\n"
				+ "1 - " + comparator1.toString() + " \"" + local1 + "\"\n"
				+ "2 - " + comparator2.toString() + " \"" + local2 + "\"");
		for (int k = 1; k < 3; k++){
			System.out.println("Iniciando leitura do arquivo " + k + ".");
			MatchDetail[] array = WriterAndReader.read(local1);
			System.out.println("Leitura concluida.");
			if (Testes.testeDeOrdenamento(array, comparator1.reversedDigitCatcher()))
				System.out.println("O array ja esta inversamente ordenado.");
			else {
				System.out.println("O array nao esta inversamente ordenado. Iniciando ordenacao inversa do array.");
				MergeSort.sort(array, comparator1.reversedDigitCatcher());
				System.out.println("Salvando array em \"" + local1.substring(0, local1.length() - 4) + ".reversed.ser\".");
				WriterAndReader.write(array, (local1.substring(0, local1.length() - 4) + ".reversed.ser"));
				System.out.println("Salvo.");
			}
			
			System.out.println("Inicializando array de 100.");
			int size = 100;
			MatchDetail[] a1 = new MatchDetail[size];
			for(int i = 0; i < size; i++)
			    a1[i]=array[i];
			
			System.out.println("Inicializando array de 1000.");
			size = 1000;
			MatchDetail[] a2 = new MatchDetail[size];
			for(int i = 0; i < size; i++)
			    a2[i]=array[i];
			
			System.out.println("Inicializando array de 10000.");
			size = 10000;
			MatchDetail[] a3 = new MatchDetail[size];
			for(int i = 0; i < size; i++)
			    a3[i]=array[i];
			
			System.out.println("Inicializando testes de desempenho.");
			Testes.testeDeDesempenho(a1, comparator1, resultados);
			Testes.testeDeDesempenho(a2, comparator1, resultados);
			Testes.testeDeDesempenho(a3, comparator1, resultados);
			Testes.testeDeDesempenho(array, comparator1, resultados);
			System.out.println("Teste " + k + " concluido. Resultados salvos em \"" + resultados + "\".");
		
			local1 = local2;
			comparator1 = comparator2;
		}
	}
}
