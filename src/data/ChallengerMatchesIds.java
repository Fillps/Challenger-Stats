package data;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;

import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiAsync;
import net.rithms.riot.api.RiotApiException;

import net.rithms.riot.api.endpoints.matchlist.dto.MatchList;
import net.rithms.riot.api.endpoints.matchlist.dto.MatchReference;
import net.rithms.riot.api.request.AsyncRequest;
import net.rithms.riot.api.request.RequestAdapter;
/*
 * Classe que permite armazenar ou obter, com todos os ids dos jogadores, 
 * partidas ranqueadas de um determinado patch na sua determinada regiao,
 * armazenado mas ids das partidas.
 */
public class ChallengerMatchesIds extends ChallengerIds{
	
	private static final long serialVersionUID = 8750873115069883839L;
	protected  List<Queue<Long>> matchesIds;
	protected  List<Queue<Long>> idsDeleted;
	
	public ChallengerMatchesIds(RiotApi api, ChallengerIds challengerIds) throws RiotApiException, InterruptedException {
		super(challengerIds.getRegions(), challengerIds.getPatch(), challengerIds.getListChallengerIds());
		methodChallengerMatchesIds(api);
	}
	
	public ChallengerMatchesIds(ChallengerIds challengerIds, List<Queue<Long>> matchesIds, List<Queue<Long>> IdsDeleted) {
		super(challengerIds.getRegions(), challengerIds.getPatch(), challengerIds.getListChallengerIds());
		this.matchesIds = matchesIds;
		this.idsDeleted = IdsDeleted;
	}
	
	
	public List<Queue<Long>> getMatchesIds(){
		return this.matchesIds;
	}
	public List<Queue<Long>> getIdsDeleted(){
		return this.idsDeleted;
	}
	
	/*
	 * usando a RiotApi, se obtem os ids das partidas de todos os jagadores da lista
	 */
	public void methodChallengerMatchesIds(RiotApi api) throws RiotApiException, InterruptedException{
		
		RiotApiAsync apiAsync = api.getAsyncApi();
		int idsTotais = 0;
		int idsRemovidos = 0;
		List<Queue<Long>> listIds = this.listChallengerIds;
		List<Queue<Long>> listMatchesIds = new ArrayList<Queue<Long>>();
		List<Queue<Long>> listMatchesIdsDeleted = new ArrayList<Queue<Long>>();
		for (int i = 0 ; i<this.regions.size();i++){
			listMatchesIds.add(new LinkedList<Long>());
			listMatchesIdsDeleted.add(new LinkedList<Long>());
			idsTotais = idsTotais + this.listChallengerIds.get(i).size();
		}
		int idsRestantes = idsTotais;
		while(idsRestantes > 0){
			idsRestantes = 0;
			idsRemovidos = 0;
			for (int i = 0 ; i<this.regions.size() ; i++){
				if (!listIds.get(i).isEmpty()){
					final int innerIndex = i;
					AsyncRequest requestMatchList = apiAsync.getMatchList(this.regions.get(i), listIds.get(i).peek(), "", "", "", patch.getBeginTime(), patch.getEndTime(), -1, -1);
					requestMatchList.addListeners(new RequestAdapter() {
						@Override
						public void onRequestSucceeded(AsyncRequest request) {
							MatchList matchList = request.getDto();
							listIds.get(innerIndex).remove();
							if (matchList!=null){
								List<MatchReference> matchReferences = matchList.getMatches();
								if (matchReferences!=null){
									for(int j=0; j<matchReferences.size();j++){
										listMatchesIds.get(innerIndex).add(matchReferences.get(j).getMatchId());
									}
								}
							}
						}
						@Override
						public void onRequestFailed(RiotApiException e) {
							System.out.println(e.getErrorCode());
							if (e.getErrorCode()!=429){
								listMatchesIdsDeleted.get(innerIndex).add(listIds.get(innerIndex).remove());
							}
						}
					});
				}
				idsRestantes = idsRestantes + listIds.get(i).size();
				idsRemovidos = idsRemovidos + listMatchesIdsDeleted.get(i).size();
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
		List<Queue<Long>> listMatchesIdsUnique = new ArrayList<Queue<Long>>();
		for (int i = 0 ; i<this.regions.size() ; i++){ // retira todos partidas com o mesmo id.
			listMatchesIdsUnique.add(new LinkedList<Long>(new LinkedHashSet<Long>(listMatchesIds.get(i))));
		}
		
		this.matchesIds = listMatchesIdsUnique; 
		this.idsDeleted = listMatchesIdsDeleted;
		
	}
	
}
