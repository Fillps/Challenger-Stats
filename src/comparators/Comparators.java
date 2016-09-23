package comparators;

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
	
	public static MatchDetailComparatorByFirstPlayerName matchDetailByFirstPlayerName(){
		return new MatchDetailComparatorByFirstPlayerName();
	}
	
}







