package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 04/11/2016.
 */
public class OverallStats implements Serializable
{
    private static final long serialVersionUID = 2918080219127701479L;

    private int champions_analysed;

    public int getChampions_analysed() {
        return champions_analysed;
    }

    public int getMatches_analysed() {
        return matches_analysed;
    }

    public int getTotal_bans() {
        return total_bans;
    }

    private int matches_analysed;
    private int total_bans;

    public void addChampion(){
        champions_analysed++;
    }
    public void addMatch(){ matches_analysed++; }
    public void addBan(){ total_bans++; }
    public double getPlayRate(int value){
        return ((double) value) / matches_analysed;
    }

    public double getBanRate(int value){
        return ((double) (value) / total_bans);
    }

}
