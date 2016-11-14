package model.database.stats_structure.data;

/**
 * Created by filip on 13/11/2016.
 */
public class MasteryTree {
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
