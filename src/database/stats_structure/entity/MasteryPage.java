package database.stats_structure.entity;

import database.stats_structure.data.MasteryID;

import java.io.Serializable;
import java.util.List;

public class MasteryPage implements Serializable {

	private static final long serialVersionUID = -4424081976940394743L;
	private int ID;
	List<MasteryID> masterys;

	public int getID() {
		return ID;
	}

	public List<MasteryID> getMasterys() {
		return masterys;
	}

	public MasteryPage(int ID, List<MasteryID> masterys) {

		this.ID = ID;
		this.masterys = masterys;
	}
}
