package model.sorting_algorithms.comparators;

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
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		String name = a.getRegion();
		try{
			char digit = name.charAt(digitIndex);
			return (int) digit;
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}
	
	@Override
	public int getLength(MatchDetail a){
		return a.getRegion().length();
	}

	@Override
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher(){
		return new MatchDetailComparatorByRegionReversed();
	}

}

class MatchDetailComparatorByRegionReversed extends MatchDetailComparatorByRegion {

	public int compare(MatchDetail a, MatchDetail b) {
		return (super.compare(a, b))*(-1);
	}
 
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		return (Character.MAX_VALUE - (super.getDigitValue(a, digitIndex, maxLength)));
	}
	
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher() {
		return new MatchDetailComparatorByRegion();
	}
}