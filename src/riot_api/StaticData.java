package riot_api;

import net.rithms.riot.api.endpoints.static_data.dto.*;

import java.io.Serializable;


public class StaticData implements Serializable{
    private static final long serialVersionUID = -3414128438140160537L;
    private ChampionList championList;
    private ItemList itemList;
    private RuneList runeList;
    private MasteryList masteryList;
    private SummonerSpellList summonerSpellList;

    public StaticData(ChampionList championList, ItemList itemList, RuneList runeList, MasteryList masteryList, SummonerSpellList summonerSpellList) {
        this.championList = championList;
        this.itemList = itemList;
        this.runeList = runeList;
        this.masteryList = masteryList;
        this.summonerSpellList = summonerSpellList;
    }

    public ChampionList getChampionList() {
        return championList;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public RuneList getRuneList() {
        return runeList;
    }

    public MasteryList getMasteryList() {
        return masteryList;
    }

    public SummonerSpellList getSummonerSpellList() {
        return summonerSpellList;
    }
}
