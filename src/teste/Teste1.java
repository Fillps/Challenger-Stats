package teste;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.WriterAndReader;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;





public class Teste1 {
	
	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		MatchDetail[] md = WriterAndReader.read("dados\\MatchDetail.V6_17.Reverse.Region.array");
		System.out.println(md.length);
		WriterAndReader.write(md, "dados\\MatchDetail.V6_17.Reverse.Region.array-teste");
		System.out.println("salvo");
		MatchDetail[] md2 = WriterAndReader.read("dados\\MatchDetail.V6_17.Reverse.Region.array-teste");
		System.out.println(md2.length);
		/*List<MatchDetail> cm = wr.read("dados\\MatchDetail.V6_17.Reverse.Region.list");
		cm.sort(new MatchDetailComparatorByPatch());
		List<String> strings = new ArrayList<String>();
		List<Integer> ints = new ArrayList<Integer>();
		for (int i =0 ; i<cm.size();i++){
			int j = strings.indexOf(cm.get(i).getMatchVersion());
			if (j == -1) {
				strings.add(cm.get(i).getMatchVersion());
				ints.add(1);
			} else {
				ints.set(j, ints.get(j)+1);
			}
		}
		for (int i = 0 ; i < strings.size() ; i++){
			System.out.println(strings.get(i) + " - " + ints.get(i));
		}*/
		
	}
}
