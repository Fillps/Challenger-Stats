package GUI.info_panel.champion_search_panel;

import GUI.ButtonIntegerListener;
import model.database.stats_structure.entity.Champion;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by filip on 06/11/2016.
 */
public class ChampionButton extends JPanel {

    private JButton button = new JButton();
    private int ID;
    private ButtonIntegerListener buttonIntegerListener;

    public ChampionButton(String name, ImageIcon icon, int id, ButtonIntegerListener buttonIntegerListener) throws IOException {

        ID = id;
        button.setIcon(icon);
        button.setText(name);
        this.buttonIntegerListener = buttonIntegerListener;
        button.setForeground(Color.LIGHT_GRAY);
        button.setBackground(Color.BLACK);
        setBackground(Color.LIGHT_GRAY);
        button.setBorder(null);
        //button.setContentAreaFilled(false);
        button.setBounds(1,1,1,1);
        button.setVerticalAlignment(SwingConstants.NORTH);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        button.setIconTextGap(0);

        Dimension dim = button.getPreferredSize();
        dim.width = 80;
        dim.height = 108;//103
        button.setMinimumSize(dim);
        button.setMaximumSize(dim);

        setMinimumSize(dim);
        setMaximumSize(dim);

        add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                buttonIntegerListener.ButtonIntegerEvent(ID);
            }
        });
    }

    public void setButtonIntegerListener(ButtonIntegerListener buttonIntegerListener) {
        this.buttonIntegerListener = buttonIntegerListener;
    }
}
