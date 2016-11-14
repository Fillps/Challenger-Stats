package GUI.info_panel.champion_panel;


import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.relationship.Champion_Entity;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

/**
 * Created by filip on 12/11/2016.
 */
public class MasteryTableModel extends AbstractTableModel {
    Controller controller;
    String[] colNames = {"<html><center>Rank", "<html><center>Tree", "<html><center>Win<br>Percent",
            "<html><center>Play<br>Percent", "<html><center>Games<br>Analyzed"};
    List<Champion_Entity> data;
    int[] index;

    public MasteryTableModel(List<Champion_Entity> data, Controller controller){
        this.controller = controller;
        this.data = data;
        this.index = new int[data.size()];
        for(int i = 0; i < index.length; i++)
            index[i] = i+1;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
                return index[rowIndex];
            case 1:
                return controller.getMasteryPage(data.get(rowIndex).getEntity_ID()).getTree();
            case 2:
                return data.get(rowIndex).getWin_rate();
            case 3:
                return ((double)data.get(rowIndex).getGames_analyzed())/controller.getChampion(data.get(rowIndex).getChampion_ID()).getStats().getGames_analyzed();
            case 4:
                return data.get(rowIndex).getGames_analyzed();
            default:
                return null;

        }
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (data.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return colNames[column];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            index[rowIndex] = (int) value;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }


    public int getMasteryPageFromRank(int rank){
        for(int i = 0; i < index.length; i++){
            if (rank == index[i])
                return data.get(i).getEntity_ID();
        }
        return -20;
    }
}