package database.stats_structure.relationship;

import java.io.Serializable;

/**
 * Created by filip on 29/10/2016.
 */
public class Champion_Entity implements Serializable {

    private static final long serialVersionUID = 2056758456617624719L;
    private int champion_ID;
    private int entity_ID;
    private int wins;
    private int games_analyzed;
    private float win_rate;


    public Champion_Entity(int champion_ID, int entity_ID) {
        this.champion_ID = champion_ID;
        this.entity_ID = entity_ID;
    }

    public int getChampion_ID() {
        return champion_ID;
    }

    public int getEntity_ID() {
        return entity_ID;
    }

    public int getWins() {
        return wins;
    }

    public int getGames_analyzed() { return games_analyzed; }

    public float getWin_rate() { return win_rate; }

    public void addGame(boolean win) {
        if (win)
            wins++;
        games_analyzed++;
        win_rate = wins / games_analyzed;
    }
}
