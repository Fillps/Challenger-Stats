package teste;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import constants.Patches;
import data.ChallengerIds;
import data.ChallengerMatches;
import data.ChallengerMatchesIds;
import data.WriterAndReader;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.constant.Region;





public class Teste1 {

	public static void main(String[] args) throws RiotApiException, IOException, InterruptedException {
		WriterAndReader wr = new WriterAndReader();
		ChallengerMatches cm = wr.readChallengerMatches("dados\\ChallengerMatches.AllRegions.V6_17-final");
		wr.writeListMatchDetail(cm.getChallengerMatches(), "dados\\ListMatchDetail.AllRegions.V6_17-final");
	}
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
