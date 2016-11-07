package controller;

import model.database.data_structure.TrieMap;
import model.database.data_structure.TrieMapExtended;
import model.database.files.Database;
import model.database.files.WriterAndReader;
import model.database.stats_structure.entity.Champion;

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


}
