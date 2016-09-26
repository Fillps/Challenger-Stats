

import java.io.IOException;
import java.util.Comparator;

import comparators.Comparators;
import data.WriterAndReader;
import teste.Testes;

import net.rithms.riot.api.endpoints.match.dto.*;
import sorting_algorithms.HeapSort;


/*
 * ETAPA II: TESTES DE DESEMPENHO (ATÉ 02/10/2016)
 * 
 * Desenvolvimento e teste de desempenho de algoritmos de classificação
 * Preparar o subconjunto de dados, criando duas versões (ambas ordenadas de maneira decrescente): 
 * uma ordenada pelo atributo numérico e outra ordenada pelo atributo categórico. Salve em arquivos 
 * para poder carregar esses dados para depois não precisar refazer a ordenação.
 * Implementar pelo menos os seguintes algoritmos:
 * 		Insertion Sort com Busca Linear (ISBL)
 *		Insertion Sort com Busca Binária (ISBB)
 * 		Shell Sort (SHST)
 * 		Bubble Sort (BBST)
 * 		Quick Sort Randomizado (QSRM)
 * 		Merge Sort (MGST)
 * 		Heap Sort (HPST)
 * 		Radix Sort MSD (RMSD) 
 * Para todos os algoritmos, contabilizar o tempo de execução para pelo menos 3 tamanhos de array, tanto numérico quanto categórico:
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
	
	public static void main(String[] args) throws IOException{
		String resultados = "dados\\resultados_challenger_stats.txt";
		String local = "dados\\MatchDetail.V6_17.Reverse.Id.array";
		Comparator<MatchDetail> comparator = Comparators.matchDetailById();
		
		System.out.println("Iniciando testes do array:\n"
				+ "1 - Numerico \"dados\\MatchDetail.V6_17.Reverse.Id.array\"\n"
				+ "2 - Categorico \"dados\\MatchDetail.V6_17.Reverse.FirstPlayerName.array\"");
		for (int k = 1; k < 3; k++){
			System.out.println("Iniciando leitura do arquivo " + k + ".");
			MatchDetail[] array = WriterAndReader.read(local);
			System.out.println("Leitura concluida.");
			
			if (Testes.testeDeOrdenamento(array, comparator.reversed()))
				System.out.println("O array ja esta inversamente ordenado.");
			else {
				System.out.println("O array nao esta inversamente ordenado. Iniciando ordenacao inversa do array.");
				HeapSort.sort(array, comparator.reversed());
				System.out.println("Salvando array em \"" + local + ".Reversed\".");
				WriterAndReader.write(array, (local + ".Reversed"));
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
			Testes.testeDeDesempenho(a1, comparator, resultados);
			Testes.testeDeDesempenho(a2, comparator, resultados);
			Testes.testeDeDesempenho(a3, comparator, resultados);
			Testes.testeDeDesempenho(array, comparator, resultados);
			System.out.println("Teste " + k + " concluido. Resultados salvos em \"" + resultados + "\".");
		
			local = "dados\\MatchDetail.V6_17.Reverse.FirstPlayerName.array";
			comparator = Comparators.matchDetailByFirstPlayerName();
		}
	}
}
