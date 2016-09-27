package comparators;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByRegion implements ComparatorDigitCatcher<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getRegion().compareTo(b.getRegion());
    }
	@Override
	public String toString(){
		return tipo;
	}
	
	@Override
	public int getValueDigit(MatchDetail a, int digitIndex){
		String name = a.getRegion();
		char digit = name.charAt(digitIndex);
		return Character.getNumericValue(digit);
	}
	
	@Override
	public int getLength(MatchDetail a){
		return a.getRegion().length();
	}
}