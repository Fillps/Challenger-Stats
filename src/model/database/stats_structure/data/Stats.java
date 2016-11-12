package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 20/10/2016.
 */
public class Stats implements Serializable {

    private static final long serialVersionUID = -4183730106517931399L;

    private double kDA;
    private double win_rate;
    private double kills;
    private double assists;
    private double deaths;
    private double minions_killed;
    private double pinkWards_bought;
    private double wards_placed;
    private double wards_killed;

    private int damage_dealt;
    private int damage_dealt_to_champions;
    private int damage_taken;
    private int gold_earned;
    private int heal;

    private int total_minions_killed;
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
                        int total_damage_dealt, int total_damage_dealt_to_champions, int total_damage_taken, int total_minions_killed) {
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
        this.total_minions_killed = total_minions_killed;

        this.minions_killed = ((double)this.total_minions_killed) / this.games_analyzed;
        this.win_rate = ((double)this.total_wins) / this.games_analyzed;
        this.gold_earned = this.total_gold_earned / this.games_analyzed;
        this.kills = ((double)this.total_kills) / this.games_analyzed;
        this.assists = ((double)this.total_assists) / this.games_analyzed;
        this.deaths = ((double)this.total_deaths) / this.games_analyzed;
        this.heal = this.total_heal / this.games_analyzed;
        this.pinkWards_bought = ((double)this.total_pinkWards_bought) / this.games_analyzed;
        this.wards_placed = ((double)this.total_wards_placed) / this.games_analyzed;
        this.wards_killed = ((double)this.total_wards_killed) / this.games_analyzed;
        this.damage_dealt = this.total_damage_dealt / this.games_analyzed;
        this.damage_dealt_to_champions = this.total_damage_dealt_to_champions / this.games_analyzed;
        this.damage_taken = this.total_damage_taken / this.games_analyzed;
        this.kDA = (this.kills + this.assists) / this.deaths;
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

    public double getWin_rate() {
        return win_rate;
    }

    public int getGames_analyzed() {
        return games_analyzed;
    }

    public int getGold_earned() {
        return gold_earned;
    }

    public double getKills() {
        return kills;
    }

    public double getAssists() {
        return assists;
    }

    public double getDeaths() {
        return deaths;
    }

    public int getHeal() {
        return heal;
    }

    public double getPinkWards_bought() {
        return pinkWards_bought;
    }

    public double getWards_placed() {
        return wards_placed;
    }

    public double getWards_killed() {
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

    public double getMinions_killed() {
        return minions_killed;
    }

    public int getTotal_minions_killed() {
        return total_minions_killed;
    }

    public double getKDA() {
        return kDA;
    }
}
