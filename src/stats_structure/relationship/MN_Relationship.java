package stats_structure.relationship;

public class MN_Relationship {
    private int entity_0_ID;
    private int entity_1_ID;
    private int quantity;

    public MN_Relationship(int entity_0_ID, int entity_1_ID, int quantity) {
        this.entity_0_ID = entity_0_ID;
        this.entity_1_ID = entity_1_ID;
        this.quantity = quantity;
    }

    public int getEntity_0_ID() {
        return entity_0_ID;
    }

    public int getEntity_1_ID() {
        return entity_1_ID;
    }

    public int getQuantity() { return quantity; }
}
