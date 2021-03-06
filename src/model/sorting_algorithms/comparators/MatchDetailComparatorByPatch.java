package model.sorting_algorithms.comparators;

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
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		String name = a.getMatchVersion();
		try{
			char digit = name.charAt(digitIndex);
			return (int) digit;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}
	
	@Override
	public int getLength(MatchDetail a){
		return a.getMatchVersion().length();
	}

	@Override
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher(){
		return new MatchDetailComparatorByPatchReversed();
	}

}

class MatchDetailComparatorByPatchReversed extends MatchDetailComparatorByPatch {

	public int compare(MatchDetail a, MatchDetail b) {
		return (super.compare(a, b))*(-1);
	}
 
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		return (Character.MAX_VALUE - (super.getDigitValue(a, digitIndex, maxLength)));
	}
	
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher() {
		return new MatchDetailComparatorByPatch();
	}
}