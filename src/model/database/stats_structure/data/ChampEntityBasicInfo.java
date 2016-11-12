package model.database.stats_structure.data;

/**
 * Created by filip on 12/11/2016.
 */
public class ChampEntityBasicInfo implements Comparable<ChampEntityBasicInfo> {
    private int ID;
    private int champion_ID;
    private int entity_ID;

    public ChampEntityBasicInfo(int ID, int champion_ID, int entity_ID) {
        this.ID = ID;
        this.champion_ID = champion_ID;
        this.entity_ID = entity_ID;
    }


    @Override
    public int compareTo(ChampEntityBasicInfo o) {
        if (champion_ID == o.getChampion_ID())
            return Integer.compare(entity_ID, o.getEntity_ID());
        return Integer.compare(champion_ID, o.getChampion_ID());
    }

    public int getChampion_ID() {
        return champion_ID;
    }

    public int getEntity_ID() {
        return entity_ID;
    }

    public int getID() {
        return ID;
    }


}
