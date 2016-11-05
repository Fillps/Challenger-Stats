package database.stats_structure.entity;


import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 2804362539185239812L;
	private int ID;
	private net.rithms.riot.api.endpoints.static_data.dto.Item itemData;

	public Item(int ID, net.rithms.riot.api.endpoints.static_data.dto.Item itemData) {
		this.ID = ID;
		this.itemData = itemData;
	}

	public int getID() {
		return ID;
	}

	public net.rithms.riot.api.endpoints.static_data.dto.Item getItemData() {
		return itemData;
	}
}
