package database.stats_structure.entity;


import database.stats_structure.data.RuneID;

import java.io.Serializable;
import java.util.List;

public class RunePage implements Serializable {

	private static final long serialVersionUID = -7441938847492728547L;
	private int ID;
	List<RuneID> masterys;

	public RunePage(int ID, List<RuneID> masterys) {
		this.ID = ID;
		this.masterys = masterys;
	}

	public int getID() {
		return ID;
	}

	public List<RuneID> getMasterys() {
		return masterys;
	}
}
