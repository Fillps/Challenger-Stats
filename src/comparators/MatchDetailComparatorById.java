package comparators;


import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorById implements ComparatorDigitCatcher<MatchDetail> {
	public static final String tipo = "numerico";
	
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return Long.compare(a.getMatchId(), b.getMatchId());
    }
	@Override
	public String toString(){
		return tipo;
	}
	
	@Override
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		long id = a.getMatchId();
		String id_string = String.valueOf(id);
		while(id_string.length()<maxLength)
			id_string = "0" + id_string;
		try{
			char digit = id_string.charAt(digitIndex);
			return Character.getNumericValue(digit);
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
		
	}
	
	@Override
	public int getLength(MatchDetail a){
		long id = a.getMatchId();
		int length = String.valueOf(id).length();
		return length;
	}
	
	@Override
	public ComparatorDigitCatcher<MatchDetail> reversedDigitCatcher(){
		return new MatchDetailComparatorByIdReversed();
	}

}

class MatchDetailComparatorByIdReversed extends MatchDetailComparatorById {
	
	public int compare(MatchDetail a, MatchDetail b) {
		return (super.compare(a, b))*(-1);
	}
	
	public int getDigitValue(MatchDetail a, int digitIndex, int maxLength){
		return (9 - (super.getDigitValue(a, digitIndex, maxLength)));
	}
}