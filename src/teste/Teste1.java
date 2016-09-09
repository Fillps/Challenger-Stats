package teste;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import java.io.Serializable;

import constants.Patches;
import data.ChallengerMatches;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.constant.Region;





public class Teste1 {

	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		ApiConfig config = new ApiConfig().setKey(readFile("Key\\key.txt", Charset.defaultCharset()));
		RiotApi api = new RiotApi(config);
		
		//Summoner a = api.getSummonerByName(Region.BR, "fillps");
		//System.out.println(a.getName());
		//MatchList matchList = api.getMatchList(Region.BR, id, "", "", "",Patches.V6_16.getBeginTime(), Patches.V6_16.getEndTime(), -1, -1);
		//ChallengerMatches c = new ChallengerMatches(api,Region.BR,Patches.V6_15);
		
		ChallengerMatches challengerMatches = new ChallengerMatches(api, Region.BR, Patches.V6_15);
		List<MatchDetail> list = challengerMatches.getChallengerMatches();
		escrever(list, "ChallegerMatches.BR.V6_15");
		
		//List<MatchDetail> list = ler("ChallegerMatches.NA.V6_15");
		//System.out.println(list.size());
		
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	static List<MatchDetail> ler(String local){
		List<MatchDetail> list = null;
		try {
			FileInputStream in = new FileInputStream(local);
			ObjectInputStream ois = new ObjectInputStream(in);
			list = (List<MatchDetail>) (ois.readObject());
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	static void escrever( List<MatchDetail> list, String local){
		
		try {
			FileOutputStream out = new FileOutputStream(local);
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(list);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

