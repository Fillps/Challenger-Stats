package database.stats_structure.entity;


import java.io.Serializable;

public class Rune implements Serializable {

	private static final long serialVersionUID = -8507226255856881552L;
	private int ID;
	private net.rithms.riot.api.endpoints.static_data.dto.Rune runeData;

	public Rune(int ID, net.rithms.riot.api.endpoints.static_data.dto.Rune runeData) {
		this.ID = ID;
		this.runeData = runeData;
	}

	public int getID() {
		return ID;
	}

	public net.rithms.riot.api.endpoints.static_data.dto.Rune getRuneData() {
		return runeData;
	}
}
