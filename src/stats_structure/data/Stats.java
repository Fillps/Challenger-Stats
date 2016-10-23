package stats_structure.data;

/**
 * Created by filip on 20/10/2016.
 */
public class Stats {
    private float win_rate;
    private float play_rate;
    private float ban_rate;
    private int games_analyzed;
    private int gold_earned;
    private int kills;
    private int assists;
    private int deaths;
    private int heal;
    private int pinkWards_bought;
    private int wards_placed;
    private int wards_killed;
    private int damage_dealt;

    public Stats(float win_rate, float play_rate, float ban_rate, int games_analyzed,
                 int gold_earned, int kills, int assists, int deaths, int heal, int pinkWards_bought,
                 int wards_placed, int wards_killed, int damage_dealt) {
        this.win_rate = win_rate;
        this.play_rate = play_rate;
        this.ban_rate = ban_rate;
        this.games_analyzed = games_analyzed;
        this.gold_earned = gold_earned;
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.heal = heal;
        this.pinkWards_bought = pinkWards_bought;
        this.wards_placed = wards_placed;
        this.wards_killed = wards_killed;
        this.damage_dealt = damage_dealt;
    }

    public float getWin_rate() {
        return win_rate;
    }

    public float getPlay_rate() {
        return play_rate;
    }

    public float getBan_rate() {
        return ban_rate;
    }

    public int getGames_analyzed() {
        return games_analyzed;
    }

    public int getGold_earned() {
        return gold_earned;
    }

    public int getKills() {
        return kills;
    }

    public int getAssists() {
        return assists;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getHeal() {
        return heal;
    }

    public int getPinkWards_bought() {
        return pinkWards_bought;
    }

    public int getWards_placed() {
        return wards_placed;
    }

    public int getWards_killed() {
        return wards_killed;
    }

    public int getDamage_dealt() {
        return damage_dealt;
    }
}
