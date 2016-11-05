package database.stats_structure.entity;


import java.io.Serializable;

public class Mastery implements Serializable {

	private static final long serialVersionUID = 377247565762239578L;
	private int ID;
	private net.rithms.riot.api.endpoints.static_data.dto.Mastery masteryData;

	public Mastery(int ID, net.rithms.riot.api.endpoints.static_data.dto.Mastery masteryData) {
		this.ID = ID;
		this.masteryData = masteryData;
	}

	public int getID() {
		return ID;
	}

	public net.rithms.riot.api.endpoints.static_data.dto.Mastery getMasteryData() {
		return masteryData;
	}
}
