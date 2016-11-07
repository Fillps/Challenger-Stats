package model.database.files;

import model.database.data_structure.BTree;
import model.database.data_structure.TrieMapExtended;
import model.database.stats_structure.entity.Champion;
import model.riot_api.StaticData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by filip on 30/10/2016.
 */
public class Database {

    private TrieMapExtended<String, Integer> trieChampionsNames;
    private List<Champion> listChampions;

    public Database(){
        this.trieChampionsNames = WriterAndReader.read("arquivos/TrieMap_ChampionsNames.ser");
        this.listChampions = WriterAndReader.read("arquivos/ListChampions.ser");
    }

    public TrieMapExtended<String, Integer> getTrieChampionsNames() {
        return trieChampionsNames;
    }

    public Champion getChampion(int id){
        return null;
    } //  TODO: 07/11/2016

    public List<Champion> getAllChampions(){
        return listChampions;
    }
}
