package GUI.info_panel.champion_panel;

import GUI.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by filip on 08/11/2016.
 */
public class LoadingImagePanel extends JPanel {
    public LoadingImagePanel(String path) {

        setLayout(new BorderLayout());
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage rounded = Utils.makeRoundedCorner(myPicture, 20);

        JLabel picLabel = new JLabel(new ImageIcon(rounded.getScaledInstance(246, 448, java.awt.Image.SCALE_SMOOTH)));


        add(picLabel, BorderLayout.NORTH);

        Border innerBorder = BorderFactory.createTitledBorder(" ");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

}

