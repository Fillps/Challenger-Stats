package data;

import java.util.Comparator;

import net.rithms.riot.api.endpoints.match.dto.MatchDetail;

public class Comparators {
	public static MatchDetailComparatorByRegion matchDetailByRegion(){
		return new MatchDetailComparatorByRegion();
	}
	
	public static MatchDetailComparatorById matchDetailById(){
		return new MatchDetailComparatorById();
	}
	
	public static MatchDetailComparatorByPatch matchDetailByPatch(){
		return new MatchDetailComparatorByPatch();
	}
}

class MatchDetailComparatorByRegion implements Comparator<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getRegion().compareToIgnoreCase(b.getRegion());
    }
	@Override
	public String toString(){
		return tipo;
	}
}

class MatchDetailComparatorById implements Comparator<MatchDetail> {
	public static final String tipo = "numerico";
	
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchId() < b.getMatchId() ? -1 : a.getMatchId() == b.getMatchId() ? 0 : 1;
    }
	@Override
	public String toString(){
		return tipo;
	}
}

class MatchDetailComparatorByPatch implements Comparator<MatchDetail> {
	public static final String tipo = "categorico";
	
	@Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchVersion().compareToIgnoreCase(b.getMatchVersion());
    }
	@Override
	public String toString(){
		return tipo;
	}
}