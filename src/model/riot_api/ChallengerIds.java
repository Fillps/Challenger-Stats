package model.riot_api;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;

import model.riot_api.constants.Patches;
import net.rithms.riot.api.RiotApi;
//import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.dto.League;
import net.rithms.riot.api.endpoints.league.dto.LeagueEntry;
//import net.rithms.riot.api.request.AsyncRequest;
//import net.rithms.riot.api.request.RequestAdapter;
import net.rithms.riot.constant.QueueType;
import net.rithms.riot.constant.Region;



/*
 * Classe que permite armazenar ou obter as Ids de todos os jogadores Challengers de uma determinada lista de regioes
 * 
 */

public class ChallengerIds implements Serializable{
	
	private static final long serialVersionUID = -636600274741439321L;
	protected static final int sleepTime = 1200;
	protected List<Region> regions;
	protected Patches patch;
	protected List<Queue<Long>> listChallengerIds;
	
	public ChallengerIds(RiotApi api, List<Region> regions, Patches patch) throws RiotApiException, InterruptedException{
		this.regions = regions;
		this.patch = patch;
		methodListChallengerIds(api);
	}
	
	public ChallengerIds(List<Region> regions, Patches patch, List<Queue<Long>> listChallengerIds){
		this.regions = regions;
		this.patch = patch;
		this.listChallengerIds = listChallengerIds;
	}
	

	public List<Region> getRegions(){
		return this.regions;
	}
	
	public Patches getPatch(){
		return this.patch;
	}
	
	public List<Queue<Long>> getListChallengerIds(){
		return this.listChallengerIds;
	}
	
	/*
	 * usando a RiotApi, se obtem o challengers ids de todas as regioes na lista
	 */
	private void methodListChallengerIds(RiotApi api) throws RiotApiException, InterruptedException{
		
		List<Queue<Long>> listIds= new ArrayList<Queue<Long>>();
		for (int i = 0 ; i<this.regions.size(); i++){
			try{
				League challenger = api.getChallengerLeague(this.regions.get(i), QueueType.RANKED_SOLO_5x5);
				List<LeagueEntry> challengerEntry = challenger.getEntries();
				Iterator<LeagueEntry> it = challengerEntry.iterator();
				LeagueEntry entry;
				Queue<Long> ids = new LinkedList<Long>();
				while(it.hasNext()){
				    entry = it.next();
				   	ids.add(Long.valueOf(entry.getPlayerOrTeamId()));
				}
				listIds.add(ids);	
			} catch(RiotApiException e){
				RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
				Thread.currentThread().interrupt();
			}
		}
		try {			
			Thread.sleep(sleepTime);
		} catch (InterruptedException ex){
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		this.listChallengerIds = listIds;
		
	}
	
	
}
