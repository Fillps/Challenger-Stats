package model.database.stats_structure.entity;


import java.io.Serializable;

public class SummonerSpell implements Serializable {

	private static final long serialVersionUID = -7269569187441009434L;
	private int ID;
	private net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell summonerSpellData;

	public SummonerSpell(int ID, net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell summonerSpellData) {
		this.ID = ID;
		this.summonerSpellData = summonerSpellData;
	}

	public int getID() {
		return ID;
	}

	public net.rithms.riot.api.endpoints.static_data.dto.SummonerSpell getSummonerSpellData() {
		return summonerSpellData;
	}
}
