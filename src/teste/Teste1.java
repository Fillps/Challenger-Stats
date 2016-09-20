package teste;

import java.io.*;
import java.util.Comparator;

import comparators.Comparators;
import data.WriterAndReader;

import net.rithms.riot.api.endpoints.match.dto.*;
import sorting_algorithms.SortingAlgorithms;



public class Teste1 {
	
	public static void main(String[] args) throws IOException{
		String resultados = "dados\\resultados_challenger_stats.txt";
		String local = "dados\\MatchDetail.V6_17.Reverse.Id.array";
		Comparator<MatchDetail> comparator = Comparators.matchDetailById();
		
		System.out.println("Iniciando testes do array numerico e categorico.");
		for (int k = 0; k < 2; k++){
			System.out.println("Iniciando leitura do arquivo.");
			MatchDetail[] array = WriterAndReader.read(local);
			System.out.println("Leitura concluida.");
			
			if (Testes.testeDeOrdenamento(array, comparator.reversed()))
				System.out.println("O array ja esta inversamente ordenado.");
			else {
				System.out.println("O array nao esta inversamente ordenado. Iniciando ordenacao inversa do array.");
				SortingAlgorithms.bubbleSort(array, comparator.reversed());
				System.out.println("Salvando array.");
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
			System.out.println("Concluido. Resultados salvos em " + resultados + ".");
		
			local = "dados\\MatchDetail.V6_17.Reverse.FirstPlayerName.array";
			comparator = Comparators.matchDetailByFirstPlayerName();
		}
	}
}
