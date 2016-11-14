package GUI.info_panel.champion_panel;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Created by filip on 12/11/2016.
 */
public class SummonerSpellPanel extends JPanel {
    private JTable summonerSpellTable;

    public SummonerSpellPanel(Controller controller, int championID){

        SummonerSpellTableModel model = new SummonerSpellTableModel(controller.getChampionSummonerSpells(championID), controller);
        summonerSpellTable = new JTable(model);
        summonerSpellTable.setRowHeight(30);

        summonerSpellTable.getColumnModel().getColumn(2).setMinWidth(70);
        summonerSpellTable.getColumnModel().getColumn(4).setMinWidth(70);

        summonerSpellTable.getTableHeader().setPreferredSize(
                new Dimension(summonerSpellTable.getColumnModel().getTotalColumnWidth(), 60));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        summonerSpellTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        for (int i = 5; i < summonerSpellTable.getColumnCount(); i++)
            summonerSpellTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        setLayout(new BorderLayout());
        TableRowSorter<SummonerSpellTableModel> sorter = new TableRowSorter<SummonerSpellTableModel>((SummonerSpellTableModel) summonerSpellTable.getModel());

        sorter.setSortable(0,false);
        sorter.setSortable(1,false);
        sorter.setSortable(3,false);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
                int indexOfNoColumn = 0;
                for (int i = 0; i < summonerSpellTable.getRowCount(); i++) {
                    summonerSpellTable.setValueAt(i + 1, i, indexOfNoColumn);
                }
            }
        });
        summonerSpellTable.setRowSorter(sorter);

        add(new JScrollPane(summonerSpellTable), BorderLayout.CENTER);

        Border innerBorder = BorderFactory.createTitledBorder("SummonerSpells");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
