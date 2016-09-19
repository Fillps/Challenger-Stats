package comparators;

import java.util.Comparator;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class MatchDetailComparatorByPatch implements Comparator<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchVersion().compareTo(b.getMatchVersion());
    }
	@Override
	public String toString(){
		return tipo;
	}
}