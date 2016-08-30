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
	
	public List<Long> getChallengerMatchList() throws RiotApiException, InterruptedException{
		List<Long> ids = getChallengerIds();
		List<Long> matchesIds = new ArrayList<Long>();
		MatchList matchList = null;
		for(int i=0; i<ids.size();i++){
			try {
				matchList = api.getMatchList(region, ids.get(i), "", "", "", patches.getBeginTime(), patches.getEndTime(), -1, -1);
				System.out.println(i+1);
			} catch(RiotApiException e) {
				e.printStackTrace();
			    Thread.currentThread().interrupt();
			}               //1000 milliseconds is one second.
			if (matchList!=null){
				List<MatchReference> matchReferences = matchList.getMatches();
				if (matchReferences!=null){
					for(int j=0; j<matchReferences.size();j++){
						matchesIds.add(matchReferences.get(j).getMatchId());
					}
				}
			}
		}
		return matchesIds;
	}
	
	
	private List<Long> getChallengerIds() throws RiotApiException{
		League challenger = null;
		try {
			challenger = api.getChallengerLeague(region, QueueType.RANKED_SOLO_5x5);
		} catch(InterruptedException e) {
			e.printStackTrace();
		    Thread.currentThread().interrupt();
		}
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
