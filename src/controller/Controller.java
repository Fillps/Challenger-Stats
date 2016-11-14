package controller;

import model.database.data_structure.TrieMapExtended;
import model.database.files.Database;
import model.database.stats_structure.data.MasteryID;
import model.database.stats_structure.data.MasteryTree;
import model.database.stats_structure.data.OverallStats;
import model.database.stats_structure.data.RuneID;
import model.database.stats_structure.entity.*;
import model.database.stats_structure.relationship.Champion_Entity;
import model.database.stats_structure.relationship.Champion_SummonerSpell;

import java.util.ArrayList;
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

    public List<Champion> getAllChampions(){
        return db.getAllChampions();
    }

    public OverallStats getOverallStats() {
        return db.getOverallStats();
    }

    public List<Champion_Entity> getChampionItems(int championID) {
        List<Champion_Entity> list = new ArrayList<>();
        for (int i = 0 ; i< 100;i++)
            list.add(new Champion_Entity(0,0,i));

        return list;//// TODO: 12/11/2016
    }

    public Item getItem(int itemID) {
        return db.getItem(itemID);
    }





    public Mastery getMastery(int masteryID){
        return db.getMastery(masteryID);
    }

    public List<Champion_SummonerSpell> getChampionSummonerSpells(int championID) {
        List<Champion_SummonerSpell> list = new ArrayList<>();
        for (int i = 0 ; i< 8;i++)
            list.add(new Champion_SummonerSpell(0,i,i+1,0));

        return list;//// TODO: 13/11/2016
    }

    public SummonerSpell getSummonerSpell(int summonerSpellID) {
        return db.getSummonerSpell(summonerSpellID);
    }

    public List<Champion_Entity> getChampionRunes(int championID) {
        List<Champion_Entity> list = new ArrayList<>();
        list.add(new Champion_Entity(0,0,0));
        return list;
    }

    public Rune getRune(int runeID){
        return db.getRune(runeID);
    }

    public RunePage getRunePage(int runePageID) {
        //return db.getRunePage(runePageID);
        List<RuneID> listRuneID = new ArrayList<>();
        listRuneID.add(new RuneID(0,9));
        listRuneID.add(new RuneID(1,9));
        listRuneID.add(new RuneID(2,9));
        listRuneID.add(new RuneID(3,3));
        return new RunePage(0,listRuneID,20.5,22.5,21.4566);
    }

    public MasteryPage getMasteryPage(int masteryPageID) {
        //return db.getMasteryPage(masteryPageID);
        List<MasteryID> listMasteryID = new ArrayList<>();
        listMasteryID.add(new MasteryID(1,5));
        listMasteryID.add(new MasteryID(2,1));
        listMasteryID.add(new MasteryID(6,5));
        listMasteryID.add(new MasteryID(8,1));
        /*listMasteryID.add(new MasteryID(4,5));
        listMasteryID.add(new MasteryID(5,5));
        listMasteryID.add(new MasteryID(6,5));
        listMasteryID.add(new MasteryID(7,4));
        listMasteryID.add(new MasteryID(8,1));
        listMasteryID.add(new MasteryID(9,2));
        listMasteryID.add(new MasteryID(10,3));*/
        return new MasteryPage(0,listMasteryID,new MasteryTree(18,12,0));
    }

    public List<Champion_Entity> getChampionMasterys(int championID) {
        //return null;//// TODO: 13/11/2016
        List<Champion_Entity> list = new ArrayList<>();
        list.add(new Champion_Entity(0,0,0));
        return list;
    }
}
