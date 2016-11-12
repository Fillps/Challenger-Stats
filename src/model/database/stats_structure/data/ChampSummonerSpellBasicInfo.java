package model.database.stats_structure.data;

/**
 * Created by filip on 12/11/2016.
 */

public class ChampSummonerSpellBasicInfo implements Comparable<ChampSummonerSpellBasicInfo> {
    private int ID;
    private int champion_ID;
    private int summonerSpell_0_ID;
    private int summonerSpell_1_ID;


    public ChampSummonerSpellBasicInfo(int ID, int champion_ID, int summonerSpell_0_ID, int summonerSpell_1_ID) {
        this.ID = ID;
        this.champion_ID = champion_ID;
        this.summonerSpell_0_ID = summonerSpell_0_ID;
        this.summonerSpell_1_ID = summonerSpell_1_ID;
    }


    @Override
    public int compareTo(ChampSummonerSpellBasicInfo o) {
        if (champion_ID == o.getChampion_ID()) {
            if (summonerSpell_0_ID == o.getSummonerSpell_0_ID())
                return Integer.compare(summonerSpell_1_ID, o.getSummonerSpell_1_ID());
            return Integer.compare(summonerSpell_0_ID, o.getSummonerSpell_0_ID());
        }
        return Integer.compare(champion_ID, o.getChampion_ID());
    }

    public int getChampion_ID() {
        return champion_ID;
    }


    public int getID() {
        return ID;
    }

    public int getSummonerSpell_0_ID() {
        return summonerSpell_0_ID;
    }

    public int getSummonerSpell_1_ID() {
        return summonerSpell_1_ID;
    }
}
