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
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getRegion().compareToIgnoreCase(b.getRegion());
    }
}

class MatchDetailComparatorById implements Comparator<MatchDetail> {
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchId() < b.getMatchId() ? -1 : a.getMatchId() == b.getMatchId() ? 0 : 1;
    }
}

class MatchDetailComparatorByPatch implements Comparator<MatchDetail> {
    @Override
    public int compare(MatchDetail a, MatchDetail b) {
        return a.getMatchVersion().compareToIgnoreCase(b.getMatchVersion());
    }
}