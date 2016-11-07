package GUI.info_panel.champion_panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by filip on 06/11/2016.
 */
public class ChampionPanel extends JPanel{

    private JTextField textField = new JTextField("Search Champion...", 20);

    public ChampionPanel(){

        setLayout(new BorderLayout());
        add(textField, BorderLayout.CENTER);
        Border innerBorder = BorderFactory.createTitledBorder("Champion");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


    }
}
