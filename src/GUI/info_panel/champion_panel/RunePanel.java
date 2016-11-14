package GUI.info_panel.champion_panel;

import controller.Controller;
import model.database.files.Path;
import model.database.stats_structure.data.RuneID;
import model.database.stats_structure.entity.Rune;
import model.database.stats_structure.entity.RunePage;

import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by filip on 12/11/2016.
 */
public class RunePanel extends JPanel {
    public RunePanel(RunePage runePage, Controller controller, ActionListener listener) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton button = new JButton("X");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
        add(button);
        String[] col = {"Icon", "Quantity", "Name", "Description"};
        Object[][] data = new Object[runePage.getRunes().size()][4];

        int i = 0;
        for(RuneID runeID : runePage.getRunes()){
            Rune rune = controller.getRune(runeID.getID());

            Image image = new ImageIcon(Path.RUNE_ICONS + rune.getRuneData().getImage().getFull()).getImage();
            ImageIcon icon = new ImageIcon(image.getScaledInstance(65, 65,  java.awt.Image.SCALE_SMOOTH ));
            data[i][0] = icon;

            data[i][1] = runeID.getRank() + "x ";

            data[i][2] = rune.getRuneData().getName();

            data[i][3] = rune.getRuneData().getDescription();

            i++;
        }
        DefaultTableModel model = new DefaultTableModel(data, col);
        JTable table = new JTable( model )
        {
            //  Returning the Class of each column will allow different
            //  renderers to be used based on Class
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }
        };
        //table.setPreferredScrollableViewportSize(table.getPreferredSize());

        table.getColumnModel().getColumn(1).setPreferredWidth(8);
        table.setRowHeight(75);
        table.setEnabled(false);
        table.setBackground(Color.LIGHT_GRAY);
        add(table);
        setBackground(Color.LIGHT_GRAY);

    }
}
