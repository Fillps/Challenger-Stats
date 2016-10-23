package stats_structure.data;

/**
 * Created by filip on 23/10/2016.
 */
public class EntityWinRatePlayRate {
    private EntityStats win_rate;
    private EntityStats play_rate;

    public EntityWinRatePlayRate(EntityStats win_rate, EntityStats play_rate) {
        this.win_rate = win_rate;
        this.play_rate = play_rate;
    }

    public EntityStats getWin_rate() {
        return win_rate;
    }

    public EntityStats getPlay_rate() {
        return play_rate;
    }
}
