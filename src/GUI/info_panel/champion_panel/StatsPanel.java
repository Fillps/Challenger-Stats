package GUI.info_panel.champion_panel;

import model.database.stats_structure.data.OverallStats;
import model.database.stats_structure.data.Stats;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by filip on 12/11/2016.
 */
public class StatsPanel extends JPanel {

    private JTable statsTable;

    public StatsPanel(Stats stats, OverallStats overallStats){

        String[] col = {"Tipo", "Dado"};

        Object[][] data = {
                {"Win Rate", stats.getWin_rate()},
                {"Play Rate", overallStats.getPlayRate(stats.getGames_analyzed())},
                {"Ban Rate", overallStats.getPlayRate(stats.getTotal_bans())},
                {"Gold Earned", stats.getGold_earned()},
                {"KDA", stats.getKDA()},
                {"Kills", stats.getKills()},
                {"Deaths", stats.getDeaths()},
                {"Assists", stats.getAssists()},
                {"Damage Dealt", stats.getDamage_dealt()},
                {"Damage Dealt to Champions", stats.getDamage_dealt_to_champions()},
                {"Damage Taken", stats.getDamage_taken()},
                {"Minions Killed", stats.getMinions_killed()},
                {"Wards Placed", stats.getWards_placed()},
                {"Pink Wards Bought", stats.getPinkWards_bought()},
                {"Wards Killed", stats.getWards_killed()},
                {"Heal", stats.getHeal()},
                {"Games Analyzed", stats.getGames_analyzed()}
        };
        statsTable = new JTable(data, col);
        statsTable.setRowHeight(30);
        statsTable.setEnabled(false);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        statsTable.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

        statsTable.getColumnModel().getColumn(0).setMinWidth(200);

        /*se quiser mudar a cor das linhas
        statsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table,
                        value, isSelected, hasFocus, row, column);
                c.setBackground(row%2==0 ? new Color(171, 171, 171) : Color.LIGHT_GRAY);
                return c;
            }
        });*/

        setLayout(new BorderLayout());

        add(statsTable, BorderLayout.NORTH);
        Dimension dim = new Dimension(300, 580);
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Stats");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }
}
