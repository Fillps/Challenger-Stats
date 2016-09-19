package comparators;

import java.util.Comparator;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByFirstPlayerName implements Comparator<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
		if (a.getParticipantIdentities().get(0).getPlayer()==null && b.getParticipantIdentities().get(0).getPlayer()==null)
			return 0;
		else if (a.getParticipantIdentities().get(0).getPlayer()==null)
			return -1;
		else if (b.getParticipantIdentities().get(0).getPlayer()==null)
			return 1;
        String nameA = a.getParticipantIdentities().get(0).getPlayer().getSummonerName();
        String nameB = b.getParticipantIdentities().get(0).getPlayer().getSummonerName();
		return nameA.compareTo(nameB);
    }
	@Override
	public String toString(){
		return tipo;
	}
}