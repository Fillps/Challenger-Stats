package model.riot_api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;


import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.RiotApiException;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;

/*
 * Classe que permite armazenar ou obter, com todos os ids das partidas, 
 * os detalhes das partidas ranqueadas na sua determinada regiao,
 * armazenado os detalhes numa lista.
 */
public class ChallengerMatches extends ChallengerMatchesIds{
	
	private static final long serialVersionUID = 3144184202985087439L;
	private List<MatchDetail> listChallengerMatches;
	private List<Queue<Long>> matchesIdsDeleted;
	
	public ChallengerMatches(RiotApi api, ChallengerMatchesIds challengerMatchesIds) throws RiotApiException, InterruptedException{
		super(challengerMatchesIds, challengerMatchesIds.matchesIds, challengerMatchesIds.idsDeleted);
		this.listChallengerMatches = null;
		this.matchesIdsDeleted = null;
		methodChallengerMatches(api);
	}
	
	public ChallengerMatches(ChallengerMatchesIds challengerMatchesIds, List<MatchDetail> listChallengerMatches, List<Queue<Long>> matchesIdsDeleted) {
		super(challengerMatchesIds, challengerMatchesIds.matchesIds, challengerMatchesIds.idsDeleted);
		this.listChallengerMatches = listChallengerMatches;
		this.matchesIdsDeleted = matchesIdsDeleted;
	}
	
	public List<MatchDetail> getChallengerMatches(){
		return listChallengerMatches;
	}
	
	public List<Queue<Long>> getMatchesIdsDeleted(){
		return this.matchesIdsDeleted;
	}
	
	private void methodChallengerMatches(RiotApi api) throws RiotApiException, InterruptedException{
		RiotApiAsync apiAsync = api.getAsyncApi();
		int idsTotais = 0;
		int idsRemovidos = 0;
		List<MatchDetail> listMatches = new ArrayList<MatchDetail>();
		List<Queue<Long>> deletedMatchesIds = new ArrayList<Queue<Long>>();
		List<Queue<Long>> matchesIds = this.matchesIds;
		for (int i = 0 ; i<this.regions.size();i++){
			deletedMatchesIds.add(new LinkedList<Long>());
			idsTotais = idsTotais + matchesIds.get(i).size();
		}
		int idsRestantes = idsTotais;
		while(idsRestantes > 0){
			idsRestantes = 0;
			idsRemovidos = 0;
			for (int i = 0 ; i<this.regions.size() ; i++){
				if (!matchesIds.get(i).isEmpty()){
					final int innerIndex = i;
					AsyncRequest requestMatch = apiAsync.getMatch(this.regions.get(innerIndex), matchesIds.get(innerIndex).peek(), false);
					requestMatch.addListeners(new RequestAdapter() {
						@Override
						public void onRequestSucceeded(AsyncRequest request) {
							MatchDetail matchDetail = request.getDto();
							listMatches.add(matchDetail);
							matchesIds.get(innerIndex).remove();
						}
						@Override
						public void onRequestFailed(RiotApiException e) {
							if (e.getErrorCode()!=429)
								deletedMatchesIds.get(innerIndex).add(matchesIds.get(innerIndex).remove());
						}
					});
				}
				idsRestantes = idsRestantes + matchesIds.get(i).size();
				idsRemovidos = idsRemovidos + deletedMatchesIds.get(i).size();
			}
			System.out.println("Restantes: " + idsRestantes + "/" + idsTotais + " Removidos: " + idsRemovidos + " Sucessos: " + (idsTotais-idsRestantes-idsRemovidos));
			try {			
				Thread.sleep(sleepTime);
			} catch (InterruptedException ex){
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
			try {
				apiAsync.awaitAll();
			} catch (InterruptedException e) {
				RiotApi.log.log(Level.SEVERE, "Waiting Interrupted", e);
			}
		}
		for (int i = 0 ; i < regions.size() ; i++ ){
			deletedMatchesIds.get(i).addAll(matchesIds.get(i));
		}
		this.listChallengerMatches = listMatches;
		this.matchesIdsDeleted = deletedMatchesIds;
	}
	
}
	
	
