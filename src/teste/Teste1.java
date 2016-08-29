package teste;
import data.ChallengerIds;
import net.rithms.riot.constant.*;
import net.rithms.riot.api.*;

public class Teste1 {

	public static void main(String[] args) throws RiotApiException {
		RiotApi api = new RiotApi("RGAPI-BADC21EF-F8A5-4707-A95C-3CD50D50EFD7");
		ChallengerIds challengerIds = new ChallengerIds(api,Region.BR);
		String[] ids = challengerIds.getChallengerIds();
		for (int i=0; i<ids.length;i++){
			System.out.println(ids[i]);
		}
	}
}
