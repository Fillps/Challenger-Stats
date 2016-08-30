package teste;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import constants.Patches;
import data.ChallengerMatches;
import net.rithms.riot.constant.*;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.api.*;

public class Teste1 {

	public static void main(String[] args) throws RiotApiException, IOException {
		
		RiotApi api = new RiotApi(readFile("Key\\key.txt", Charset.defaultCharset()));
		
		//MatchList matchList = api.getMatchList(Region.BR, id, "", "", "",Patches.V6_16.getBeginTime(), Patches.V6_16.getEndTime(), -1, -1);
		ChallengerMatches c = new ChallengerMatches(api,Region.BR,Patches.V6_15);
		System.out.println(c.getChallengerMatchList().size());
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
