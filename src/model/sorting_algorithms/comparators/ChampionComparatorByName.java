package model.sorting_algorithms.comparators;

import model.database.stats_structure.entity.Champion;

import java.util.Comparator;

/**
 * Created by filip on 07/11/2016.
 */
public class ChampionComparatorByName implements Comparator<Champion> {
    public static final String tipo = "categorico";

    @Override
    public int compare(Champion a, Champion b) {
        return a.getChampionData().getName().compareTo(b.getChampionData().getName());
    }

    @Override
    public String toString(){
        return tipo;
    }


}

