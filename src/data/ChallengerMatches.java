package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import constants.Patches;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.QueueType;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;
import net.rithms.riot.dto.MatchList.MatchList;
import net.rithms.riot.dto.MatchList.MatchReference;

public class ChallengerMatches {
	private RiotApi api;
	private Region region;
	private Patches patches;
	
	public ChallengerMatches(RiotApi api, Region region, Patches patches){
		this.api = api;
		this.region = region;
		this.patches = patches;
	}
	
	public List<Long> getChallengerMatchList() throws RiotApiException{
		List<Long> ids = getChallengerIds();
		List<Long> matchesIds = new ArrayList<Long>();
		MatchList matchList;
		for(int i=0; i<ids.size();i++){
			matchList = api.getMatchList(region, ids.get(i), "", "", "", patches.getBeginTime(), patches.getEndTime(), -1, -1);
			List<MatchReference> matchReferences = matchList.getMatches();
			for(int j=0; j<matchReferences.size();j++){
				matchesIds.add(matchReferences.get(j).getMatchId());
			}
		}
		return matchesIds;
	}
	
	private List<Long> getChallengerIds() throws RiotApiException{
		League challenger = api.getChallengerLeague(region, QueueType.RANKED_SOLO_5x5);
		List<LeagueEntry> challengerEntry = challenger.getEntries();
    	Iterator<LeagueEntry> it = challengerEntry.iterator();
    	LeagueEntry entry;
    	List<Long> ids = new ArrayList<Long>();
    	
    	while(it.hasNext()){
    		entry = it.next();
    		ids.add(Long.valueOf(entry.getPlayerOrTeamId()));
    	}
		return ids;
	}
}
