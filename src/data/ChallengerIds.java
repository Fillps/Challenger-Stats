package data;

import java.util.Iterator;
import java.util.List;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.constant.QueueType;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;
import net.rithms.riot.api.RiotApiException;

/**
 * 
 * @author filip
 * 
 * ChallengerIds pega todos os IDs da liga challenger ranked solo 5x5 de uma determinada regiao
 *	e armazena em um array;
 */
public class ChallengerIds {

	private String[] ids;
	
	public ChallengerIds(RiotApi api, Region region) throws RiotApiException{
		
		League challenger = api.getChallengerLeague(region, QueueType.RANKED_SOLO_5x5);
		List<LeagueEntry> challengerEntry = challenger.getEntries();
    	Iterator<LeagueEntry> it = challengerEntry.iterator();
    	LeagueEntry entry;
    	ids = new String[challengerEntry.size()];
    	int i = 0;
    	while(it.hasNext()){
    		entry = it.next();
    		ids[i]=entry.getPlayerOrTeamId();
    		i++;
    	}
	}
	public String[] getChallengerIds(){
		return ids;
	}
}