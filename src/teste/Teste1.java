package teste;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import constants.Patches;
import data.ChallengerMatches;
import net.rithms.riot.constant.*;
import net.rithms.riot.api.*;

public class Teste1 {

	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		
		RiotApi api = new RiotApi(readFile("Key\\key.txt", Charset.defaultCharset()));
		
		//MatchList matchList = api.getMatchList(Region.BR, id, "", "", "",Patches.V6_16.getBeginTime(), Patches.V6_16.getEndTime(), -1, -1);
		ChallengerMatches c = new ChallengerMatches(api,Region.EUW,Patches.V6_15);
		System.out.println(c.getChallengerMatchList().size());//6917 jogos sem Set<> 1791 NA / 2134 BR / 2610 KR / 2368 EUW
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
