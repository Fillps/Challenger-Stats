package GUI.info_panel.champion_panel;

import model.database.files.Path;

import javax.swing.*;
import java.awt.*;

/**
 * Created by filip on 09/11/2016.
 */
public class MasteryIconsPanel extends JPanel {

    public MasteryIconsPanel(){
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        Image image1 = new ImageIcon(Path.MASTERY_ICONS + "6111.png").getImage();
        ImageIcon icon1 = new ImageIcon(image1.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH ));
        JLabel teste1 = new JLabel(icon1);

        Image image2 = new ImageIcon(Path.MASTERY_ICONS + "6114.png").getImage();
        ImageIcon icon2 = new ImageIcon(image2.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH ));
        JLabel teste2 = new JLabel(icon2);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.fill = GridBagConstraints.NONE;

        gc.gridy = 0;
        gc.gridx=0;

        gc.gridheight = 1;
        gc.gridwidth = 1;

        add(new JPanel().add(teste1), gc);

        gc.gridx++;

        add(new JPanel().add(teste2), gc);
    }
}
