package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import constants.Patches;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.constant.QueueType;
import net.rithms.riot.constant.Region;
import net.rithms.riot.dto.League.League;
import net.rithms.riot.dto.League.LeagueEntry;
import net.rithms.riot.dto.Match.MatchDetail;
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
	public List<MatchDetail> getChallengerMatches() throws RiotApiException, InterruptedException{
		Set<Long> matchesIds = getChallengerMatchListIds();
		Iterator<Long> it = matchesIds.iterator();
		List<MatchDetail> listMatches = new ArrayList<MatchDetail>();
		MatchDetail matchDetail = null;
		int i = 1;
		while (it.hasNext()){
			try {
				matchDetail = api.getMatch(region, it.next(), true);
				listMatches.add(matchDetail);
				System.out.println("Game " + i++);
			} catch(RiotApiException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		return listMatches;
	}
	
	private Set<Long> getChallengerMatchListIds() throws RiotApiException, InterruptedException{
		List<Long> ids = getChallengerIds();
		List<Long> matchesIds = new ArrayList<Long>();
		MatchList matchList = null;
		for(int i=0; i<ids.size();i++){
			try {
				matchList = api.getMatchList(region, ids.get(i), "", "", "", patches.getBeginTime(), patches.getEndTime(), -1, -1);
			} catch(RiotApiException e) {
				e.printStackTrace();
			    Thread.currentThread().interrupt();
			}              
			if (matchList!=null){
				List<MatchReference> matchReferences = matchList.getMatches();
				if (matchReferences!=null){
					for(int j=0; j<matchReferences.size();j++){
						matchesIds.add(matchReferences.get(j).getMatchId());
					}
				}
			}
		}
		Set<Long> setMatches = new HashSet<Long>(matchesIds);
		return setMatches;
	}
	
	
	private List<Long> getChallengerIds() throws RiotApiException{
		League challenger = null;
		try {
			challenger = api.getChallengerLeague(region, QueueType.RANKED_SOLO_5x5);
		} catch(RiotApiException e) {
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
