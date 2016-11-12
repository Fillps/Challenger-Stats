package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 04/11/2016.
 */
public class MasteryID implements Serializable, Comparable<MasteryID> {
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

    @Override
    public int compareTo(MasteryID o) {
        if(ID == o.getID()){
            return Integer.compare(rank, o.getRank());
        }
        return Integer.compare(ID, o.getID());
    }

    public boolean equals(RuneID o){
        if (ID == o.getID() && rank == o.getRank())
            return true;
        return false;
    }

}
