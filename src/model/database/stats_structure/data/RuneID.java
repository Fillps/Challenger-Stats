package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 04/11/2016.
 */
public class RuneID implements Serializable {
    private int ID;
    private int rank;

    public RuneID(int ID, int rank) {
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
