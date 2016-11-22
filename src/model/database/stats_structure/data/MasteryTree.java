package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 13/11/2016.
 */
public class MasteryTree implements Serializable{
    private static final long serialVersionUID = -5723876951467007791L;
    private int ferocity;
    private int cunning;
    private int resolve;

    public MasteryTree(int ferocity, int cunning, int resolve) {
        this.ferocity = ferocity;
        this.cunning = cunning;
        this.resolve = resolve;
    }

    public String toString(){
        return ferocity + "/" + cunning + "/" + resolve;
    }
}
