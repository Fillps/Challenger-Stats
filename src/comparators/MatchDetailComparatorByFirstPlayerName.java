package comparators;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByFirstPlayerName implements ComparatorDigitCatcher<MatchDetail> {
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
	
	@Override
	public int getValueDigit(MatchDetail a, int digitIndex){
		if (a.getParticipantIdentities().get(0).getPlayer()==null)
			return -1;
		String name = a.getParticipantIdentities().get(0).getPlayer().getSummonerName();
		try{
		char digit = name.charAt(digitIndex);
		return Character.getNumericValue(digit);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}
	
	@Override
	public int getLength(MatchDetail a){
		if (a.getParticipantIdentities().get(0).getPlayer()==null)
			return -1;
		return a.getParticipantIdentities().get(0).getPlayer().getSummonerName().length();
	}
	
}