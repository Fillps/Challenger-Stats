package GUI.info_panel.champions_stats_panel;

import GUI.ButtonIntegerListener;
import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by filip on 08/11/2016.
 */
public class ChampionStatsPanel extends JPanel {

    private JTable table;
    private ChampionTableModel championTableModel;

    public ChampionStatsPanel(ButtonIntegerListener buttonIntegerListener, Controller controller){

        championTableModel = new ChampionTableModel(controller, controller.getOverallStats());
        table = new JTable(championTableModel);
        table.setRowHeight(30);

        table.getColumnModel().getColumn(2).setMinWidth(120);

        table.getTableHeader().setPreferredSize(
                new Dimension(table.getColumnModel().getTotalColumnWidth(), 60));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        for (int i = 3; i < table.getColumnCount(); i++)
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    String name = (String) table.getValueAt(table.getSelectedRow(), 2);
                    buttonIntegerListener.ButtonIntegerEvent(controller.getTrieChampionsNames().getListValuesOnSubTree(name.toLowerCase()).get(0));
                }
            }
        });

        setLayout(new BorderLayout());
        TableRowSorter<ChampionTableModel> sorter = new TableRowSorter<ChampionTableModel>((ChampionTableModel) table.getModel());

        sorter.setSortable(0,false);
        sorter.setSortable(1,false);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
                int indexOfNoColumn = 0;
                for (int i = 0; i < table.getRowCount(); i++) {
                    table.setValueAt(i + 1, i, indexOfNoColumn);
                }
            }
        });
        table.setRowSorter(sorter);



        add(new JScrollPane(table), BorderLayout.CENTER);

        Border innerBorder = BorderFactory.createTitledBorder("Champions Stats");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
