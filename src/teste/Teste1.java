package teste;

import java.io.*;

import data.Comparators;
import data.WriterAndReader;

import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;



public class Teste1 {
	
	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		String resultados = "dados\\resultados.txt";
		MatchDetail[] md = WriterAndReader.read("dados\\MatchDetail.V6_17.Reverse.Id.array");
		MatchDetail[] a100 = new MatchDetail[100];
		for(int i = 0; i < 100; i++)
		    a100[i]=md[i];
		MatchDetail[] a1000 = new MatchDetail[1000];
		for(int i = 0; i < 1000; i++)
		    a1000[i]=md[i];
		MatchDetail[] a10000 = new MatchDetail[10000];
		for(int i = 0; i < 10000; i++)
		    a10000[i]=md[i];
		TesteDeDesempenho td1 = new TesteDeDesempenho(a100, Comparators.matchDetailById(), resultados);
		TesteDeDesempenho td2 = new TesteDeDesempenho(a1000, Comparators.matchDetailById(), resultados);
		TesteDeDesempenho td3 = new TesteDeDesempenho(a10000, Comparators.matchDetailById(), resultados);
	}
}
