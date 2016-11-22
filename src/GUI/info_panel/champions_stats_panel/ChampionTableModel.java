package GUI.info_panel.champions_stats_panel;

import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.data.OverallStats;
import model.database.stats_structure.entity.Champion;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;

/**
 * Created by filip on 08/11/2016.
 */
public class ChampionTableModel extends AbstractTableModel {
    private Controller controller;
    private List<Integer> listChampionId;
    private int[] index;
    private OverallStats overallStats;

    private Champion champion;
    private int championIndex = -1;

    private String[] colNames = {"<html><center>Rank", "<html><center>Icon","<html><center>Champion", "<html><center>Win<br>Percent",
            "<html><center>Play<br>Percent", "<html><center>Ban<br>Rate", "<html><center>Kills", "<html><center>Deaths", "<html><center>Assists",
            "<html><center>K/D/A", "<html><center>Damage<br>Dealt", "<html><center>Damage<br>Taken", "<html><center>Total<br>Healing",
            "<html><center>Minions<br>Killed", "<html><center>Gold<br>Earned", "<html><center>Games<br>Analyzed"};


    public ChampionTableModel(Controller controller, OverallStats overallStats){
        this.controller = controller;
        this.listChampionId = controller.getAllChampionsIds();
        this.index = new int[listChampionId.size()];
        for(int i = 0; i < index.length; i++)
            index[i] = i+1;
        this.overallStats = overallStats;
    }


    @Override
    public int getRowCount() {
        return listChampionId.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex!=championIndex){
            championIndex = rowIndex;
            champion = controller.getChampion(listChampionId.get(rowIndex));
        }
        switch (columnIndex){
            case 0:
                return index[rowIndex];
            case 1:
                return getImageIcon();
            case 2:
                return champion.getChampionData().getName();
            case 3:
                return champion.getStats().getWin_rate();
            case 4:
                return overallStats.getPlayRate(champion.getStats().getGames_analyzed());
            case 5:
                return overallStats.getBanRate(champion.getStats().getTotal_bans());
            case 6:
                return champion.getStats().getKills();
            case 7:
                return champion.getStats().getDeaths();
            case 8:
                return champion.getStats().getAssists();
            case 9:
                return champion.getStats().getKDA();
            case 10:
                return champion.getStats().getDamage_dealt();
            case 11:
                return champion.getStats().getDamage_taken();
            case 12:
                return champion.getStats().getHeal();
            case 13:
                return champion.getStats().getMinions_killed();
            case 14:
                return champion.getStats().getGold_earned();
            case 15:
                return champion.getStats().getGames_analyzed();
            default:
                return null;

        }
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (listChampionId.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return colNames[column];
    }

    private ImageIcon getImageIcon(){
        Image image = new ImageIcon(Path.CHAMPION_ICON + champion.getChampionData().getImage().getFull()).getImage();
        ImageIcon icon = new ImageIcon(image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH ));
        return icon;
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


}
