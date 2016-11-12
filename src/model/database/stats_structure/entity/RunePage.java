package model.database.stats_structure.entity;


import model.database.stats_structure.data.MasteryID;
import model.database.stats_structure.data.RuneID;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class RunePage implements Serializable, Comparable<RunePage> {

	private static final long serialVersionUID = -7441938847492728547L;
	private int ID;
	List<RuneID> runes;

	public RunePage(int ID, List<RuneID> runes) {
		this.ID = ID;
		this.runes = runes;
	}

	public int getID() {
		return ID;
	}

	public List<RuneID> getRunes() {
		return runes;
	}


	@Override
	public int compareTo(RunePage o) {

		int comparison = 0;
		Iterator<RuneID> it1 = runes.iterator();
		Iterator<RuneID> it2 = o.getRunes().iterator();
		while(comparison==0 && it1.hasNext() && it2.hasNext()){
			comparison = it1.next().compareTo(it2.next());
		}
		if (comparison == 0 && runes.size() != o.getRunes().size())
			return runes.size() - o.getRunes().size();
		return comparison;
	}
}
