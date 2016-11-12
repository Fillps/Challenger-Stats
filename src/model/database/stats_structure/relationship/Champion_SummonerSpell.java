package model.database.stats_structure.relationship;

import java.io.Serializable;

/**
 * Created by filip on 29/10/2016.
 */
public class Champion_SummonerSpell implements Serializable {

    private static final long serialVersionUID = 8246878613663362643L;
    private int ID;
    private int summonerSpell_0__ID;
    private int summonerSpell_1_ID;
    private int champion_ID;
    private int wins;
    private int games_analyzed;
    private double win_rate;

    public Champion_SummonerSpell(int ID, int summonerSpell_0__ID, int summonerSpell_1_ID, int champion_ID) {
        this.ID = ID;
        this.summonerSpell_0__ID = summonerSpell_0__ID;
        this.summonerSpell_1_ID = summonerSpell_1_ID;
        this.champion_ID = champion_ID;
    }

    public int getID() { return ID; }

    public int getSummonerSpell_0__ID() {
        return summonerSpell_0__ID;
    }

    public int getSummonerSpell_1_ID() {
        return summonerSpell_1_ID;
    }

    public int getChampion_ID() {
        return champion_ID;
    }

    public int getWins() {
        return wins;
    }

    public int getGames_analyzed() {
        return games_analyzed;
    }

    public double getWin_rate() { return win_rate; }

    public void addGame(boolean win) {
        if (win)
            wins++;
        games_analyzed++;
        win_rate = ((double)wins) / games_analyzed;
    }
}
