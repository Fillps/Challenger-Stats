package stats_structure.data;

/**
 * Created by filip on 23/10/2016.
 */
public class EntityStats {
    private int entity_ID;
    private float win_rate;
    private float play_rate;

    public EntityStats(int entity_ID, float win_rate, float play_rate) {
        this.entity_ID = entity_ID;
        this.win_rate = win_rate;
        this.play_rate = play_rate;
    }

    public int getEntity_ID() {
        return entity_ID;
    }

    public float getWin_rate() {
        return win_rate;
    }

    public float getPlay_rate() {
        return play_rate;
    }
}
