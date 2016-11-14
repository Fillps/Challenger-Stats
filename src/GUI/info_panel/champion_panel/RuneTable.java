package GUI.info_panel.champion_panel;

import GUI.ButtonIntegerListener;
import controller.Controller;

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
public class RuneTable extends JPanel {
    private JScrollPane scrollPane;
    private JTable runeTable;
    private Controller controller;

    public RuneTable(Controller controller, int championID){

        this.controller = controller;

        RuneTableModel model = new RuneTableModel(controller.getChampionRunes(championID), controller);
        runeTable = new JTable(model);
        runeTable.setRowHeight(30);


        runeTable.getTableHeader().setPreferredSize(
                new Dimension(runeTable.getColumnModel().getTotalColumnWidth(), 60));

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        for (int i = 0; i < runeTable.getColumnCount(); i++)
            runeTable.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        setLayout(new BorderLayout());
        TableRowSorter<RuneTableModel> sorter = new TableRowSorter<RuneTableModel>((RuneTableModel) runeTable.getModel());

        sorter.setSortable(0,false);

        sorter.addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent evt) {
                int indexOfNoColumn = 0;
                for (int i = 0; i < runeTable.getRowCount(); i++) {
                    runeTable.setValueAt(i + 1, i, indexOfNoColumn);
                }
            }
        });
        runeTable.setRowSorter(sorter);

        scrollPane = new JScrollPane(runeTable);
        add(scrollPane, BorderLayout.CENTER);

        runeTable.addMouseListener(new MouseAdapter() {
                                       public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                   JTable target = (JTable) e.getSource();
                   //int index = (int) runeTable.getValueAt(runeTable.getSelectedRow(), 0);
                   int index = 0;
                   int runePageID = ((RuneTableModel) runeTable.getModel()).getRunePageFromRank(index);
                   removeAll();
                   validate();
                   repaint();
                   add(new RunePanel(controller.getRunePage(runePageID), controller, new ActionListener() {
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


        Border innerBorder = BorderFactory.createTitledBorder("Runes");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


    }
}