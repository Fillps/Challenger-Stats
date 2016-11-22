package model.database.stats_structure.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by filip on 20/11/2016.
 */
public class RelationshipByChampion<T> implements Serializable, Comparable<RelationshipByChampion<T>> {
    private static final long serialVersionUID = 3017095778143329115L;
    private int championID;
    private List<T> list;

    public RelationshipByChampion(int championID, T relation) {
        this.championID = championID;
        this.list = new ArrayList<T>();
        this.list.add(relation);
    }

    @Override
    public int compareTo(RelationshipByChampion o) {
        return Integer.compare(championID, o.getChampionID());
    }

    public int getChampionID() {
        return championID;
    }

    public List<T> getList() {
        return list;
    }
}
