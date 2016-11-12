package model.database.stats_structure.entity;

import model.database.stats_structure.data.MasteryID;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class MasteryPage implements Serializable, Comparable<MasteryPage>{

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

	@Override
	public int compareTo(MasteryPage o) {
		int comparison = 0;
		Iterator<MasteryID> it1 = masterys.iterator();
		Iterator<MasteryID> it2 = o.getMasterys().iterator();
		while(comparison==0 && it1.hasNext() && it2.hasNext()){
			comparison = it1.next().compareTo(it2.next());
		}
		if (comparison == 0 && masterys.size() != o.getMasterys().size())
			return masterys.size() - o.getMasterys().size();
		return comparison;
	}
}
