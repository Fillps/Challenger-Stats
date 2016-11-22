package model.database.stats_structure.entity;

import model.database.stats_structure.data.MasteryID;
import model.database.stats_structure.data.MasteryTree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class MasteryPage implements Serializable, Comparable<MasteryPage>{

	private static final long serialVersionUID = -4424081976940394743L;
	private int ID;
	private List<MasteryID> masteries;
	private MasteryTree tree;

	public int getID() {
		return ID;
	}

	public List<MasteryID> getMasteries() {
		return masteries;
	}

	public MasteryPage(int ID, List<MasteryID> masteries, MasteryTree tree) {
		this.tree = tree;
		this.ID = ID;
		this.masteries = masteries;
	}

	@Override
	public int compareTo(MasteryPage o) {
		int comparison = 0;
		Iterator<MasteryID> it1 = masteries.iterator();
		Iterator<MasteryID> it2 = o.getMasteries().iterator();
		while(comparison==0 && it1.hasNext() && it2.hasNext()){
			comparison = it1.next().compareTo(it2.next());
		}
		if (comparison == 0 && masteries.size() != o.getMasteries().size())
			return masteries.size() - o.getMasteries().size();
		return comparison;
	}

	public String getTree(){
		return tree.toString();
	}

	public boolean equals(MasteryPage o){
		if (this.getID() == o.getID())
			return true;
		return false;
	}
}
