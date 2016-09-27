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
	public int getValueDigit(MatchDetail a, int digitIndex){
		long id = a.getMatchId();
		String id_string = String.valueOf(id);
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

}