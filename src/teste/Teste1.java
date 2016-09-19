package teste;

import java.io.*;
import java.util.Arrays;

import comparators.Comparators;
import data.WriterAndReader;

import net.rithms.riot.api.endpoints.match.dto.*;
import sorting_algorithms.SortingAlgorithms;



public class Teste1 {
	
	public static void main(String[] args) throws IOException{
		System.out.println("Iniciando leitura do arquivo.");
		MatchDetail[] md = WriterAndReader.read("dados\\MatchDetail.V6_17.Reverse.Region.array");
		System.out.println("Leitura concluida.");
		System.out.println("Inicializando array de 100.");
		int size = 100;
		MatchDetail[] a1 = new MatchDetail[size];
		for(int i = 0; i < size; i++)
		    a1[i]=md[i];
		System.out.println("Inicializando array de 1000.");
		size = 1000;
		MatchDetail[] a2 = new MatchDetail[size];
		for(int i = 0; i < size; i++)
		    a2[i]=md[i];
		System.out.println("Inicializando array de 10000.");
		size = 10000;
		MatchDetail[] a3 = new MatchDetail[size];
		for(int i = 0; i < size; i++)
		    a3[i]=md[i];
		String resultados = "dados\\resultados.txt";
		System.out.println("Inicializando testes de desempenho.");
		Testes.testeDeDesempenho(a1, Comparators.matchDetailByRegion(), resultados);
		Testes.testeDeDesempenho(a2, Comparators.matchDetailByRegion(), resultados);
		Testes.testeDeDesempenho(a3, Comparators.matchDetailByRegion(), resultados);
		Testes.testeDeDesempenho(md, Comparators.matchDetailByRegion(), resultados);
		System.out.println("Concluido. Resultados salvos em " + resultados + ".");
	}
}
