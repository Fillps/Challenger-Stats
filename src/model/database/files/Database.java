package model.database.files;

import model.database.data_structure.TrieMapExtended;
import model.database.stats_structure.entity.Champion;

/**
 * Created by filip on 30/10/2016.
 */
public class Database {

    private TrieMapExtended<String, Integer> trieChampionsNames;

    public Database(){
        this.trieChampionsNames = WriterAndReader.read("arquivos/TrieMap_ChampionsNames.ser");
    }

    public TrieMapExtended<String, Integer> getTrieChampionsNames() {
        return trieChampionsNames;
    }

    public Champion getChampion(int id){
        return null;
    }
}
