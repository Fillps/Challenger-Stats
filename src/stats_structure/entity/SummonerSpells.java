package stats_structure.entity;

public class SummonerSpells {

	private int summonerSpells_ID;
	private int summonerSpell_0_ID;
	private int summonerSpell_1_ID;

	public SummonerSpells(int summonerSpells_ID, int summonerSpell_0_ID, int summonerSpell_1_ID) {
		this.summonerSpells_ID = summonerSpells_ID;
		this.summonerSpell_0_ID = summonerSpell_0_ID;
		this.summonerSpell_1_ID = summonerSpell_1_ID;
	}

	public int getSummonerSpells_ID() {
		return summonerSpells_ID;
	}

	public int getSummonerSpell_0_ID() {
		return summonerSpell_0_ID;
	}

	public int getSummonerSpell_1_ID() {
		return summonerSpell_1_ID;
	}
}
