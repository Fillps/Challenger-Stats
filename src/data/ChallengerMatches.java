package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import constants.Patches;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.League;
import net.rithms.riot.api.endpoints.league.dto.LeagueEntry;
import net.rithms.riot.api.endpoints.match.dto.MatchDetail;
import net.rithms.riot.api.endpoints.matchlist.dto.MatchList;
import net.rithms.riot.api.endpoints.matchlist.dto.MatchReference;
import net.rithms.riot.constant.QueueType;
import net.rithms.riot.constant.Region;


public class ChallengerMatches {
	private final RiotApi api;
	private final Region region;
	private final Patches patches;
	
	public ChallengerMatches(RiotApi api, Region region, Patches patches){
		this.api = api;
		this.region = region;
		this.patches = patches;
	}
	public List<MatchDetail> getChallengerMatches() throws RiotApiException, InterruptedException{
		List<Long> matchesIds = new ArrayList<Long>(getChallengerMatchListIds());
		System.out.println(matchesIds.size());
		List<MatchDetail> listMatches = new ArrayList<MatchDetail>();
		MatchDetail matchDetail = null;
		for (int i=0; i<matchesIds.size(); i++){
			try {			
				Thread.sleep(1200);
			} catch (InterruptedException ex){
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
			try {
				matchDetail = api.getMatch(region, matchesIds.get(i), true);
				listMatches.add(matchDetail);
				System.out.println("Game " + i);  
			} catch(RiotApiException e) {
				RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
				if (e.getErrorCode()==429)
					i--;
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
				Thread.sleep(1200);
			} catch (InterruptedException ex){
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
			try {
				matchList = api.getMatchList(region, ids.get(i), "", "", "", patches.getBeginTime(), patches.getEndTime(), -1, -1);
				System.out.println(i);   
			} catch(RiotApiException e) {   
				RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
				if (e.getErrorCode()==429)
					i--;
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
			//RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
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
