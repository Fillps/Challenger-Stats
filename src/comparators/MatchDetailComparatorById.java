package comparators;

import java.util.Comparator;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorById implements Comparator<MatchDetail> {
	public static final String tipo = "numerico";
	
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return Long.compare(a.getMatchId(), b.getMatchId());
    }
	@Override
	public String toString(){
		return tipo;
	}
}