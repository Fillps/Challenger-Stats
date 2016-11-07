package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 20/10/2016.
 */
public class Stats implements Serializable {

    private static final long serialVersionUID = -4183730106517931399L;

    private float win_rate;
    private int gold_earned;
    private int kills;
    private int assists;
    private int deaths;
    private int heal;
    private int pinkWards_bought;
    private int wards_placed;
    private int wards_killed;
    private int damage_dealt;
    private int damage_dealt_to_champions;
    private int damage_taken;


    private int total_bans;
    private int games_analyzed;
    private int total_wins;
    private int total_gold_earned;
    private int total_kills;
    private int total_assists;
    private int total_deaths;
    private int total_heal;
    private int total_pinkWards_bought;
    private int total_wards_placed;
    private int total_wards_killed;
    private int total_damage_dealt;
    private int total_damage_dealt_to_champions;
    private int total_damage_taken;

    public void addGame(boolean win, int total_gold_earned, int total_kills, int total_assists, int total_deaths,
                        int total_heal, int total_pinkWards_bought, int total_wards_placed, int total_wards_killed,
                        int total_damage_dealt, int total_damage_dealt_to_champions, int total_damage_taken) {
        if (win)
            this.total_wins++;
        this.games_analyzed++;
        this.total_gold_earned += total_gold_earned;
        this.total_kills += total_kills;
        this.total_assists += total_assists;
        this.total_deaths += total_deaths;
        this.total_heal += total_heal;
        this.total_pinkWards_bought += total_pinkWards_bought;
        this.total_wards_placed += total_wards_placed;
        this.total_wards_killed += total_wards_killed;
        this.total_damage_dealt += total_damage_dealt;
        this.total_damage_dealt_to_champions += total_damage_dealt_to_champions;
        this.total_damage_taken += total_damage_taken;
        this.win_rate = this.total_wins / this.games_analyzed;
        this.gold_earned = this.total_gold_earned / this.games_analyzed;
        this.kills = this.total_kills / this.games_analyzed;
        this.assists = this.total_assists / this.games_analyzed;
        this.deaths = this.total_deaths / this.games_analyzed;
        this.heal = this.total_heal / this.games_analyzed;
        this.pinkWards_bought = this.total_pinkWards_bought / this.games_analyzed;
        this.wards_placed = this.total_wards_placed / this.games_analyzed;
        this.wards_killed = this.total_wards_killed / this.games_analyzed;
        this.damage_dealt = this.total_damage_dealt / this.games_analyzed;
        this.damage_dealt_to_champions = this.total_damage_dealt_to_champions / this.games_analyzed;
        this.damage_taken = this.total_damage_taken / this.games_analyzed;
    }


    public void addBan(){
        total_bans++;
    }

    public int getTotal_gold_earned() {
        return total_gold_earned;
    }

    public int getTotal_kills() {
        return total_kills;
    }

    public int getTotal_assists() {
        return total_assists;
    }

    public int getTotal_deaths() {
        return total_deaths;
    }

    public int getTotal_heal() {
        return total_heal;
    }

    public int getTotal_pinkWards_bought() {
        return total_pinkWards_bought;
    }

    public int getTotal_wards_placed() {
        return total_wards_placed;
    }

    public int getTotal_wards_killed() {
        return total_wards_killed;
    }

    public int getTotal_damage_dealt() {
        return total_damage_dealt;
    }

    public float getWin_rate() {
        return win_rate;
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

    public int getDamage_dealt_to_champions() {
        return damage_dealt_to_champions;
    }

    public int getDamage_taken() {
        return damage_taken;
    }

    public int getTotal_wins() {
        return total_wins;
    }

    public int getTotal_damage_dealt_to_champions() {
        return total_damage_dealt_to_champions;
    }

    public int getTotal_damage_taken() {
        return total_damage_taken;
    }

    public int getTotal_bans() {
        return total_bans;
    }
}
