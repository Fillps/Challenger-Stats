package controller;

import model.database.data_structure.TrieMapExtended;
import model.database.files.Database;
import model.database.stats_structure.data.OverallStats;
import model.database.stats_structure.entity.*;
import model.database.stats_structure.relationship.Champion_Entity;
import model.database.stats_structure.relationship.Champion_SummonerSpell;

import java.util.List;

/**
 * Created by filip on 06/11/2016.
 */
public class Controller {

    private Database db = new Database();

    public TrieMapExtended<String, Integer> getTrieChampionsNames(){
        return db.getTrieChampionsNames();
    }

    public Champion getChampion(int id){
        return db.getChampion(id);
    }

    public List<Integer> getAllChampionsIds(){
        return db.getAllChampionsIds();
    }

    public OverallStats getOverallStats() {
        return db.getOverallStats();
    }

    public Item getItem(int itemID) {
        return db.getItem(itemID);
    }


    public Mastery getMastery(int masteryID){
        return db.getMastery(masteryID);
    }

    public SummonerSpell getSummonerSpell(int summonerSpellID) {
        return db.getSummonerSpell(summonerSpellID);
    }


    public Rune getRune(int runeID){
        return db.getRune(runeID);
    }

    public RunePage getRunePage(int runePageID) {
        return db.getRunePage(runePageID);
    }

    public MasteryPage getMasteryPage(int masteryPageID) {
        return db.getMasteryPage(masteryPageID);
    }

    public List<Champion_Entity> getChampionMasteries(int championID) {
        return db.getChampionMasteries(championID);
    }

    public List<Champion_Entity> getChampionRunes(int championID) {
        return db.getChampionRunes(championID);
    }

    public List<Champion_SummonerSpell> getChampionSummonerSpells(int championID) {
        return db.getChampionSummonerSpells(championID);
    }

    public List<Champion_Entity> getChampionItems(int championID) {
        return db.getChampionItems(championID);
    }
}
