package model.database.stats_structure.entity;

import model.database.stats_structure.data.Stats;


import java.io.Serializable;


public class Champion implements Serializable {

	private static final long serialVersionUID = -5936925436345819729L;
	private int ID;
	private Stats stats = new Stats();
	private net.rithms.riot.api.endpoints.static_data.dto.Champion championData;


	public Champion(int ID, net.rithms.riot.api.endpoints.static_data.dto.Champion championData) {
		this.ID = ID;
		this.championData = championData;
	}

	public void addGame(boolean win, int total_gold_earned, int total_kills, int total_assists,
						int total_deaths, int total_heal, int total_pinkWards_bought,
						int total_wards_placed, int total_wards_killed, int total_damage_dealt,
						int total_damage_deat_to_champions, int total_damage_taken, int total_minions_killed){

		this.stats.addGame(win,total_gold_earned,total_kills,total_assists,total_deaths,
				total_heal,total_pinkWards_bought,total_wards_placed,total_wards_killed
				,total_damage_dealt, total_damage_deat_to_champions, total_damage_taken, total_minions_killed);
	}

	public void addBan(){
		stats.addBan();
	}

	public int getID() {
		return ID;
	}

	public Stats getStats() {
		return stats;
	}

	public net.rithms.riot.api.endpoints.static_data.dto.Champion getChampionData() {
		return championData;
	}

	public String toString(){
		return ID + ": " + getChampionData().getName();
	}
}
