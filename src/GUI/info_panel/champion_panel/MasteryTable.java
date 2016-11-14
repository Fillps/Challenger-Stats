package GUI.info_panel.champion_panel;

import controller.Controller;
import model.database.stats_structure.entity.Mastery;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by filip on 12/11/2016.
 */
public class MasteryTable extends JPanel {
    private JScrollPane scrollPane;
    private JTable masteryTable;

    public MasteryTable(Controller controller, int championID){

        MasteryTableModel model = new MasteryTableModel(controller.getChampionMasterys(championID), controller);
        masteryTable = new JTable(model);
        masteryTable.setRowHeight(30);

        masteryTable.getTableHeader().setPreferredSize(
                new Dimension(masteryTable.getColumnModel().getTotalColumnWidth(), 60));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < masteryTable.getColumnCount(); i++)
            masteryTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        setLayout(new BorderLayout());
        TableRowSorter<MasteryTableModel> sorter = new TableRowSorter<MasteryTableModel>((MasteryTableModel) masteryTable.getModel());

        sorter.setSortable(0,false);

        masteryTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int index = (int) masteryTable.getValueAt(masteryTable.getSelectedRow(), 0);
                    //int index = 0;
                    int masteryPageID = ((MasteryTableModel) masteryTable.getModel()).getMasteryPageFromRank(index);
                    removeAll();
                    validate();
                    repaint();
                    add(new MasteryPanel(controller.getMasteryPage(masteryPageID), controller, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeAll();
                            validate();
                            repaint();
                            add(scrollPane, BorderLayout.CENTER);
                            validate();
                            repaint();
                        }
                    }), BorderLayout.CENTER);
                    validate();
                    repaint();
                }
            }
        });

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
                int indexOfNoColumn = 0;
                for (int i = 0; i < masteryTable.getRowCount(); i++) {
                    masteryTable.setValueAt(i + 1, i, indexOfNoColumn);
                }
            }
        });
        masteryTable.setRowSorter(sorter);

        scrollPane = new JScrollPane(masteryTable);
        add(scrollPane, BorderLayout.CENTER);

        Border innerBorder = BorderFactory.createTitledBorder("Masteries");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
