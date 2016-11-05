package database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 04/11/2016.
 */
public class MasteryID implements Serializable {
    private int ID;
    private int rank;

    public MasteryID(int ID, int rank) {
        this.ID = ID;
        this.rank = rank;
    }

    public int getID() {
        return ID;
    }

    public int getRank() {
        return rank;
    }
}
