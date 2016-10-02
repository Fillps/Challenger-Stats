package comparators;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByFirstPlayerName implements ComparatorDigitCatcher<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
		int comparison;
		try {
			String nameA = a.getParticipantIdentities().get(0).getPlayer().getSummonerName();
			String nameB = b.getParticipantIdentities().get(0).getPlayer().getSummonerName();
			comparison = nameA.compareTo(nameB);
		} catch (NullPointerException e){
			if (a.getParticipantIdentities().get(0).getPlayer()==null && b.getParticipantIdentities().get(0).getPlayer()==null)
				comparison = 0;
			else if (a.getParticipantIdentities().get(0).getPlayer()==null)
				comparison = -1;
			else
				comparison = 1;
		}
		return comparison;
    }
	
	@Override
	public String toString(){
		return tipo;
	}
	
	@Override
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		try{
			String name = a.getParticipantIdentities().get(0).getPlayer().getSummonerName();
			char digit = name.charAt(digitIndex);
			return (int) digit;
		} catch (IndexOutOfBoundsException | NullPointerException e) {
			return -1;
		}
	}
	
	@Override
	public int getLength(MatchDetail a){
		try {
			return a.getParticipantIdentities().get(0).getPlayer().getSummonerName().length();
		} catch (NullPointerException e){
			return -1;
		}
		
	}
	
	@Override
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher(){
		return new MatchDetailComparatorByFirstPlayerNameReversed();
	}
	
}

class MatchDetailComparatorByFirstPlayerNameReversed extends MatchDetailComparatorByFirstPlayerName {
	
	public int compare(MatchDetail a, MatchDetail b) {
		return (super.compare(a, b))*(-1);
	}
	 
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		return (Character.MAX_VALUE - (super.getDigitValue(a, digitIndex, maxLength)));
	}
	
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher() {
		return new MatchDetailComparatorByFirstPlayerName();
	}
}
