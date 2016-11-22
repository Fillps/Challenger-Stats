package model.database.stats_structure.data;

import java.io.Serializable;

/**
 * Created by filip on 09/11/2016.
 */
public class BasicInfo implements Comparable<BasicInfo>, Serializable {
    private static final long serialVersionUID = 7978859631144948114L;
    private int ID;
    private int riot_ID;

    public BasicInfo(int ID, int riot_ID) {
        this.ID = ID;
        this.riot_ID = riot_ID;
    }

    public int getID() {
        return ID;
    }

    public int getRiot_ID(){
        return riot_ID;
    }

    @Override
    public int compareTo(BasicInfo o) {
        return Integer.compare(this.getRiot_ID(), o.getRiot_ID());
    }

    public boolean equals(BasicInfo o){
        if (this.getID() == o.getID())
            return true;
        return false;
    }

    public String toString(){
        return "ID: " + ID + " riot ID: " + riot_ID;
    }

}
