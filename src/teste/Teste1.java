package teste;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import data.ChallengerIds;
import net.rithms.riot.constant.*;
import net.rithms.riot.api.*;

public class Teste1 {

	public static void main(String[] args) throws RiotApiException, IOException {
		
		RiotApi api = new RiotApi(readFile("Key\\key.txt", Charset.defaultCharset()));
		ChallengerIds challengerIds = new ChallengerIds(api,Region.BR);
		String[] ids = challengerIds.getChallengerIds();
		for (int i=0; i<ids.length;i++){
			System.out.println(ids[i]);
		}
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
