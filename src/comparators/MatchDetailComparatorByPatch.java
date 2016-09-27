package comparators;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByPatch implements ComparatorDigitCatcher<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchVersion().compareTo(b.getMatchVersion());
    }
	@Override
	public String toString(){
		return tipo;
	}
	
	@Override
	public int getValueDigit(MatchDetail a, int digitIndex){
		String name = a.getMatchVersion();
		char digit = name.charAt(digitIndex);
		return Character.getNumericValue(digit);
	}
	
	@Override
	public int getLength(MatchDetail a){
		return a.getMatchVersion().length();
	}
}