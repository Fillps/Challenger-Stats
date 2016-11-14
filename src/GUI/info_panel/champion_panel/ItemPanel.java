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
public class ItemPanel extends JPanel {
    private JTable itemTable;

    public ItemPanel(Controller controller, int championID){

        ItemTableModel model = new ItemTableModel(controller.getChampionItems(championID), controller);
        itemTable = new JTable(model);
        itemTable.setRowHeight(30);

        itemTable.getColumnModel().getColumn(2).setMinWidth(120);

        itemTable.getTableHeader().setPreferredSize(
                new Dimension(itemTable.getColumnModel().getTotalColumnWidth(), 60));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        itemTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        for (int i = 3; i < itemTable.getColumnCount(); i++)
            itemTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        setLayout(new BorderLayout());
        TableRowSorter<ItemTableModel> sorter = new TableRowSorter<ItemTableModel>((ItemTableModel) itemTable.getModel());

        sorter.setSortable(0,false);
        sorter.setSortable(1,false);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
                int indexOfNoColumn = 0;
                for (int i = 0; i < itemTable.getRowCount(); i++) {
                    itemTable.setValueAt(i + 1, i, indexOfNoColumn);
                }
            }
        });
        itemTable.setRowSorter(sorter);

        add(new JScrollPane(itemTable), BorderLayout.CENTER);

        Border innerBorder = BorderFactory.createTitledBorder("Items");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
