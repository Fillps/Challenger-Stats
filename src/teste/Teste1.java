package teste;

import java.io.*;

import data.Comparators;
import data.WriterAndReader;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import sorting_algorithms.SortingAlgorithms;





public class Teste1 {
	
	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		MatchDetail[] md = WriterAndReader.read("dados\\MatchDetail.V6_17.Reverse.Id.array");
		MatchDetail[] out = new MatchDetail[200];
		for(int i = 0; i < 200; i++)
		    out[i]=md[i];
		
		long start = System.nanoTime();
		SortingAlgorithms.bubbleSort(out, Comparators.matchDetailById());
		long end = System.nanoTime();
		long duration = (end - start) / 1000000; //em milisegundos
		System.out.println("Duracao: " + duration + "ms");
		for(int i = 0; i < 200; i++)
		    System.out.println(out[i].getMatchId());
	}
}
